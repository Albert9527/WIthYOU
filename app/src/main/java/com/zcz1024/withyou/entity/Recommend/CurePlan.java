package com.zcz1024.withyou.entity.Recommend;


import java.sql.Date;

public class CurePlan {

    private String rcmcpId;

    private String rcmcpName;

    private String rcmcpIntro;

    private String userId;

    private String categoryTag;

    private Integer praiseCount;

    //发布日期
    private Date createTime;

    private String rcmcpReason;

    //创始人
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

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
