package com.example.bean;

/**
 * Created by Administrator on 2016/5/28.
 */
public class Diary {
    private String content;
    private int id;

    public Diary() {
    }

    public Diary(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
