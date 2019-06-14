package com.dudu.service.impl;

import com.dudu.dao.RecordMapper;
import com.dudu.domain.RecordResource;
import com.dudu.service.RecordService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<RecordResource> queryRecordResourceList(Map<String,Object> params){

        PageHelper.startPage(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("rows").toString()));
        return this.recordMapper.queryRecordResourceList(params);
    }

}
