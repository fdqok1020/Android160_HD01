package com.example.frg;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.activity.ProductActivity;
import com.example.adapter.FirstPageAdapter;
import com.example.bean.Product;
import com.example.qianfeng.R;
import com.example.task.FirstPageTask;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GridFragment extends Fragment {

    //  ui
    private GridViewWithHeaderAndFooter disGrid;
    private FirstPageAdapter adapter;
    private FirstPageTask task;
    private List<Product.DataBean.ProductsBean> data;
    private String path;

    public GridFragment() {
        // Required empty public constructor
    }

    public static GridFragment newInstance() {

        Bundle args = new Bundle();

        GridFragment fragment = new GridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid, container, false);
        disGrid = (GridViewWithHeaderAndFooter) view.findViewById(R.id.discount_gv);
        Bundle bundle = getArguments();
        int x = bundle.getInt("m");
        if(x == 2){
            int homeId = bundle.getInt("homeId");
            int subId = bundle.getInt("subId");
            path ="http://apicn.seashellmall.com/product/list/"+homeId+"-"+subId+"?size=20&p=1" ;
        }else if(x ==1){
            String encode = bundle.getString("path");
            path ="HTTP://apicn.seashellmall.com/search/product/?q="+encode +"&size=20" ;
        }
        data = new ArrayList<>();
        adapter = new FirstPageAdapter(getContext(),data);
        task = new FirstPageTask(adapter);
        task.execute(path);
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
