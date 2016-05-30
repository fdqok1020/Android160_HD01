package com.example.frg;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.activity.ProductActivity;
import com.example.adapter.FirstPageAdapter;
import com.example.bean.Product;
import com.example.qianfeng.R;
import com.example.task.FirstPageTask;
import com.example.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseDiscoverFragment extends Fragment {
//ui

    private GridViewWithHeaderAndFooter disGrid;
    private FirstPageAdapter adapter;
    private FirstPageTask task;
    private List<Product.DataBean.ProductsBean> data;

    public BaseDiscoverFragment() {
        // Required empty public constructor
    }

    public static BaseDiscoverFragment newInstance() {
        Bundle args = new Bundle();
        BaseDiscoverFragment fragment = new BaseDiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_discover, container, false);
        disGrid = (GridViewWithHeaderAndFooter) view.findViewById(R.id.dis_gv);

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        String path = bundle.getString("path");
        LayoutInflater inflater1 =LayoutInflater.from(getContext());
        View view1 = inflater.inflate(R.layout.discoverhead, null);
        ImageView iv = (ImageView) view1.findViewById(R.id.iv_head);
        iv.setTag(path);
        ImageLoader.loaderImage(path,iv);
        disGrid.addHeaderView(view1);
        data = new ArrayList<>();
        adapter = new FirstPageAdapter(getContext(),data);
        task = new FirstPageTask(adapter);
        switch (id){
            case 11:
                String  httpPath ="http://apicn.seashellmall.com/product/topic/11/products?size=20&p=1";
                task.execute(httpPath);
                break;
            case 8:
                String  httpPath1 ="http://apicn.seashellmall.com/product/topic/8/products?size=20&p=1";
                task.execute(httpPath1);
                break;
            case 10:
                String  httpPath2 ="http://apicn.seashellmall.com/product/topic/10/products?size=20&p=1";
                task.execute(httpPath2);
                break;
            case 9:
                String  httpPath3 ="http://apicn.seashellmall.com/product/topic/9/products?size=20&p=1";
                task.execute(httpPath3);
                break;
            case 7:
                String  httpPath4 ="http://apicn.seashellmall.com/product/topic/7/products?size=20&p=1";
                task.execute(httpPath4);
                break;
            case 1:
                String  httpPath5 ="http://apicn.seashellmall.com/product/topic/1/products?size=20&p=1";
                task.execute(httpPath5);
                break;
            case 2:
                String  httpPath6 ="http://apicn.seashellmall.com/product/topic/2/products?size=20&p=1";
                task.execute(httpPath6);
                break;
            case 4:
                String  httpPath7 ="http://apicn.seashellmall.com/product/topic/4/products?size=20&p=1";
                task.execute(httpPath7);
                break;
        }
        disGrid.setAdapter(adapter);

        disGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product.DataBean.ProductsBean productsBean = data.get(position);
                Intent intent = new Intent(getContext(), ProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product",productsBean);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

}
