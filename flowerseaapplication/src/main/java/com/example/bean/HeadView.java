package com.example.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/21.
 */
public class HeadView {

    /**
     * code : 0
     * data : {"topics":[{"desc":"","id":11,"image":"http://ec-img.ufile.ucloud.cn/68749ac5660246cca2977e6964be55ed.jpg","title":"现货专区"},{"desc":"","id":8,"image":"http://ec-img.ufile.ucloud.cn/650833bc0c194f889f6bd1cc694a7ca3.jpg","title":"日本妈妈都在用它们"},{"desc":"","id":10,"image":"http://ec-img.ufile.ucloud.cn/9e8840a847a94fc78379482e8ccced0f.jpg","title":"给宝宝的，只有直邮才放心"},{"desc":"1941年，COACH诞生于纽约曼哈顿，秉承近75年来精湛手工工艺及独具匠心的创造力，融合美国悠久文化与历史，不断钻研并致力于打造纽约现代奢华风尚。产品系列包括手袋、钱包、服饰、鞋履、配饰等。COACH专注细节，精益求精的设计理念，无不彰显美国高端生活时尚。","id":9,"image":"http://ec-img.ufile.ucloud.cn/d14f03a4629649c9ab41ad9a9648d60c.jpg","title":"买COACH，不用去专柜"},{"desc":null,"id":7,"image":"http://ec-img.ufile.ucloud.cn/e035d0f062584baf959713635de78880.jpg","title":"吃土也要败的女神爱用物"},{"desc":null,"id":1,"image":"http://ec-img.ufile.ucloud.cn/ac4f8096ae0146c88803390c2b7f0c66.png","title":"护肤保湿"},{"desc":null,"id":2,"image":"http://ec-img.ufile.ucloud.cn/10fbe715e91d44fa89b9da673c103cd1.jpg","title":"花王-乐而雅"},{"desc":null,"id":4,"image":"http://ec-img.ufile.ucloud.cn/d0e1543ac9104bdfb4ed8dead9df003c.jpg","title":"Thermos保温杯"}]}
     * success : true
     */

    private int code;
    private DataBean data;
    private boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * desc :
         * id : 11
         * image : http://ec-img.ufile.ucloud.cn/68749ac5660246cca2977e6964be55ed.jpg
         * title : 现货专区
         */

        private List<TopicsBean> topics;

        public List<TopicsBean> getTopics() {
            return topics;
        }

        public void setTopics(List<TopicsBean> topics) {
            this.topics = topics;
        }

        public static class TopicsBean {
            private String desc;
            private int id;
            private String image;
            private String title;

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
