package com.zcz1024.withyou.entity.Recommend;


import okhttp3.MultipartBody;

public class Videos {

    private String rcmvdId;

    //名称
    private String rcmvdTitle;

    //首图
    private String firstPictrue;

    //简介
    private String rcmvdIntro;

    //点映地址
    private String watchAddress;

    //发布者
    private String userId;

    //上映时间
    private String releaseTime;

    //导演
    private String director;

    //主演
    private String mainPerforme;

    //分类标签
    private String categoryTag;

    //推荐理由
    private String rcmReason;

    //点赞数
    private Integer praiseCount;

    private MultipartBody upPic;

    public String getRcmvdId() {
        return rcmvdId;
    }

    public void setRcmvdId(String rcmvdId) {
        this.rcmvdId = rcmvdId;
    }

    public String getRcmvdTitle() {
        return rcmvdTitle;
    }

    public void setRcmvdTitle(String rcmvdTitle) {
        this.rcmvdTitle = rcmvdTitle;
    }

    public String getFirstPictrue() {
        return firstPictrue;
    }

    public void setFirstPictrue(String firstPictrue) {
        this.firstPictrue = firstPictrue;
    }

    public String getRcmvdIntro() {
        return rcmvdIntro;
    }

    public void setRcmvdIntro(String rcmvdIntro) {
        this.rcmvdIntro = rcmvdIntro;
    }

    public String getWatchAddress() {
        return watchAddress;
    }

    public void setWatchAddress(String watchAddress) {
        this.watchAddress = watchAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainPerforme() {
        return mainPerforme;
    }

    public void setMainPerforme(String mainPerforme) {
        this.mainPerforme = mainPerforme;
    }

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }

    public String getRcmReason() {
        return rcmReason;
    }

    public void setRcmReason(String rcmReason) {
        this.rcmReason = rcmReason;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public MultipartBody getUpPic() {
        return upPic;
    }

    public void setUpPic(MultipartBody upPic) {
        this.upPic = upPic;
    }
}
