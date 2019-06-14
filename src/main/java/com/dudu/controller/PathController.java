package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.PathResource;
import com.dudu.service.PathService;
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
import java.util.Map;
import java.util.List;
/**
 * Created by Administrator on 2017/07/25.
 */
@Controller
@RequestMapping("/path")
public class PathController {


    @Autowired
    private PathService pathService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String path(){
        return "path-resource";
    }

    /**
     *查询路径信息
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/queryPathList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryPathList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String pathId = request.getParameter("path_id");//path_id 为界面传参  pathId为 后台定义的变量
        String begin = request.getParameter("begin");
        String end = request.getParameter("end");

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("pathId", pathId);//path_id 为查询数据库的字段变量 与mapper.xml中数据库字段对应 但变量命名不需要与表中字段一致，关系对应即可
        params.put("begin", begin);
        params.put("end", end);

        List<PathResource> pathList = pathService.queryPathResourceList(params);
        PageInfo<PathResource> pageInfo = new PageInfo<PathResource>(pathList);
        JSONObject jo=new JSONObject();
        jo.put("rows", pathList);//包含实际数据的数组（这里面存的是数据？？？？ 是的 存的是studentList！！！）
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records",pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);//看 jo 变量中存的应该是返回值，而且是所以变量的返回值
        System.out.println(response);


    }
}
