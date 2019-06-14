package com.dudu.service;

import com.dudu.domain.AdminResource;

/**
 * Created by Administrator on 2017/07/13.
 */
public interface AdminService {

    AdminResource queryAdminResourceByAccount(String adminResource);

    int update(AdminResource adminResource);

}
