package com.example.bean;

/**
 * Created by Administrator on 2016/5/6.
 */
public class DiscoveryList {
    private  String photo;
    private String href;

    public DiscoveryList(String photo, String href) {
        this.photo = photo;
        this.href = href;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
