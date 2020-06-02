package com.zcz1024.withyou.pojoVO;

import java.io.Serializable;

public class BookTjVo implements Serializable {

    /**
     * rcmbookId : 1254356948909547522
     * rcmbookName : 测试1
     * firstPictrue :
     * rcmbookAuthor : albert
     * rcmbookPress : 程序员出版社
     * rcmbookIntro : 这是一条测试数据
     * rcmbookReason : 测试数据不需要理由
     * categoryTag : test
     * userId : 1
     * praiseCount : 1
     * pubDate : 2020-04-26
     * upPic : null
     */

    private String rcmbookId;
    private String rcmbookName;
    private String firstPictrue;
    private String rcmbookAuthor;
    private String rcmbookPress;
    private String rcmbookIntro;
    private String rcmbookReason;
    private String categoryTag;
    private String userId;
    private int praiseCount;
    private String pubDate;
    private Object upPic;

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

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Object getUpPic() {
        return upPic;
    }

    public void setUpPic(Object upPic) {
        this.upPic = upPic;
    }
}
