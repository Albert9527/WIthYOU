package com.zcz1024.withyou.entity;


import java.sql.Date;

public class Activity {

    private String actId;

    private String actTitle;

    private String userId;

    private String actAddress;

    private Date actStartTime;

    private Integer actDuration;

    private String actIntro;

    private Integer actScale;

    private String actTag;

    private Integer actAuditState;

    private String actPic;

    private Integer act_join_num;

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActTitle() {
        return actTitle;
    }

    public void setActTitle(String actTitle) {
        this.actTitle = actTitle;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getActAddress() {
        return actAddress;
    }

    public void setActAddress(String actAddress) {
        this.actAddress = actAddress;
    }

    public Date getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Date actStartTime) {
        this.actStartTime = actStartTime;
    }

    public Integer getActDuration() {
        return actDuration;
    }

    public void setActDuration(Integer actDuration) {
        this.actDuration = actDuration;
    }

    public String getActIntro() {
        return actIntro;
    }

    public void setActIntro(String actIntro) {
        this.actIntro = actIntro;
    }

    public Integer getActScale() {
        return actScale;
    }

    public void setActScale(Integer actScale) {
        this.actScale = actScale;
    }

    public String getActTag() {
        return actTag;
    }

    public void setActTag(String actTag) {
        this.actTag = actTag;
    }

    public Integer getActAuditState() {
        return actAuditState;
    }

    public void setActAuditState(Integer actAuditState) {
        this.actAuditState = actAuditState;
    }

    public String getActPic() {
        return actPic;
    }

    public void setActPic(String actPic) {
        this.actPic = actPic;
    }

    public Integer getAct_join_num() {
        return act_join_num;
    }

    public void setAct_join_num(Integer act_join_num) {
        this.act_join_num = act_join_num;
    }
}
