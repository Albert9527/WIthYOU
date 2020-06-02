package com.zcz1024.withyou.pojoVO;

public class MyapplyVo {

    private String actname;
    private String createTime;
    private String state;
    private String reason;

    public String getActname() {
        return actname;
    }

    public void setActname(String actname) {
        this.actname = actname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "MyapplyVo{" +
                "actname='" + actname + '\'' +
                ", createTime='" + createTime + '\'' +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
