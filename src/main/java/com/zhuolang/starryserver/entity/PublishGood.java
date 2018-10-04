package com.zhuolang.starryserver.entity;

import java.util.Date;

/**
 * Created by HuangMingPan on 2018/9/03.
 */
public class PublishGood {
    private int gdId;
    private int publishId;
    private int manOfPraise;
    private Date timeOfPraise;
    private int state;

    public int getGdId() {
        return gdId;
    }

    public void setGdId(int gdId) {
        this.gdId = gdId;
    }

    public int getPublishId() {
        return publishId;
    }

    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }

    public int getManOfPraise() {
        return manOfPraise;
    }

    public void setManOfPraise(int manOfPraise) {
        this.manOfPraise = manOfPraise;
    }

    public Date getTimeOfPraise() {
        return timeOfPraise;
    }

    public void setTimeOfPraise(Date timeOfPraise) {
        this.timeOfPraise = timeOfPraise;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "PublishGood{" +
                "gdId=" + gdId +
                ", publishId=" + publishId +
                ", manOfPraise=" + manOfPraise +
                ", timeOfPraise=" + timeOfPraise +
                ", state=" + state +
                '}';
    }
}
