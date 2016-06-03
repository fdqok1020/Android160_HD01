package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.HotProduct;
import com.example.qianfeng.giftapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public class HotGridViewAdapter extends AbsBaseAdapter<HotProduct.DataBean.ItemsBean> {
    public HotGridViewAdapter(Context context, List<HotProduct.DataBean.ItemsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView=inflater.inflate(R.layout.hot_grid_view_item,null);
            holder.ivShow = (ImageView) convertView.findViewById(R.id.card_imageview);
            holder.tvName = (TextView) convertView.findViewById(R.id.card_name);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.card_price);
            holder.tvLikes = (TextView) convertView.findViewById(R.id.card_favourite);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        HotProduct.DataBean.ItemsBean.ProductBean product = data.get(position).getProduct();
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice());
        Picasso.with(context).load(product.getCover_image_url()).into(holder.ivShow);
        holder.tvLikes.setText(String.valueOf(product.getFavorites_count()));

        return convertView;
    }
    class ViewHolder{
        private ImageView ivShow;
        private TextView tvPrice;
        private  TextView tvName;
        private TextView tvLikes;
    }
}
