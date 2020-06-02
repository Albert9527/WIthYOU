package com.zcz1024.withyou.pojoVO;

import java.io.Serializable;

public class VideoVo implements Serializable {

    /**
     * rcmvdId : rcmvideos-b3d7b9680be64bc399ed47b1d09bcc2e
     * rcmvdTitle : bug崛起
     * firstPictrue : 0e00acf0-296f-4bb4-a67e-92c3dc5d963c.jpg
     * rcmvdIntro : 测试数据
     * watchAddress : 腾讯视频
     * userId : 1
     * releaseTime : 2020-04-27
     * director : test
     * mainPerforme : albert
     * categoryTag : 1
     * rcmReason : 无
     * praiseCount : 0
     * upPic : null
     */

    private String rcmvdId;
    private String rcmvdTitle;
    private String firstPictrue;
    private String rcmvdIntro;
    private String watchAddress;
    private String userId;
    private String releaseTime;
    private String director;
    private String mainPerforme;
    private String categoryTag;
    private String rcmReason;
    private int praiseCount;
    private Object upPic;

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

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Object getUpPic() {
        return upPic;
    }

    public void setUpPic(Object upPic) {
        this.upPic = upPic;
    }
}
