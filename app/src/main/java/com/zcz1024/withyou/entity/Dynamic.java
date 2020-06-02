package com.zcz1024.withyou.entity;


import java.sql.Date;

/**
 * 动态类
 * createby：zd
 */

public class Dynamic {

    //id
    private String dyId;

    //内容
    private String dyContent;

    //图片
    private String dyPictrue;

    //发布者
    private String userId;

    //发布时间
    private Date dyCreateTime;

    //分享来源
    private String dyShareId;

    //点赞数
    private Integer dyPraiseCount;

    //分享数
    private Integer dyShareCount;

    public String getDyId() {
        return dyId;
    }

    public void setDyId(String dyId) {
        this.dyId = dyId;
    }

    public String getDyContent() {
        return dyContent;
    }

    public void setDyContent(String dyContent) {
        this.dyContent = dyContent;
    }

    public String getDyPictrue() {
        return dyPictrue;
    }

    public void setDyPictrue(String dyPictrue) {
        this.dyPictrue = dyPictrue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDyCreateTime() {
        return dyCreateTime;
    }

    public void setDyCreateTime(Date dyCreateTime) {
        this.dyCreateTime = dyCreateTime;
    }

    public String getDyShareId() {
        return dyShareId;
    }

    public void setDyShareId(String dyShareId) {
        this.dyShareId = dyShareId;
    }

    public Integer getDyPraiseCount() {
        return dyPraiseCount;
    }

    public void setDyPraiseCount(Integer dyPraiseCount) {
        this.dyPraiseCount = dyPraiseCount;
    }

    public Integer getDyShareCount() {
        return dyShareCount;
    }

    public void setDyShareCount(Integer dyShareCount) {
        this.dyShareCount = dyShareCount;
    }
}
