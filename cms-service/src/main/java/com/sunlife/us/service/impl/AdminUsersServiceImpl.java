/*
*  Used for search the employees, get the security functions,
*  get list of registered users, delete registered users and to
*  insert registered users for Admin
* */
package com.sunlife.us.service.impl;

import com.sunlife.us.entities.HrdmEmployeeV;
import com.sunlife.us.entities.RefData;
import com.sunlife.us.entities.UserSecurity;
import com.sunlife.us.mybatis.dao.AdminUsersDao;
import com.sunlife.us.service.AdminUsersService;
import sunlife.us.dc.utility.log.LogMethodCalls;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
@LogMethodCalls
public class AdminUsersServiceImpl implements AdminUsersService {

    @Inject
    @Named("adminUsersDaoImpl")
    private AdminUsersDao adminUsersDao;

    @Override
    public List<HrdmEmployeeV> searchEmployees(String searchCriteria) {

        return adminUsersDao.searchEmployees(searchCriteria);

    }

    @Override
    public List<RefData> getSecurityFunctions() {

        return adminUsersDao.getSecurityFunctions();


    }

    @Override
    public List<HrdmEmployeeV> getRegisteredUsers() {

        return adminUsersDao.getRegisteredUsers();


    }

    @Override
    public Integer deleteRegisteredUser(String emplId) {

        return adminUsersDao.deleteRegisteredUser(emplId);


    }

    @Override
    public Integer insertRegisteredUser(UserSecurity userSecurity, String secRoleCdCR, String secRoleCdNC, String secRoleCdRS) {

        return adminUsersDao.insertRegisteredUser(userSecurity, secRoleCdCR, secRoleCdNC, secRoleCdRS);

    }
}
