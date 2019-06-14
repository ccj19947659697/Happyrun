package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.HandleResource;
import com.dudu.service.HandleService;
import com.dudu.tools.ServletUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/07/26.
 */
@Controller
@RequestMapping("/handle")
public class HandleController {

    @Autowired
    private HandleService handleService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String handle(){
        return "handle-resource";
    }

    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    public String toLogin(){
        return "login";
    }

    /**
     * 查询成绩处理信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/queryHandleList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryScoreList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String account = request.getParameter("account");
        String runDate = request.getParameter("runDate");//通过学生学号 跑步日期关键字来查询
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("account", account);
        params.put("runDate", runDate);

        List<HandleResource> handleList = handleService.queryHandleResourceList(params);
        PageInfo<HandleResource> pageInfo = new PageInfo<HandleResource>(handleList);
        JSONObject jo=new JSONObject();
        jo.put("rows", handleList);
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records",pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);//看 jo 变量中存的应该是返回值，而且是所以变量的返回值
        System.out.println(response);


    }


    /**
     * 处理成绩成绩申诉
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateStudent(HttpServletRequest request , HttpServletResponse response) {
        JSONObject resultJson = new JSONObject();
        String id = request.getParameter("id");
        String appealDate = request.getParameter("appealDate");
        String account = request.getParameter("account");
        String runDate = request.getParameter("runDate");
        String success = request.getParameter("success");
        String result = request.getParameter("result");
        String status = request.getParameter("status");

        HandleResource handleResource = handleService.queryHandleResourceById(Long.valueOf(id));


        //输入合法性验证


        handleResource.setAppealDate(appealDate);
        handleResource.setAccount(account);
        handleResource.setRunDate(runDate);
        handleResource.setSuccess(success);
        handleResource.setResult(result);
        handleResource.setStatus(status);

        int index = handleService.update(handleResource);
        System.out.println("修改结果="+index);
        if(index>0){
            resultJson.put("message","成绩申诉处理完成");
            resultJson.put("flag",true);
        }else {
            resultJson.put("message","成绩申诉处理失败");
            resultJson.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, resultJson, response);

    }

}
