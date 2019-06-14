package com.dudu.service.impl;

import com.dudu.dao.StudentMapper;
import com.dudu.domain.StudentResource;
import com.dudu.service.StudentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/06/27.
 */
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentMapper studentMapper;

    @Override
    public int add(StudentResource studentResource){return this.studentMapper.add(studentResource);}

    @Override
    public int update(StudentResource studentResource){return this.studentMapper.update(studentResource);}

    @Override
    public int deleteByIds(String[] ids) {return this.studentMapper.deleteByIds(ids);}

    @Override
    public int queryStudentResourceByAccount(String account){return this.studentMapper.queryStudentResourceByAccount(account);}

    @Override
    public StudentResource queryStudentResourceById(Long id){return this.studentMapper.queryStudentResourceById(id);}

    @Override
    public List<StudentResource> queryStudentResourceList(Map<String,Object> params){
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()),Integer.parseInt(params.get("rows").toString()));
        return this.studentMapper.queryStudentResourceList(params);
    }

}
