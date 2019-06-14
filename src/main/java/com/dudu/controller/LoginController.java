package com.dudu.controller;

import com.dudu.domain.AdminResource;
import com.dudu.domain.User;
import com.dudu.service.AdminService;
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

/** 登录
 * Created by tengj on 2017/4/10.
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, HttpServletResponse response){


        Map<String,Object> map =new HashMap<String,Object>();
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        //String s123 = request.getParameter("s123");

        if(userName!="" && password!=""){
            try{
                AdminResource adminResource = adminService.queryAdminResourceByAccount(String.valueOf(userName));
                String psd = adminResource.getPasswd();

                User user =new User(userName,password);//这两句为什么不能去掉？？？？
                request.getSession().setAttribute("user",user);
                request.getSession().setMaxInactiveInterval(10*60);//session生命周期设置为10分钟（连续10分钟无任何操作就消session）
                //request.getSession().setAttribute("pwsd",password);

                if(password.equals(psd)){
                    map.put("result","0");//验证成功
                }else {
                    map.put("result","1");//用户名与密码不匹配
                }

            }catch (Exception e){

                map.put("result","3");//用户名不存在
            }
        }else{
            map.put("result","2");//用户名或密码为空
        }

        return map;
    }


    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> map =new HashMap<String,Object>();
        try{
            request.getSession().removeAttribute("user");
            map.put("result","0");//用户名session清空
        }catch (Exception e){

            map.put("result","1");//用户名session未清空
        }

        return map;
    }

//    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
//    //@RequestMapping(value = "/toLogin")
//    public String toLogin(){
//        return "login";
//    }


}
