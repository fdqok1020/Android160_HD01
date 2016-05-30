package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.AbsBaseAdapter;
import com.example.app.R;
import com.example.bean.News;
import com.example.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/5/8.
 */
public class NewsAdapter extends AbsBaseAdapter<News.NewsBean.ListBean> {

    //设置常量

    //普通+独家
    public static final int   TYPE_1=0;
    //专题
    public static final int   TYPE_2=1;
    //一张大图
    public static final int   TYPE_3=2;
    //三张图
    public static final int   TYPE_4=3;


    public NewsAdapter(Context context, List<News.NewsBean.ListBean> data) {
        super(context, data);
    }


    //获取该item是什么类型的数据
    @Override
    public int getItemViewType(int position) {
        News.NewsBean.ListBean news = getData().get(position);
        //得到json数据中的 type值
       int articleType = news.getArticleType();
        int  type= 0;
        switch (articleType){
            case 10://普通+独家
                type=TYPE_1;
                break;
            case 7://专题
                type=TYPE_2;
                break;
            case 1://1张三图
                type=TYPE_3;
                break;
            case 14:
                type=TYPE_4;
                break;
        }
        return  type;
    }


        //确定这个fragment中的item一共有多少种布局
    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       int type = getItemViewType(position);
        ViewHolderComm comm = null;
        ViewHolderSingle single = null;
        ViewHolderImg1 img1 = null;
        ViewHolderImg3 img3 = null;
        if(convertView == null){
            switch (type){
                case TYPE_1:
                    comm = new ViewHolderComm();
                    convertView = inflater.inflate(R.layout.ltem_listview_comm_news,null);
                    comm.iv = (ImageView) convertView.findViewById(R.id.iv_pic);
                    comm.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    comm.tvContent= (TextView) convertView.findViewById(R.id.tv_content);
                    comm.tvType = (TextView) convertView.findViewById(R.id.tv_type);
                    convertView.setTag(comm);
                    break;
                case TYPE_2:
                    single = new ViewHolderSingle();
                    convertView = inflater.inflate(R.layout.item_listview_news_single,null);
                    single.iv = (ImageView) convertView.findViewById(R.id.iv_pic);
                    single.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    single.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
                    convertView.setTag(single);
                    break;
                case TYPE_3:
                    img1 = new ViewHolderImg1();
                    convertView = inflater.inflate(R.layout.item_listview_news_img1,null);
                    img1.ivShow = (ImageView) convertView.findViewById(R.id.tv_show);
                    img1.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(img1);
                    break;
                case TYPE_4:
                    img3 = new ViewHolderImg3();
                    convertView = inflater.inflate(R.layout.ltem_listview_news_img3,null);
                    img3.ivLeft = (ImageView) convertView.findViewById(R.id.iv_left);
                    img3.ivRightTop = (ImageView) convertView.findViewById(R.id.iv_righttop);
                    img3.ivRightBottom = (ImageView) convertView.findViewById(R.id.iv_rightbottom);
                    img3.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                    convertView.setTag(img3);
                    break;
            }
        }else{
            switch (type){
                case TYPE_1:
                    comm = (ViewHolderComm) convertView.getTag();
                    break;
                case TYPE_2:
                    single = (ViewHolderSingle) convertView.getTag();
                    break;
                case TYPE_3:
                    img1 = (ViewHolderImg1) convertView.getTag();
                    break;
                case TYPE_4:
                    img3 = (ViewHolderImg3) convertView.getTag();
                    break;
            }
        }
        //数据的映射
        News.NewsBean.ListBean news = getData().get(position);
        switch(type){
            case TYPE_1:
                ImageLoader.loaderImage(news.getData().getPhoto(),comm.iv);
                comm.tvTitle.setText(news.getData().getTitle());
                comm.tvContent.setText(news.getData().getSummary());
                if("".equals(news.getData().getFooter())){
                    //当type没有内容的时候 将tvType隐藏掉
                    comm.tvType.setVisibility(View.INVISIBLE);
                }else{
                    //当getFooter（）中有内容的时候将tvType显示出来
                    comm.tvType.setVisibility(View.VISIBLE);
                    comm.tvType.setText(news.getData().getFooter());
                }
                break;
            case TYPE_2:
                ImageLoader.loaderImage(news.getData().getPhoto(),single.iv);
                single.tvTitle.setText(news.getData().getTitle());
                single.tvContent.setText(news.getData().getSummary());
                break;
            case TYPE_3:
                ImageLoader.loaderImage(news.getData().getPhoto(),img1.ivShow);
                img1.tvTitle.setText(news.getData().getTitle());
                break;
            case TYPE_4:
                ImageLoader.loaderImage(news.getData().getPhoto(),img3.ivLeft);
                ImageLoader.loaderImage(news.getData().getPhoto_s1(),img3.ivRightTop);
                ImageLoader.loaderImage(news.getData().getPhoto_s2(),img3.ivRightBottom);
                img3.tvTitle.setText(news.getData().getTitle());
                break;
        }
        return convertView;
    }

    //普通新闻和独家新闻 item_news_comm

    public static class ViewHolderComm {
        ImageView iv;
        TextView tvTitle;
        TextView tvContent;
        TextView tvType;

    }

    //独家新闻 item_news_single
    public static class ViewHolderSingle {
        ImageView iv;
        TextView tvTitle;
        TextView tvContent;

    }

    //一张大图  item_news_img1
    public static class ViewHolderImg1 {
        ImageView ivShow;
        TextView tvTitle;
    }

    //三张图片 item_news_img3
    public static class ViewHolderImg3 {
        ImageView ivLeft;
        ImageView ivRightTop;
        ImageView ivRightBottom;
        TextView tvTitle;

    }
}
