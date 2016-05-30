package com.example.app.constast.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/30.
 */
public class Diary implements Serializable{
    private  String title;
    private  String content;
    private  int id;


    public Diary(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Diary(String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
