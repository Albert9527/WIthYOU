package com.zcz1024.withyou.pojoVO;

import java.io.Serializable;

public class ActiveVo implements Serializable {

    /**
     * actId : 1
     * actTitle : 测试活动数据
     * userId : 1
     * actAddress : 翻斗街翻斗花园
     * actStartTime : 2020-04-24
     * actDuration : 2
     * actIntro : 这是一条测试数据
     * actScale : 5
     * actTag : test
     * actAuditState : 1
     * actPic : null
     */

    private String actId;
    private String actTitle;
    private String userId;
    private String actAddress;
    private String actStartTime;
    private int actDuration;
    private String actIntro;
    private int actScale;
    private String actTag;
    private int actAuditState;
    private Object actPic;

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

    public String getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(String actStartTime) {
        this.actStartTime = actStartTime;
    }

    public int getActDuration() {
        return actDuration;
    }

    public void setActDuration(int actDuration) {
        this.actDuration = actDuration;
    }

    public String getActIntro() {
        return actIntro;
    }

    public void setActIntro(String actIntro) {
        this.actIntro = actIntro;
    }

    public int getActScale() {
        return actScale;
    }

    public void setActScale(int actScale) {
        this.actScale = actScale;
    }

    public String getActTag() {
        return actTag;
    }

    public void setActTag(String actTag) {
        this.actTag = actTag;
    }

    public int getActAuditState() {
        return actAuditState;
    }

    public void setActAuditState(int actAuditState) {
        this.actAuditState = actAuditState;
    }

    public Object getActPic() {
        return actPic;
    }

    public void setActPic(Object actPic) {
        this.actPic = actPic;
    }
}
