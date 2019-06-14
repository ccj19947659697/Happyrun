package com.dudu.dao;

import com.dudu.domain.StudentResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/06/27.
 */
@Mapper
public interface StudentMapper {

    int add(StudentResource studentResource);
    int update(StudentResource studentResource);
    int deleteByIds(String[] ids);
    int queryStudentResourceByAccount(String account);
    StudentResource queryStudentResourceById(Long studentResource);
    public List<StudentResource> queryStudentResourceList(Map<String, Object> params);


}
