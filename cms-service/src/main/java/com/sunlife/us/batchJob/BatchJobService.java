package com.sunlife.us.batchJob;

import java.time.LocalDateTime;

public interface BatchJobService {
    boolean executeJob(LocalDateTime time,String jobType);
    boolean triggerDailyJob();

    boolean triggerYearlyJob();
}
