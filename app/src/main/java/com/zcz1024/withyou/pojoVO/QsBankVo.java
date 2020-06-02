package com.zcz1024.withyou.pojoVO;

public class QsBankVo {

    /**
     * qsId : qsbank-d2f7836456b449a7858510fc6d8286b8
     * qsName : 贝克抑郁自评量表
     * qsIntro : 贝克抑郁自评量表（Beck Depression Inventory）是专门评测抑郁程度的。整个量表包括下面21组项目，每组有4句陈述，每句之前标有的阿拉伯数字为等级分。可根据一周来的感觉，把最适合自己情况的一句话前面的数字圈出来。全部21组都做完后，将各组的圈定分数相加，便得到总分。依据总分，就能明白无误地了解自己是否有抑郁，抑郁的程度如何。
     * qsPictrue : 0d753911-bf3e-47d3-aacd-e16cf4fcfdee.jpg
     * qsCreateTime : 2020-04-29
     * qsTotal : 21
     * qsOptionScore : 1
     * upPic : null
     */

    private String qsId;
    private String qsName;
    private String qsIntro;
    private String qsPictrue;
    private String qsCreateTime;
    private int qsTotal;
    private int qsOptionScore;
    private Object upPic;

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

    public String getQsCreateTime() {
        return qsCreateTime;
    }

    public void setQsCreateTime(String qsCreateTime) {
        this.qsCreateTime = qsCreateTime;
    }

    public int getQsTotal() {
        return qsTotal;
    }

    public void setQsTotal(int qsTotal) {
        this.qsTotal = qsTotal;
    }

    public int getQsOptionScore() {
        return qsOptionScore;
    }

    public void setQsOptionScore(int qsOptionScore) {
        this.qsOptionScore = qsOptionScore;
    }

    public Object getUpPic() {
        return upPic;
    }

    public void setUpPic(Object upPic) {
        this.upPic = upPic;
    }
}
