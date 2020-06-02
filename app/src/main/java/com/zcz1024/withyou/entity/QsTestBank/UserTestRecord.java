package com.zcz1024.withyou.entity.QsTestBank;


import java.io.Serializable;
import java.sql.Date;

public class UserTestRecord implements Serializable {

    private String trId;

    private String userId;

    private Integer trScore;

    private String trAnswer;

    private String qsId;

    private Date testTime;

    public String getTrId() {
        return trId;
    }

    public void setTrId(String trId) {
        this.trId = trId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTrScore() {
        return trScore;
    }

    public void setTrScore(Integer trScore) {
        this.trScore = trScore;
    }

    public String getTrAnswer() {
        return trAnswer;
    }

    public void setTrAnswer(String trAnswer) {
        this.trAnswer = trAnswer;
    }

    public String getQsId() {
        return qsId;
    }

    public void setQsId(String qsId) {
        this.qsId = qsId;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "UserTestRecord{" +
                "trId='" + trId + '\'' +
                ", userId='" + userId + '\'' +
                ", trScore=" + trScore +
                ", trAnswer='" + trAnswer + '\'' +
                ", qsId='" + qsId + '\'' +
                ", testTime=" + testTime +
                '}';
    }
}
