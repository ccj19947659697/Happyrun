package com.dudu.service;

import com.dudu.domain.HandleResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
public interface HandleService {

    //申诉处理 web端只进行 查询 和修改

    int update(HandleResource handleResource);
    HandleResource queryHandleResourceById(Long handleResource);

    List<HandleResource> queryHandleResourceList(Map<String,Object> params);


}
