package com.zcz1024.withyou.entity.QsTestBank;


import java.sql.Date;

import okhttp3.MultipartBody;

public class QsBank {

    private String qsId;

    private String qsName;

    private String qsIntro;

    private String qsPictrue;

    private Date qsCreateTime;

    private Integer qsTotal;

    private Integer qsOptionScore;


    private MultipartBody upPic;

    public String getQsId() {
        return qsId;
    }

    public void setQsId(String qsId) {
        this.qsId = qsId;
    }

    public String getQsName() {
        return qsName;
    }

    public void setQsName(String qsName) {
        this.qsName = qsName;
    }

    public String getQsIntro() {
        return qsIntro;
    }

    public void setQsIntro(String qsIntro) {
        this.qsIntro = qsIntro;
    }

    public String getQsPictrue() {
        return qsPictrue;
    }

    public void setQsPictrue(String qsPictrue) {
        this.qsPictrue = qsPictrue;
    }

    public Date getQsCreateTime() {
        return qsCreateTime;
    }

    public void setQsCreateTime(Date qsCreateTime) {
        this.qsCreateTime = qsCreateTime;
    }

    public Integer getQsTotal() {
        return qsTotal;
    }

    public void setQsTotal(Integer qsTotal) {
        this.qsTotal = qsTotal;
    }

    public Integer getQsOptionScore() {
        return qsOptionScore;
    }

    public void setQsOptionScore(Integer qsOptionScore) {
        this.qsOptionScore = qsOptionScore;
    }

    public MultipartBody getUpPic() {
        return upPic;
    }

    public void setUpPic(MultipartBody upPic) {
        this.upPic = upPic;
    }
}
