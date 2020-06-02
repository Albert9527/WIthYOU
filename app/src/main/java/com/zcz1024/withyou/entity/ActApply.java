package com.zcz1024.withyou.entity;


public class ActApply {
    //申请表ID
    private String actApplyId;

    //活动id
    private String actId;

    //申请者ID
    private String userId;

    //申请时间
    private String actApplyTime;

    //申请理由
    private String actApplyReason;

    //审核状态
    private Integer actAuditState;

    public String getActApplyId() {
        return actApplyId;
    }

    public void setActApplyId(String actApplyId) {
        this.actApplyId = actApplyId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActApplyTime() {
        return actApplyTime;
    }

    public void setActApplyTime(String actApplyTime) {
        this.actApplyTime = actApplyTime;
    }

    public String getActApplyReason() {
        return actApplyReason;
    }

    public void setActApplyReason(String actApplyReason) {
        this.actApplyReason = actApplyReason;
    }

    public Integer getActAuditState() {
        return actAuditState;
    }

    public void setActAuditState(Integer actAuditState) {
        this.actAuditState = actAuditState;
    }
}

