package com.zcz1024.withyou.pojoVO;

import java.io.Serializable;

public class MusicVo implements Serializable {

    /**
     * rcmMusicId : rcmmusic-c5d54c5ae28b4a5fab56b93fd5335ba8
     * rcmMusicName : alala
     * firstPictrue : e95b3259-9eb9-44e2-accc-de686e57a40e.png
     * playAddress : wyyun
     * userId : 1
     * singer : mdn
     * createTime : null
     * rcmMusicIntro : 这是一条测试数据
     * categoryTag : 1
     * rcmReason : 测试音乐推荐的添加功能
     * praiseCount : 0
     * upPic : null
     */

    private String rcmMusicId;
    private String rcmMusicName;
    private String firstPictrue;
    private String playAddress;
    private String userId;
    private String singer;
    private String createTime;
    private String rcmMusicIntro;
    private String categoryTag;
    private String rcmReason;
    private int praiseCount;
    private String upPic;

    public String getRcmMusicId() {
        return rcmMusicId;
    }

    public void setRcmMusicId(String rcmMusicId) {
        this.rcmMusicId = rcmMusicId;
    }

    public String getRcmMusicName() {
        return rcmMusicName;
    }

    public void setRcmMusicName(String rcmMusicName) {
        this.rcmMusicName = rcmMusicName;
    }

    public String getFirstPictrue() {
        return firstPictrue;
    }

    public void setFirstPictrue(String firstPictrue) {
        this.firstPictrue = firstPictrue;
    }

    public String getPlayAddress() {
        return playAddress;
    }

    public void setPlayAddress(String playAddress) {
        this.playAddress = playAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRcmMusicIntro() {
        return rcmMusicIntro;
    }

    public void setRcmMusicIntro(String rcmMusicIntro) {
        this.rcmMusicIntro = rcmMusicIntro;
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

    public String getUpPic() {
        return upPic;
    }

    public void setUpPic(String upPic) {
        this.upPic = upPic;
    }
}
