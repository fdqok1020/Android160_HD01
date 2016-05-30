package com.example.frg;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.DiscoveryAdapter;
import com.example.app.DisWebActivity;
import com.example.app.R;
import com.example.bean.DiscoveryList;
import com.example.constant.Constant;
import com.example.task.DiscoveryTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoveryFragment extends Fragment {

    //UI
    private PullToRefreshListView ptflv;
    //数据
    private List<DiscoveryList> data;

    //适配器
    private DiscoveryAdapter adapter;
    //异步任务
    private DiscoveryTask task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discovery, container, false);
        initDtat();
        ptflv  = (PullToRefreshListView) view.findViewById(R.id.ptflv);
        adapter = new DiscoveryAdapter(getActivity(), data);
        task = new DiscoveryTask(adapter,ptflv);
        task.execute(Constant.DISCOVERY_PATH);
        ptflv.setAdapter(adapter);

        ptflv.setMode(PullToRefreshBase.Mode.BOTH);
        ptflv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                adapter.getData().clear();
                new DiscoveryTask(adapter,ptflv).execute(Constant.DISCOVERY_PATH);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                adapter.getData().clear();
                new DiscoveryTask(adapter,ptflv).execute(Constant.DISCOVERY_PATH);

            }
        });
        ptflv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), DisWebActivity.class);
                intent.putExtra("url",data.get(position-1).getHref());
                Log.i("TAG","我哪里越界了++++++++++"+position);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initDtat() {
        data = new ArrayList<>();
    }


    public DiscoveryFragment() {
        // Required empty public constructor
    }

    public static DiscoveryFragment newInstance() {

        Bundle args = new Bundle();

        DiscoveryFragment fragment = new DiscoveryFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
