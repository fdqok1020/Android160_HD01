package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.bean.ProductDetails;

import java.util.List;

/**
 * Created by Administrator on 2016/5/28.
 */
public class ProductDetailsAdapter extends AbsBaseAdapter <ProductDetails.DataBean>{
    public ProductDetailsAdapter(Context context, List<ProductDetails.DataBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }

}
