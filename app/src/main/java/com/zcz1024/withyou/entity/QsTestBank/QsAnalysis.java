package com.zcz1024.withyou.entity.QsTestBank;


public class QsAnalysis {

    //评分左区间

    private String sectionLeft;

    //评分右区间

    private String sectionRight;


    private String qsId;

    //测试结果
    private String alysContent;

    //建议
    private String alysProposal;

    public String getSectionLeft() {
        return sectionLeft;
    }

    public void setSectionLeft(String sectionLeft) {
        this.sectionLeft = sectionLeft;
    }

    public String getSectionRight() {
        return sectionRight;
    }

    public void setSectionRight(String sectionRight) {
        this.sectionRight = sectionRight;
    }

    public String getQsId() {
        return qsId;
    }

    public void setQsId(String qsId) {
        this.qsId = qsId;
    }

    public String getAlysContent() {
        return alysContent;
    }

    public void setAlysContent(String alysContent) {
        this.alysContent = alysContent;
    }

    public String getAlysProposal() {
        return alysProposal;
    }

    public void setAlysProposal(String alysProposal) {
        this.alysProposal = alysProposal;
    }
}
