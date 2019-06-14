package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.AdminResource;
import com.dudu.service.AdminService;
import com.dudu.tools.ServletUtil;
import com.dudu.tools.StringUtil;
import com.dudu.tools.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/20.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/getAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getAdmin(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> map =new HashMap<String,Object>();
        String adminName=request.getParameter("adminName");

        try{

            AdminResource adminResource = adminService.queryAdminResourceByAccount(String.valueOf(adminName));

            map.put("result1",adminResource.getId());
            map.put("result2",adminResource.getAccount());
            map.put("result3",adminResource.getName());
            map.put("result4",adminResource.getPasswd());
            map.put("result5",adminResource.getGender());
            map.put("result6",adminResource.getOfficeTel());
            map.put("result7",adminResource.getMail());

        }catch (Exception e){

        }

        return map;

    }

    @RequestMapping(value = "/updateAdmin",method = RequestMethod.POST)
    public void updateAdmin(HttpServletRequest request, HttpServletResponse response){

        JSONObject result=new JSONObject();
        String id = request.getParameter("id");
        String account = request.getParameter("account");//这里的变量值 不一定要与实体 或mapper.xml文件中的属性字段名称一致
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String officeTel = request.getParameter("tel");
        String mail = request.getParameter("mail");
        AdminResource adminResource = adminService.queryAdminResourceByAccount(String.valueOf(account));

        //数据合输入法性验证
        if(ValidatorUtil.isChineseName(name)){
            result.put("message","请重新添加姓名!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        if(!ValidatorUtil.isPasswd(password)){
        //if(StringUtil.isNull(password)){
            result.put("message","密码需为6-12位的字母或数字组合!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        if(StringUtil.isGender(gender)){
            result.put("message","请重新修改性别!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        if(ValidatorUtil.isMobile(officeTel)){
            result.put("message","请重新修改办公电话!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        //邮箱合法性验证
        if(ValidatorUtil.isEmail(mail)){
            result.put("message","请重新修改邮箱!");
            result.put("flag",false);
            ServletUtil.createSuccessResponse(200, result, response);
            return;
        }

        adminResource.setName(name);
        adminResource.setPasswd(password);
        adminResource.setGender(gender);
        adminResource.setOfficeTel(officeTel);
        adminResource.setMail(mail);

        int index = adminService.update(adminResource);
        System.out.println("修改结果="+index);
        if(index>0){
            result.put("message","个人信息修改成功");
            result.put("flag",true);
        }else {
            result.put("message","个人信息修改失败");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

}
