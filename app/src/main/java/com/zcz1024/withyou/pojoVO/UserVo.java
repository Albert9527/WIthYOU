package com.zcz1024.withyou.pojoVO;

public class UserVo {


    /**
     * id : 1
     * nickname : albert
     * userId : 1
     * intro : 管理员
     * avatar : 49eed3ac-2e09-4cad-baf8-7aebbfa95012.jpg
     * createTime : 2020-04-22
     * location : 翻斗街
     */

    private String id;
    private String nickname;
    private String userId;
    private String intro;
    private String avatar;
    private String createTime;
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
