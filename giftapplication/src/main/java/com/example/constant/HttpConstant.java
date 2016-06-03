package com.example.constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class HttpConstant {
    //头部的图片请求地址
    public static final String BANNERS_PATH = "http://api.liwushuo.com/v2/banners";
    public static final String HEAD_LIST_PATH = "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=1";
    public static final String HOTPRODUCT_PATH="http://api.liwushuo.com/v2/items?gender=1&limit=20&offset=0&generation=2";
    public static final String GUIDE_PRODUCT_PATH="http://api.liwushuo.com/v2/channels/101/items?gender=1&limit=20&offset=0&generation=2";
    public static List<UrlInfo> urlInfos = new ArrayList<>();

    static {
        urlInfos.add(new UrlInfo(101, "精选", "http://api.liwushuo.com/v2/channels/" + 101 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(129, "海淘", "http://api.liwushuo.com/v2/channels/" + 129 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(125, "创意生活", "http://api.liwushuo.com/v2/channels/" + 125 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(10, "送女票", "http://api.liwushuo.com/v2/channels/" + 10 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(28, "科技苑", "http://api.liwushuo.com/v2/channels/" + 28 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(6, "送爸妈", "http://api.liwushuo.com/v2/channels/" + 6 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(26, "送基友", "http://api.liwushuo.com/v2/channels/" + 26 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(17, "送同事", "http://api.liwushuo.com/v2/channels/" + 17 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(24, "送宝贝", "http://api.liwushuo.com/v2/channels/" + 24 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(127, "设计感", "http://api.liwushuo.com/v2/channels/" + 127 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(14, "文艺风", "http://api.liwushuo.com/v2/channels/" + 14 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(126, "奇葩搞怪", "http://api.liwushuo.com/v2/channels/" + 126 + "/items?gender=1&limit=20&offset=0&generation=2"));
        urlInfos.add(new UrlInfo(11, "萌萌哒", "http://api.liwushuo.com/v2/channels/" + 11 + "/items?gender=1&limit=20&offset=0&generation=2"));


    }


    public static class UrlInfo implements Serializable {
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
