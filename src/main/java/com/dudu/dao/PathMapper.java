package com.dudu.dao;

import com.dudu.domain.PathResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
@Mapper
public interface PathMapper {

    public List<PathResource> queryPathResourceList(Map<String,Object> params);
}
