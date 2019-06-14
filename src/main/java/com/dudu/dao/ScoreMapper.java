package com.dudu.dao;

import com.dudu.domain.ScoreResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
@Mapper
public interface ScoreMapper {

    int add(ScoreResource scoreResource);
    int update(ScoreResource scoreResource);
    int deleteByIds(String[] ids);
    ScoreResource queryScoreResourceById(Long scoreResource);
    public List<ScoreResource> queryScoreResourceList(Map<String, Object> params);

}
