package com.dudu.domain;

/**
 * Created by Administrator on 2017/07/26.
 */
public class HandleResource {

    private Long id;
    private String appealDate;//申诉日期
    private String account;//申诉人学号
    private String runDate;//要申诉的跑步日期
    private String success;//申诉是否成功
    private String result;//申诉处理结果描述
    private String status;//申诉处理所处的状态

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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
