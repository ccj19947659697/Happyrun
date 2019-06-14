package com.dudu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dudu.domain.ScoreResource;
import com.dudu.service.ScoreService;
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
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String score(){
        return "score-resource";
    }

    @RequestMapping(value = "/toLogin",method = RequestMethod.POST)
    public String toLogin(){
        return "login";
    }

    /**
     * 查询成绩
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/queryScoreList",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void queryScoreList(HttpServletRequest request , HttpServletResponse response) throws IOException {

        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        String account = request.getParameter("account");
        String date = request.getParameter("date");//通过学生学号 姓名关键字来查询
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("page", page);
        params.put("rows", rows);
        params.put("account", account);
        params.put("date", date);
        //params.put("name", date);

        List<ScoreResource> scoreList=scoreService.queryScoreResourceList(params);
        PageInfo<ScoreResource> pageInfo =new PageInfo<ScoreResource>(scoreList);
        JSONObject jo=new JSONObject();
        jo.put("rows", scoreList);
        jo.put("total", pageInfo.getPages());//总页数
        jo.put("records",pageInfo.getTotal());//查询出的总记录数
        ServletUtil.createSuccessResponse(200, jo, response);//看 jo 变量中存的应该是返回值，而且是所以变量的返回值
        System.out.println(response);
    }

    /**
     * 添加成绩  还需将 totalDis totalOk 两个字段写入到最新一条记录中！！！（可在添加之后继续修改操作）
     * @param request
     * @param response
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addScore(HttpServletRequest request , HttpServletResponse response) {
        JSONObject result = new JSONObject();
//        String student_id1= student_id;
        String account = request.getParameter("account");
        String date = request.getParameter("date");
        String pathId = request.getParameter("pathId");
        String singleDis = request.getParameter("singleDis");
        //String photo = request.getParameter("photo");
        String photo = "/1234";//添加时图片可以从对应的申诉记录中读取路径
        String time = request.getParameter("time");
        String beginTime = request.getParameter("beginTime");
        String speed = request.getParameter("speed");
        String steps = request.getParameter("steps");
        String singleOk = request.getParameter("singleOk");


        //String totalDis = request.getParameter("totalDis");
        //String totalOk = request.getParameter("totalOk");
        String totalDis = "1234";
        String totalOk ="否";


        //输入合法性验证


        ScoreResource scoreResource = new ScoreResource();
        scoreResource.setAccount(account);
        scoreResource.setDate(date);
        scoreResource.setPathId(pathId);
        scoreResource.setSingleDis(singleDis);
        scoreResource.setPhoto(photo);
        scoreResource.setTime(time);
        scoreResource.setBeginTime(beginTime);
        scoreResource.setSpeed(speed);
        scoreResource.setSteps(steps);
        scoreResource.setSingleOk(singleOk);
        scoreResource.setTotalDis(totalDis);
        scoreResource.setTotalOk(totalOk);

        int index = scoreService.add(scoreResource);
        if(index>0){
            result.put("message","学生成绩添加成功！");
            result.put("flag",true);
        }else {
            result.put("message","学生成绩添加失败，请重试！");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);

    }

    /**
     * 修改学生成绩 还需将 totalDis totalOk 两个字段写入到最新一条记录中！！！（可在修改之后继续修改操作）
     * select *from student_score WHERE account = '2016001' ORDER BY id DESC LIMIT 1
     * @param request
     * @param response
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void updateStudent(HttpServletRequest request , HttpServletResponse response) {
        JSONObject result = new JSONObject();
        String id = request.getParameter("id");
        String account = request.getParameter("account");
        String date = request.getParameter("date");
        String pathId = request.getParameter("pathId");
        String singleDis = request.getParameter("singleDis");
        //String photo = request.getParameter("photo");//修改时 图片地址不处理。
        String time = request.getParameter("time");
        String beginTime = request.getParameter("beginTime");
        String speed = request.getParameter("speed");
        String steps = request.getParameter("steps");
        String singleOk = request.getParameter("singleOk");


        //String totalDis = request.getParameter("totalDis");
        //String totalOk = request.getParameter("totalOk");
        String totalDis = "1234";
        String totalOk ="否";

        ScoreResource scoreResource = scoreService.queryScoreResourceById(Long.valueOf(id));

        //输入合法性验证

        scoreResource.setAccount(account);
        scoreResource.setDate(date);
        scoreResource.setPathId(pathId);
        scoreResource.setSingleDis(singleDis);
        //scoreResource.setPhoto(photo);
        scoreResource.setTime(time);
        scoreResource.setBeginTime(beginTime);
        scoreResource.setSpeed(speed);
        scoreResource.setSteps(steps);
        scoreResource.setSingleOk(singleOk);
        scoreResource.setTotalDis(totalDis);
        scoreResource.setTotalOk(totalOk);


        int index = scoreService.update(scoreResource);
        System.out.println("修改结果="+index);
        if(index>0){
            result.put("message","学生成绩修改成功");
            result.put("flag",true);
        }else {
            result.put("message","学生成绩修改失败");
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
    public void deleteStudent(HttpServletRequest request ,HttpServletResponse response) {
        String ids = request.getParameter("ids");
        System.out.println("ids===" + ids);
        JSONObject result = new JSONObject();

        //删除操作
        int index = scoreService.deleteByIds(ids.split(","));
        if(index>0){
            result.put("message","学生成绩删除成功!");
            result.put("flag",true);
        }else{
            result.put("message","学生成绩删除失败!");
            result.put("flag",false);
        }
        ServletUtil.createSuccessResponse(200, result, response);
    }



}
