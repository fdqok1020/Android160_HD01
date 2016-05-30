package com.example.bean;

/**
 * Created by Administrator on 2016/5/4.
 */
public class Joke {
    private   String digest;
    private  String  source;
    private  String title;

    public Joke(String digest, String source, String title) {
        this.digest = digest;
        this.source = source;
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
