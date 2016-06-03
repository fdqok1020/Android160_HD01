package com.example.frg;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.GuideBaseAdapter;
import com.example.bean.Guide;
import com.example.constant.HttpConstant;
import com.example.httputils.HttpUtil;
import com.example.httputils.IRequestCallBack;
import com.example.qianfeng.giftapplication.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideBaseFragment extends Fragment {

    private ListView listView;
    private GuideBaseAdapter adapter;
    private List<Guide.DataBean.ItemsBean> data;
    private Context mContext;
    public GuideBaseFragment() {
        // Required empty public constructor
    }

    public static GuideBaseFragment newInstance(HttpConstant.UrlInfo info) {

        Bundle args = new Bundle();
        args.putSerializable("info", info);
        GuideBaseFragment fragment = new GuideBaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide_base, container, false);
        listView = (ListView) view.findViewById(R.id.guide_base_list);
        listView.setDivider(null);
        mContext = getActivity();
        HttpConstant.UrlInfo info = (HttpConstant.UrlInfo) getArguments().getSerializable("info");
        HttpUtil.requestGet(info.getUrl(), new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Guide guide = gson.fromJson(result, Guide.class);
                data = guide.getData().getItems();
                adapter = new GuideBaseAdapter(mContext, data);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

}
