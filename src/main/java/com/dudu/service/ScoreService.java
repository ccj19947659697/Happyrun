package com.dudu.service;

import com.dudu.domain.ScoreResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
public interface ScoreService {

    //还差一个通过 学号 查最新成绩记录的 查询方法 参考 adminservice

    int add(ScoreResource scoreResource);
    int update(ScoreResource scoreResource);
    int deleteByIds(String[] ids);
    ScoreResource queryScoreResourceById(Long scoreResource);
    List<ScoreResource> queryScoreResourceList(Map<String, Object> params);

}
