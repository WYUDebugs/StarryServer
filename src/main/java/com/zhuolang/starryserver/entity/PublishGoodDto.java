package com.zhuolang.starryserver.entity;

public class PublishGoodDto {
    private int gdId;
    private int publishId;
    private int manOfPraise;
    private String timeOfPraise;
    private User user;

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

    public String getTimeOfPraise() {
        return timeOfPraise;
    }

    public void setTimeOfPraise(String timeOfPraise) {
        this.timeOfPraise = timeOfPraise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PublishGoodDto{" +
                "gdId=" + gdId +
                ", publishId=" + publishId +
                ", manOfPraise=" + manOfPraise +
                ", timeOfPraise='" + timeOfPraise + '\'' +
                ", user=" + user +
                '}';
    }
}
