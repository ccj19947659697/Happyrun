package com.dudu.dao;

import com.dudu.domain.MonitorResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
@Mapper
public interface MonitorMapper {

    public List<MonitorResource> queryMonitorResourceList(Map<String,Object> params);

}
