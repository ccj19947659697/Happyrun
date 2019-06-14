package com.dudu.service.impl;

import com.dudu.dao.HandleMapper;
import com.dudu.domain.HandleResource;
import com.dudu.service.HandleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
@Service
public class HandleServiceImpl implements HandleService{

    @Autowired
    HandleMapper handleMapper;

    @Override
    public int update(HandleResource handleResource){return this.handleMapper.update(handleResource);}

    @Override
    public HandleResource queryHandleResourceById(Long id){return this.handleMapper.queryHandleResourceById(id);}

    @Override
    public List<HandleResource> queryHandleResourceList(Map<String,Object> params){

        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.handleMapper.queryHandleResourceList(params);
    }

}
