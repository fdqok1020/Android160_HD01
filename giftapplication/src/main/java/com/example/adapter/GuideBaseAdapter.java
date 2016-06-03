package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Guide;
import com.example.httputils.bitmap.ImageLoader;
import com.example.qianfeng.giftapplication.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class GuideBaseAdapter extends AbsBaseAdapter<Guide.DataBean.ItemsBean> {
    public GuideBaseAdapter(Context context, List<Guide.DataBean.ItemsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.home_child_item_view,null);
            holder.ivShow = (ImageView) convertView.findViewById(R.id.child_item_img);
            holder.tvLike = (TextView) convertView.findViewById(R.id.tv_child_like);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //数据映射
        String cover_image_url = getData().get(position).getCover_image_url();
        holder.ivShow.setTag(cover_image_url);
        ImageLoader.loadImage(context,cover_image_url,holder.ivShow);
        holder.tvLike.setText(String.valueOf(getData().get(position).getLikes_count()));
        holder.tvTitle.setText(getData().get(position).getTitle());

        return convertView;
    }
         class ViewHolder{
             private ImageView ivShow;
             private TextView tvLike;
             private TextView tvTitle;
         }
}
