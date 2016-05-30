package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Product;
import com.example.qianfeng.R;
import com.example.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class FirstPageAdapter extends AbsBaseAdapter<Product.DataBean.ProductsBean> {
    private List<Product.DataBean.ProductsBean.ImagesBean> list = new ArrayList<>();
    private List<Product.DataBean.ProductsBean.CurrentSkuBean> list1 = new ArrayList<>();

    public FirstPageAdapter(Context context, List<Product.DataBean.ProductsBean> data) {
        super(context, data);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview, null);
            holder.img = (ImageView) convertView.findViewById(R.id.iv_img);
            holder.text = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvDiscount = (TextView) convertView.findViewById(R.id.tv_discount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        for (int i = 0; i < getData().size(); i++) {
            List<Product.DataBean.ProductsBean.ImagesBean> images = getData().get(i).getImages();
            Product.DataBean.ProductsBean.CurrentSkuBean current_sku = getData().get(i).getCurrent_sku();
            list1.add(current_sku);
            list.addAll(images);

        }
        //数据映射
        holder.img.setTag(list.get(position).getThumb());
        holder.img.setImageResource(R.drawable.placeholder_logo);
        ImageLoader.loaderImage(list.get(position).getThumb(), holder.img);
        int price =list1.get(position).getList_price().getCNY();
        int realPrice =list1.get(position).getReal_price().getCNY();
        holder.text.setText("¥  "+String.valueOf(realPrice/100)+"." +realPrice%100);
        holder.tvDiscount.setText(String.valueOf((realPrice*10)/price+"."+ ((realPrice*100)/price)%10)+"\n"+"折");

        return convertView;
    }

    public class ViewHolder {
        private ImageView img;
        private TextView text;
        private TextView tvDiscount;
    }
}
