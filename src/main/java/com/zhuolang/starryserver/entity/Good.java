package com.zhuolang.starryserver.entity;

public class Good {
    private int manOfPraise;
    private int timeOfPraise;

    public int getManOfPraise() {
        return manOfPraise;
    }

    public void setManOfPraise(int manOfPraise) {
        this.manOfPraise = manOfPraise;
    }

    public int getTimeOfPraise() {
        return timeOfPraise;
    }

    public void setTimeOfPraise(int timeOfPraise) {
        this.timeOfPraise = timeOfPraise;
    }

    @Override
    public String toString() {
        return "Good{" +
                "manOfPraise=" + manOfPraise +
                ", timeOfPraise=" + timeOfPraise +
                '}';
    }
}
