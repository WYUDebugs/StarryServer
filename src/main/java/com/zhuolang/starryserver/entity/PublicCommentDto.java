package com.zhuolang.starryserver.entity;

import java.util.Date;

public class PublicCommentDto {

    private int id;
    private int commentator;
    private User cUser;
    private int receiveId;
    private User rUser;
    private int publicId;
    private String  commentTime;
    private String commentContent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentator() {
        return commentator;
    }

    public void setCommentator(int commentator) {
        this.commentator = commentator;
    }

    public User getcUser() {
        return cUser;
    }

    public void setcUser(User cUser) {
        this.cUser = cUser;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public User getrUser() {
        return rUser;
    }

    public void setrUser(User rUser) {
        this.rUser = rUser;
    }

    public int getPublicId() {
        return publicId;
    }

    public void setPublicId(int publicId) {
        this.publicId = publicId;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
