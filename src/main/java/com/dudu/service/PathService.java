package com.dudu.service;

import com.dudu.domain.PathResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
public interface PathService {

    List<PathResource> queryPathResourceList(Map<String,Object> params);

}
