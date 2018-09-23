package com.zhuolang.starryserver.entity;

import java.util.Date;

/**
 * Created by HuangMingPan on 2018/9/03.
 */
public class PublishGood {
    private int id;
    private int publishId;
    private int manOfPraise;
    private Date timeOfPraise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "PublishGood{" +
                "id=" + id +
                ", publishId=" + publishId +
                ", manOfPraise=" + manOfPraise +
                ", timeOfPraise=" + timeOfPraise +
                '}';
    }
}
