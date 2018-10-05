package com.zhuolang.starryserver.entity;

import java.util.List;

public class Moment {
    private int id;
    private int sender;
    private  String send_time;
    private  String location_time;
    private  String content;
    private int bookId;
    private List<MomentImage> imageList;
    private User user;
    private List<MomentComment> commentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getLocation_time() {
        return location_time;
    }

    public void setLocation_time(String location_time) {
        this.location_time = location_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public List<MomentImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<MomentImage> imageList) {
        this.imageList = imageList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MomentComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<MomentComment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", sender=" + sender +
                ", send_time='" + send_time + '\'' +
                ", location_time='" + location_time + '\'' +
                ", content='" + content + '\'' +
                ", bookId=" + bookId +
                ", imageList=" + imageList +
                ", user=" + user +
                ", commentList=" + commentList +
                '}';
    }
}
