package com.zhaoyani.myapp.ui.activity;

public class BannerDataInfo {
    private int img;
    private String title;

    public BannerDataInfo(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public BannerDataInfo() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BannerDataInfo{" +
                "img=" + img +
                ", title='" + title + '\'' +
                '}';
    }
}
