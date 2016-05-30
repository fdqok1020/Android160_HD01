package com.example.constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class Constant {

    public static List<UrlInfo> urlInfos=new ArrayList<>();

    public static final String DISCOVERY_PATH="http://api.litchi.jstv.com:800/discovery";

    static {
        urlInfos.add(new UrlInfo(0,"头条","http://api.litchi.jstv.com:800/nav/14639?OrderIndex=0&val=52DA75CB95F5E32B08B5E8042423FE63"));
        urlInfos.add(new UrlInfo(1,"国内","http://api.litchi.jstv.com:800/nav/14647?OrderIndex=0&val=DB25A2FEC60A5A89527417A5DC695669"));
        urlInfos.add(new UrlInfo(2,"社会","http://api.litchi.jstv.com:800/nav/14649?OrderIndex=0&val=27794748501B1F0289BC70044CA2DE05"));
        urlInfos.add(new UrlInfo(3,"体育","http://api.litchi.jstv.com:800/nav/14651?OrderIndex=0&val=E00B7FD925E49AFEF3895755E5C6F454"));
        urlInfos.add(new UrlInfo(4,"科技","http://api.litchi.jstv.com:800/nav/14654?OrderIndex=0&val=2FE076EE3F8F46B8EF617721F132AE37"));
        urlInfos.add(new UrlInfo(5,"真相帝","http://api.litchi.jstv.com:800/nav/16180?OrderIndex=0&val=0358B067B48223A508DB7A540EF94CE6"));
    }

    public static class UrlInfo implements Serializable
    {
        private int id;
        private String title;
        private String url;

        public UrlInfo(int id, String title, String url) {
            this.id = id;
            this.title = title;
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
