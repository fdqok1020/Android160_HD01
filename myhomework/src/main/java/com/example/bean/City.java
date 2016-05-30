package com.example.bean;

/**
 * Created by Administrator on 2016/5/4.
 */
public class City {


    private String city;
    private String temp;
    private String WD;
    private String SD;
    private String time;
    private String njd;

    public City(String city, String njd, String temp, String WD, String SD, String time) {
        this.city = city;
        this.njd = njd;
        this.temp = temp;
        this.WD = WD;
        this.SD = SD;
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
