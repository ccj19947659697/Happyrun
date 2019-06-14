package com.dudu.service;

import com.dudu.domain.MonitorResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
public interface MonitorService {

    List<MonitorResource> queryMonitorResourceList(Map<String,Object> params);
}
