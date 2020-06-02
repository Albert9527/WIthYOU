package com.zcz1024.withyou.entity;


public class Follow {

    private String followId;

    private String userId;

    private String targetId;

    private Integer istwoway;

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Integer getIstwoway() {
        return istwoway;
    }

    public void setIstwoway(Integer istwoway) {
        this.istwoway = istwoway;
    }
}
