package com.zhuolang.starryserver.entity;

public class PublishImage {
    private int id;
    private int publicId;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublicId() {
        return publicId;
    }

    public void setPublicId(int publicId) {
        this.publicId = publicId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "PublishImage{" +
                "id=" + id +
                ", publicId=" + publicId +
                ", path='" + path + '\'' +
                '}';
    }
}
