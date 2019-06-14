package com.dudu.service.impl;

import com.dudu.dao.MonitorMapper;
import com.dudu.domain.MonitorResource;
import com.dudu.service.MonitorService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
@Service
public class MonitorServiceImpl implements MonitorService{

    @Autowired
    MonitorMapper monitorMapper;

    @Override
    public List<MonitorResource> queryMonitorResourceList(Map<String,Object> params){

        PageHelper.startPage(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("rows").toString()));
        return this.monitorMapper.queryMonitorResourceList(params);

    }
}
