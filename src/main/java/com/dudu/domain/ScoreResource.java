package com.dudu.domain;

import java.util.Date;

/**
 * Created by Administrator on 2017/07/25.
 */
public class ScoreResource {

    private Long id;
    private String account;
    private String date;
    private String pathId;
    private String singleDis;
    private String photo;
    private String time;
    private String beginTime;
    private String speed;
    private String steps;
    private String singleOk;
    private String totalDis;//这两个变量不需要在web端显示 但是需要对其进行处理
    private String totalOk;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getSingleDis() {
        return singleDis;
    }

    public void setSingleDis(String singleDis) {
        this.singleDis = singleDis;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getSingleOk() {
        return singleOk;
    }

    public void setSingleOk(String singleOk) {
        this.singleOk = singleOk;
    }

    public String getTotalDis() {
        return totalDis;
    }

    public void setTotalDis(String totalDis) {
        this.totalDis = totalDis;
    }

    public String getTotalOk() {
        return totalOk;
    }

    public void setTotalOk(String totalOk) {
        this.totalOk = totalOk;
    }
}
