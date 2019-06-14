package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.RecordResource;
import com.dudu.service.RecordService;
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
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String record(){return "record-resource";}

    /**
     *查询路径信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/queryRecordList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryPathList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String account = request.getParameter("account");
        String runDate = request.getParameter("runDate");

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("account", account);//path_id 为查询数据库的字段变量 与mapper.xml中数据库字段对应
        params.put("runDate", runDate);

        List<RecordResource> recordList = recordService.queryRecordResourceList(params);
        PageInfo<RecordResource> pageInfo = new PageInfo<RecordResource>(recordList);
        JSONObject jo=new JSONObject();
        jo.put("rows", recordList);
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records",pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);//看 jo 变量中存的应该是返回值，而且是所以变量的返回值
        System.out.println(response);

    }



}
