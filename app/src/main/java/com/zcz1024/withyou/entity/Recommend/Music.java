package com.zcz1024.withyou.entity.Recommend;



import java.sql.Date;

import okhttp3.MultipartBody;

public class Music {

    private String rcmMusicId;

    private String rcmMusicName;

    private String firstPictrue;

    private String playAddress;

    private String userId;

    private String singer;

    private Date createTime;

    private String rcmMusicIntro;

    private String categoryTag;

    private String rcmReason;

    private Integer praiseCount;

    private MultipartBody upPic;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
