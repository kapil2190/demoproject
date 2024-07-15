/*
* service call class used for getting the user and to login functionalities
* */
package com.sunlife.us.service.impl;

import com.sunlife.us.entities.HrdmEmployeeV;
import com.sunlife.us.mail.EmailResource;
import com.sunlife.us.mybatis.dao.CommonListsDao;
import com.sunlife.us.service.AuthrizationService;
import sunlife.us.dc.utility.log.LogMethodCalls;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ApplicationScoped
@LogMethodCalls
public class AuthrizationServiceImpl implements AuthrizationService, Serializable {

    @Inject
    @Named("commonListsDaoImpl")
    private CommonListsDao commonListsDao;
    @Inject
    private EmailResource emailResource;

    @PostConstruct
    void init(){
        //emailResource.init();
    }

    @Override
    public HrdmEmployeeV getUser(String ac2fID) {

            return commonListsDao.getUser(ac2fID);

    }
    @Override
    public HrdmEmployeeV logIn(String acf2Id) {

            return commonListsDao.logIn(acf2Id);

    }
}
