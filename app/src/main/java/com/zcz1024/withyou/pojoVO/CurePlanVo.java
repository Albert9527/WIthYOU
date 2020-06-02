package com.zcz1024.withyou.pojoVO;

public class CurePlanVo {

    /**
     * rcmcpId : 1
     * rcmcpName : 电击疗法
     * rcmcpIntro : 使用高压电电击人体
     * userId : 1
     * categoryTag : test
     * praiseCount : 0
     * createTime : 2020-04-26
     * rcmcpReason : 真实有效
     * founder : 杨永信
     */

    private String rcmcpId;
    private String rcmcpName;
    private String rcmcpIntro;
    private String userId;
    private String categoryTag;
    private int praiseCount;
    private String createTime;
    private String rcmcpReason;
    private String founder;

    public String getRcmcpId() {
        return rcmcpId;
    }

    public void setRcmcpId(String rcmcpId) {
        this.rcmcpId = rcmcpId;
    }

    public String getRcmcpName() {
        return rcmcpName;
    }

    public void setRcmcpName(String rcmcpName) {
        this.rcmcpName = rcmcpName;
    }

    public String getRcmcpIntro() {
        return rcmcpIntro;
    }

    public void setRcmcpIntro(String rcmcpIntro) {
        this.rcmcpIntro = rcmcpIntro;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRcmcpReason() {
        return rcmcpReason;
    }

    public void setRcmcpReason(String rcmcpReason) {
        this.rcmcpReason = rcmcpReason;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }
}
