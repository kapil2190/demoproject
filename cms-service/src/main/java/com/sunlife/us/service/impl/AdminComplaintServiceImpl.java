/*
* to get list of locked complaints, unlock complaints,
* search complaints and delete complaints for Admin
* */
package com.sunlife.us.service.impl;

import com.sunlife.us.entities.Complaint;
import com.sunlife.us.entities.HrdmEmployeeV;
import com.sunlife.us.mybatis.dao.AdminComplaintDao;
import com.sunlife.us.service.AdminComplaintService;
import sunlife.us.dc.utility.log.LogMethodCalls;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
@Named
@ApplicationScoped
@LogMethodCalls
public class AdminComplaintServiceImpl implements AdminComplaintService {

    @Inject
    @Named("adminComplaintDaoImpl")
    private AdminComplaintDao adminComplaintDao;

    @Override

    public List<HrdmEmployeeV> getLockedComplaints() {

        return adminComplaintDao.getLockedComplaints();

    }

    @Override
    public Integer unlockComplaintExec(Integer cmplID, String acf2ID) {

        return adminComplaintDao.unlockComplaintExec(cmplID, acf2ID);

    }

    @Override
    public List<Complaint> searchComplaint(Integer cmplId) {

        return adminComplaintDao.searchComplaint(cmplId);

    }

    @Override
    public boolean deleteComplaint(Integer cmplId) {

        return adminComplaintDao.deleteComplaint(cmplId);

    }

}
