package com.zcz1024.withyou.entity;


public class Menber {
    //成员表ID
    private String menberId;

    //活动ID
    private String actId;

    //参与者ID
    private String userId;

    //参与者角色
    private Integer menberRole;

    public String getMenberId() {
        return menberId;
    }

    public void setMenberId(String menberId) {
        this.menberId = menberId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMenberRole() {
        return menberRole;
    }

    public void setMenberRole(Integer menberRole) {
        this.menberRole = menberRole;
    }

    @Override
    public String toString() {
        return "Menber{" +
                "menberId='" + menberId + '\'' +
                ", actId='" + actId + '\'' +
                ", userId='" + userId + '\'' +
                ", menberRole=" + menberRole +
                '}';
    }
}
