package com.dudu.dao;

import com.dudu.domain.HandleResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
@Mapper
public interface HandleMapper {

    int update(HandleResource handleResource);
    HandleResource queryHandleResourceById(Long handleResource);

    public List<HandleResource> queryHandleResourceList(Map<String,Object> params);

}
