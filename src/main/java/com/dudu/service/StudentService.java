package com.dudu.service;

import com.dudu.domain.StudentResource;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/06/27.
 */
public interface StudentService {

    int add(StudentResource studentResource);
    int update(StudentResource studentResource);
    int deleteByIds(String[] ids);
    int queryStudentResourceByAccount(String account);
    StudentResource queryStudentResourceById(Long studentResource);
    List<StudentResource> queryStudentResourceList(Map<String, Object> params);

}
