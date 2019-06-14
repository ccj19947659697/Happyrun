package com.dudu.domain;

/**
 * Created by Administrator on 2017/07/26.
 */
public class RecordResource {

    private Long id;
    private String appealDate;//申诉日期
    private String account;//申诉人学号
    private String runDate;//要申诉的跑步日期
    private String pathId;//要申诉的路径编号
    private String reason;//申诉理由描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(String appealDate) {
        this.appealDate = appealDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
