package com.zcz1024.withyou.entity.Recommend;

import java.sql.Date;

import okhttp3.MultipartBody;

public class Book {

    private String rcmbookId;

    //书名
    private String rcmbookName;

    //首图地址
    private String firstPictrue;

    //作者
    private String rcmbookAuthor;

    //出版社
    private String rcmbookPress;

    //简介
    private String rcmbookIntro;

    //推荐理由
    private String rcmbookReason;

    //分类标签
    private String categoryTag;

    //发布者ID
    private String userId;

    //点赞数
    private Integer praiseCount;

    //出版日期
    private Date pubDate;

    //首图文件
    private MultipartBody upPic;

    public String getRcmbookId() {
        return rcmbookId;
    }

    public void setRcmbookId(String rcmbookId) {
        this.rcmbookId = rcmbookId;
    }

    public String getRcmbookName() {
        return rcmbookName;
    }

    public void setRcmbookName(String rcmbookName) {
        this.rcmbookName = rcmbookName;
    }

    public String getFirstPictrue() {
        return firstPictrue;
    }

    public void setFirstPictrue(String firstPictrue) {
        this.firstPictrue = firstPictrue;
    }

    public String getRcmbookAuthor() {
        return rcmbookAuthor;
    }

    public void setRcmbookAuthor(String rcmbookAuthor) {
        this.rcmbookAuthor = rcmbookAuthor;
    }

    public String getRcmbookPress() {
        return rcmbookPress;
    }

    public void setRcmbookPress(String rcmbookPress) {
        this.rcmbookPress = rcmbookPress;
    }

    public String getRcmbookIntro() {
        return rcmbookIntro;
    }

    public void setRcmbookIntro(String rcmbookIntro) {
        this.rcmbookIntro = rcmbookIntro;
    }

    public String getRcmbookReason() {
        return rcmbookReason;
    }

    public void setRcmbookReason(String rcmbookReason) {
        this.rcmbookReason = rcmbookReason;
    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public MultipartBody getUpPic() {
        return upPic;
    }

    public void setUpPic(MultipartBody upPic) {
        this.upPic = upPic;
    }
}
