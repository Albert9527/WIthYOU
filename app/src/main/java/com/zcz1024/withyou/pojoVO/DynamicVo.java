package com.zcz1024.withyou.pojoVO;

public class DynamicVo {

    /**
     * dyId : 4
     * dyContent : 第四条测试数据
     * dyPictrue : null
     * userId : acount-4892bda7345444329b8a919e7895349e
     * dyCreateTime : 2020-05-09
     * dyShareId : null
     * dyPraiseCount : 0
     * dyShareCount : 0
     * nickname : albert
     * useravatar : a5cc54dc-a465-4ef3-97d3-f8a0e330b380.jpg
     */

    private String dyId;
    private String dyContent;
    private String  dyPictrue;
    private String userId;
    private String dyCreateTime;
    private String dyShareId;
    private int dyPraiseCount;
    private int dyShareCount;
    private String nickname;
    private String useravatar;

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

    public Object getDyPictrue() {
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

    public String getDyCreateTime() {
        return dyCreateTime;
    }

    public void setDyCreateTime(String dyCreateTime) {
        this.dyCreateTime = dyCreateTime;
    }

    public Object getDyShareId() {
        return dyShareId;
    }

    public void setDyShareId(String dyShareId) {
        this.dyShareId = dyShareId;
    }

    public int getDyPraiseCount() {
        return dyPraiseCount;
    }

    public void setDyPraiseCount(int dyPraiseCount) {
        this.dyPraiseCount = dyPraiseCount;
    }

    public int getDyShareCount() {
        return dyShareCount;
    }

    public void setDyShareCount(int dyShareCount) {
        this.dyShareCount = dyShareCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }
}

