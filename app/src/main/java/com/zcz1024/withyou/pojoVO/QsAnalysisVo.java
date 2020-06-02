package com.zcz1024.withyou.pojoVO;

import java.io.Serializable;

public class QsAnalysisVo implements Serializable {

    /**
     * sectionLeft : 0
     * sectionRight : 20
     * qsId : qsbank-d2f7836456b449a7858510fc6d8286b8
     * alysContent : 状态正常
     * alysProposal : 当前无明显抑郁状态，继续保持良好的心态和规律生活
     */

    private String sectionLeft;
    private String sectionRight;
    private String qsId;
    private String alysContent;
    private String alysProposal;
    private Integer socre;


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

    public Integer getSocre() {
        return socre;
    }

    public void setSocre(Integer socre) {
        this.socre = socre;
    }
}
