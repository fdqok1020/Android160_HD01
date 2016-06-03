package com.example.frg;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.adapter.HotGridViewAdapter;
import com.example.bean.HotProduct;
import com.example.constant.HttpConstant;
import com.example.httputils.HttpUtil;
import com.example.httputils.IRequestCallBack;
import com.example.qianfeng.giftapplication.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {

//ui
    private GridView mGridView;
    private Context mContext;
    private List<HotProduct.DataBean.ItemsBean> data = new ArrayList<>();
    public HotFragment() {
        // Required empty public constructor
    }

    public static HotFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        mGridView = (GridView) view.findViewById(R.id.hot_fragment_grid_view);
        mContext =getContext();
        setupAdapter();
        return view;
    }

    private void setupAdapter() {
        //数据源
        HttpUtil.requestGet(HttpConstant.HOTPRODUCT_PATH, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HotProduct product = gson.fromJson(result,HotProduct.class);
                 data = product.getData().getItems();
                HotGridViewAdapter adapter = new HotGridViewAdapter(mContext,data);
                mGridView.setAdapter(adapter);
            }
        });
    }

}
