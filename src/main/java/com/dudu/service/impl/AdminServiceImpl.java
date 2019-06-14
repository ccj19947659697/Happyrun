package com.dudu.service.impl;

import com.dudu.dao.AdminMapper;
import com.dudu.domain.AdminResource;
import com.dudu.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/07/13.
 */
@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminResource queryAdminResourceByAccount(String account){return this.adminMapper.queryAdminResourceByAccount(account);}

    @Override
    public int update(AdminResource adminResource){return this.adminMapper.update(adminResource);}

}
