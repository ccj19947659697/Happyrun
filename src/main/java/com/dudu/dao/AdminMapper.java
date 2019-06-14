package com.dudu.dao;

import com.dudu.domain.AdminResource;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Administrator on 2017/07/13.
 */
@Mapper
public interface AdminMapper {

    AdminResource queryAdminResourceByAccount(String account);
    int update(AdminResource adminResource);

}
