package com.example.bean;

/**
 * Created by Administrator on 2016/5/5.
 */
public class News {
    private String title;
    private String source;
    private String firstImg;


    public News(String title, String source, String firstImg) {
        this.title = title;
        this.source = source;
        this.firstImg = firstImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFirstImg() {
        return firstImg;
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }
}
