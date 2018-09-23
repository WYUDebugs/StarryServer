package com.zhuolang.starryserver.entity;

import java.util.Date;

public class PublicComment {

    private int cmId;
    private int commentator;
    private int receiveId;
    private int publicId;
    private String  commentTime;
    private String commentContent;

    public int getCmId() {
        return cmId;
    }

    public void setCmId(int cmId) {
        this.cmId = cmId;
    }

    public int getCommentator() {
        return commentator;
    }

    public void setCommentator(int commentator) {
        this.commentator = commentator;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
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

    @Override
    public String toString() {
        return "PublicComment{" +
                "cmId=" + cmId +
                ", commentator=" + commentator +
                ", receiveId=" + receiveId +
                ", publicId=" + publicId +
                ", commentTime='" + commentTime + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
