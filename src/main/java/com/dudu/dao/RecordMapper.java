package com.dudu.dao;

import com.dudu.domain.RecordResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
@Mapper
public interface RecordMapper {

    public List<RecordResource> queryRecordResourceList(Map<String,Object> params);

}
