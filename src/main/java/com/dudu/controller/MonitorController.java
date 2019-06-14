package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.MonitorResource;
import com.dudu.service.MonitorService;
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
 * Created by Administrator on 2017/07/25.
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String monitor(){
        return "monitor-resource";
    }


    @RequestMapping(value = "/queryMonitorList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryMonitorList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String name = request.getParameter("name");//监测点名称
        String location = request.getParameter("location");//监测点位置

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("name", name);//"name" 为查询数据库的字段变量 与mapper.xml中数据库字段对应
        params.put("location", location);

        List<MonitorResource> monitorList = monitorService.queryMonitorResourceList(params);
        PageInfo<MonitorResource> pageInfo = new PageInfo<MonitorResource>(monitorList);
        JSONObject jo=new JSONObject();
        jo.put("rows", monitorList);//包含实际数据的数组（这里面存的是数据？？？？ 是的 存的是studentList！！！）
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records",pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);//看 jo 变量中存的应该是返回值，而且是所以变量的返回值
        System.out.println(response);

    }

}
