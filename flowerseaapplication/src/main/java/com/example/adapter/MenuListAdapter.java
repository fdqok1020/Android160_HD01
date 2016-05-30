package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Category;
import com.example.qianfeng.R;
import com.example.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MenuListAdapter extends AbsBaseAdapter<Category.DataBean.CategoriesBean> {
    public MenuListAdapter(Context context, List<Category.DataBean.CategoriesBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_menulist,null);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //数据映射
        holder.tvTitle.setText(getData().get(position).getName());
        holder.ivPic.setTag(getData().get(position).getIcon());
        holder.ivPic.setImageResource(R.drawable.placeholder_logo);
        ImageLoader.loaderImage(getData().get(position).getIcon(),holder.ivPic);
        return convertView;
    }

    public class ViewHolder{
        ImageView ivPic ;
        TextView tvTitle;
    }

}
