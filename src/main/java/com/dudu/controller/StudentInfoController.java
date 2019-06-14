package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.StudentResource;
import com.dudu.service.StudentService;
import com.dudu.tools.ServletUtil;
import com.dudu.tools.StringUtil;
import com.dudu.tools.ValidatorUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/06/20.
 */

@Controller
@RequestMapping("/student")
public class StudentInfoController {

    @Autowired
    private StudentService studentService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @RequestMapping("")
    public String student(){
        return "student-resource";
    }

    //@RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "student-resource";

    }

    /**
     *
     * 查询学生信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/queryStudentList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryStudentList(HttpServletRequest request , HttpServletResponse response) throws IOException {

//        if(request.getSession().getAttribute("user")==null){
//
//            response.sendRedirect("toLogin");
//        }

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String account = request.getParameter("account");
        String name = request.getParameter("name");//通过学生学号 姓名关键字来查询
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("account", account);
        params.put("name", name);

        //try{

            List<StudentResource> studentList=studentService.queryStudentResourceList(params);
            PageInfo<StudentResource> pageInfo =new PageInfo<StudentResource>(studentList);
            JSONObject jo=new JSONObject();
            jo.put("rows", studentList);//包含实际数据的数组（这里面存的是数据？？？？ 是的 存的是studentList！！！）
            jo.put("total", pageInfo.getPages());//总页数
            jo.put("records",pageInfo.getTotal());//查询出的总记录数
            ServletUtil.createSuccessResponse(200, jo, response);//看 jo 变量中存的应该是返回值，而且是所以变量的返回值
            System.out.println(response);

        //}catch (Exception e){

            //response.sendRedirect("toLogin");



        }


    //}

    /**
     * 添加学生信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addStudent(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
//        String student_id1= student_id;
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String mail = request.getParameter("mail");


        //输入信息合法性验证
        if(StringUtil.isNumber(account)&&account.length()==7){

        }else {
            result.put("message","请重新添加学号!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        if(ValidatorUtil.isChineseName(name)){

        }else {
            result.put("message","请重新添加姓名!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

//        if((gender=="1")|| (gender=="2")){
// 判断字符串是否相等，不能用 == 要使用equals（），详见Java疯狂讲义 P168
//        }else{
//            result.put("message","请重新添加性别!");
//            result.put("flag",false);
//            ServletUtil.createSuccessResponse(200, result, response);
//            return;
//        }

        if(StringUtil.isGender(gender)){

        }else {
            result.put("message","请重新添加性别!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        try{
            int num = studentService.queryStudentResourceByAccount(String.valueOf(account));

            if(num>0){
                result.put("message","学号已存在，请重新添加!");
                result.put("flag",false);
                ServletUtil.createSuccessResponse(200, result, response);
                return;
            }
            else {
                StudentResource studentResource = new StudentResource();
                studentResource.setAccount(account);
                studentResource.setName(name);
                studentResource.setGender(gender);
                studentResource.setPasswd(password);
                studentResource.setTel(tel);
                studentResource.setMail(mail);

                int index = studentService.add(studentResource);
                if(index>0){
                    result.put("message","学生信息添加成功");
                    result.put("flag",true);
                }else {
                    result.put("message","学生信息添加失败");
                    result.put("flag",false);
                }

                ServletUtil.createSuccessResponse(200, result, response);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

    /**
     * 修改学生信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateStudent(HttpServletRequest request , HttpServletResponse response){
        JSONObject result=new JSONObject();
        String id = request.getParameter("id");
        StudentResource studentResource = studentService.queryStudentResourceById(Long.valueOf(id));
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String mail = request.getParameter("mail");

        //系统提示这段代码重复，后面再封装成一个方法   学号一般不可修改
        if(StringUtil.isNumber(account)&&account.length()==7)
        {

        }else {
            result.put("message","请重新修改学号!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(ValidatorUtil.isChineseName(name)){

        }else {
            result.put("message","请重新修改姓名!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }
        if(StringUtil.isGender(gender)){

        }else {
            result.put("message","请重新修改性别!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        studentResource.setAccount(account);
        studentResource.setName(name);
        studentResource.setGender(gender);
        studentResource.setPasswd(password);
        studentResource.setTel(tel);
        studentResource.setMail(mail);

        int index = studentService.update(studentResource);
        System.out.println("修改结果="+index);
        if(index>0){
            result.put("message","学生信息修改成功");
            result.put("flag",true);
        }else {
            result.put("message","学生信息修改失败");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }

    /**
     * 删除学生信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteStudent(HttpServletRequest request ,HttpServletResponse response){
        String ids = request.getParameter("ids");
        System.out.println("ids==="+ids);
        JSONObject result = new JSONObject();
        //删除操作
        int index =studentService.deleteByIds(ids.split(","));
        if(index>0){
            result.put("message","学生信息删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","学生信息删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }
}
