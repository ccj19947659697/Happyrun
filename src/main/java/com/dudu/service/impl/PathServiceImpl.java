package com.dudu.service.impl;

import com.dudu.dao.PathMapper;
import com.dudu.domain.PathResource;
import com.dudu.service.PathService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
@Service
public class PathServiceImpl implements PathService{

    @Autowired
    PathMapper pathMapper;

    @Override
    public List<PathResource> queryPathResourceList(Map<String,Object> params){
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("rows").toString()));
        return this.pathMapper.queryPathResourceList(params);
    }

}
