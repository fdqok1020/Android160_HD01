package com.example.frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bean.ProductDetails;
import com.example.qianfeng.R;
import com.example.task.ProductDetailsInterface;
import com.example.task.ProductDetailsTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {
private TextView tvInfo;
private ProductDetailsTask task;
    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    public static ProductDetailsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        tvInfo = (TextView) view.findViewById(R.id.tv_info);
        Bundle bundle = getArguments();
        String id = String.valueOf(bundle.getInt("id"));
        String path = "http://apicn.seashellmall.com/product/sku/"+id;
        task = new ProductDetailsTask(new ProductDetailsInterface() {
            @Override
            public void onSuccess(ProductDetails details) {
                tvInfo.setText(details.getData().getProduct().getDesc());
            }
        });
        task.execute(path);
        return view;
    }

}
