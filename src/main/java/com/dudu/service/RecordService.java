package com.dudu.service;

import com.dudu.domain.RecordResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
public interface RecordService {

    List<RecordResource> queryRecordResourceList(Map<String,Object> params);

}
