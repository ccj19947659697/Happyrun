package com.dudu.service.impl;

import com.dudu.dao.ScoreMapper;
import com.dudu.domain.ScoreResource;
import com.dudu.service.ScoreService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/25.
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public int add(ScoreResource scoreResource){return this.scoreMapper.add(scoreResource);}

    @Override
    public int update(ScoreResource scoreResource){return this.scoreMapper.update(scoreResource);}

    @Override
    public int deleteByIds(String[] ids){return this.scoreMapper.deleteByIds(ids);}

    @Override
    public ScoreResource queryScoreResourceById(Long id){return this.scoreMapper.queryScoreResourceById(id);}

    @Override
    public List<ScoreResource> queryScoreResourceList(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.scoreMapper.queryScoreResourceList(params);
    }

}
