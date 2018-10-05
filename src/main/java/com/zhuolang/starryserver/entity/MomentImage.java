package com.zhuolang.starryserver.entity;

public class MomentImage {
    private int imageId;
    private int moment_id;
    private String path;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getMoment_id() {
        return moment_id;
    }

    public void setMoment_id(int moment_id) {
        this.moment_id = moment_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "MomentImage{" +
                "imageId=" + imageId +
                ", moment_id=" + moment_id +
                ", path='" + path + '\'' +
                '}';
    }
}
