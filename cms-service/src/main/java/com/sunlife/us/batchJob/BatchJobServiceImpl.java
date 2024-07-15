package com.sunlife.us.batchJob;

import com.sunlife.us.entities.Complaint;
import com.sunlife.us.exception.CMSRunTimeException;
import com.sunlife.us.mail.EmailResource;
import org.omnifaces.cdi.Eager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Named
@Eager
@ApplicationScoped
public class BatchJobServiceImpl implements BatchJobService {
    private static final Logger LOG = LoggerFactory.getLogger(BatchJobServiceImpl.class);
    @Inject
    private BatchJobDao batchJobDao;
    @Inject
    private EmailResource emailResource;

    @Resource(name = "cell/persistent/cms/executeDailyEmailBatchFlag", lookup = "cell/persistent/cms/executeDailyEmailBatchFlag")
    private Boolean executeDailyEmailBatchFlag;

    @PostConstruct
    void init() {
        if (executeDailyEmailBatchFlag) {
            LOG.info("Method is starting eagerly");
            LocalDateTime executionTimeDaily = LocalTime.of(21, 00, 00)
                    .atDate(LocalDate.now());

            if (LocalTime.now().isAfter(LocalTime.of(21, 00, 00))) {
                executionTimeDaily = LocalTime.of(21, 00, 00)
                        .atDate(LocalDate.now().plusDays(1));
            }
            LocalDateTime executionTimeYearly = LocalTime.of(21, 00, 00)
                    .atDate(LocalDate.of(LocalDate.now().getYear() + 1, 1, 1));
            LocalDateTime finalExecutionTimeDaily = executionTimeDaily;
            CompletableFuture.supplyAsync(() -> executeJob(finalExecutionTimeDaily.plusSeconds(30), "Daily"))
                    .thenAccept(i -> LOG.info("Triggered daily Batch job async "));
            CompletableFuture.supplyAsync(() -> executeJob(executionTimeYearly.plusSeconds(30), "Yearly"))
                    .thenAccept(i -> LOG.info("Triggered Yearly Batch job async"));
        }
    }

    @Override
    public boolean executeJob(LocalDateTime executionTime, String jobType) {
        //while (true) {
			System.out.println("testing ");
            LOG.info("checking time and sleeping " + LocalDateTime.now() + "recieved Time " + executionTime + " for job " + jobType);
            long timeForDelay = ChronoUnit.SECONDS.between(LocalDateTime.now(), executionTime);
            LOG.info("time delay in second for " + jobType + "  " + timeForDelay);
            try {
                Thread.sleep(Math.abs(timeForDelay) * 200); //It will go in while loop at most log(timeFordelay) base 5 times
            } catch (InterruptedException e) {
                LOG.error("Interrupted exception occured while initiating the "+jobType +" Job");
            }
            if (LocalDateTime.now().isAfter(executionTime.minusSeconds(10))) {
                LOG.info("firing " + jobType + " job inside while");
                if (jobType.equalsIgnoreCase("Daily")) {
                    fireDailyJob();
                    return true;
                } else if (jobType.equalsIgnoreCase("Yearly")) {
                    fireYearlyJob();
                    return true;
                } else {
                    throw new CMSRunTimeException("Wrong input for JobType");
                }
            }

            if (LocalDateTime.now().isAfter(executionTime.plusMinutes(1))) {
                LOG.info("breaking " + jobType + " out from while");
                return true;
            }
        }

    }

    private void fireDailyJob() {
        LOG.info("ExecService started");
        ScheduledExecutorService batchService = Executors.newSingleThreadScheduledExecutor();
        batchService.scheduleAtFixedRate(() -> triggerDailyJob(), 0, 1, TimeUnit.DAYS);
    }

    private void fireYearlyJob() {
        LOG.info("ExecService started for yearly");
        ScheduledExecutorService batchService = Executors.newSingleThreadScheduledExecutor();
        //batchService.scheduleAtFixedRate(() -> triggerYearlyJob(), 0, 1, TimeUnit.DAYS);
        batchService.scheduleAtFixedRate(() -> triggerYearlyJob(), 0, 365, TimeUnit.DAYS);
    }


    @Override
    public boolean triggerDailyJob() {
            LOG.info("starting the Daily Job");
            LocalDateTime qDate = LocalDateTime.now().minusDays(14);
            List<Complaint> status = batchJobDao.triggerDailyJob(Timestamp.valueOf(qDate), Timestamp.valueOf(LocalDateTime.now()));
            if (status != null) {
                String data = "Here is a list of complaints updated \n";
                data = data.concat(status.stream().map(i -> String.valueOf(i.getCmplId()).concat(",").concat(i.getRecvDeptNm())).collect(Collectors.joining("\n")));
                emailResource.sendEmail(data);
            }
            LOG.info("Log can be found " + status);
        return true;
    }

    @Override
    public boolean triggerYearlyJob() {
        LOG.info("starting the Yearly Job");
        boolean status = batchJobDao.triggerYearlyJob();
        LOG.info("Log can be found " + status);
        return status;
    }
}
