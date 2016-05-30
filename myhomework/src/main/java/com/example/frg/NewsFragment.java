package com.example.frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.NewsAdapter;
import com.example.app.R;
import com.example.bean.News;
import com.example.constant.HttpConstant;
import com.example.task.NewsTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

//ui
    private ListView lvNews;
    private List<News> data;
    //适配器
    private NewsAdapter adapter;
    private NewsTask task;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance() {
        
        Bundle args = new Bundle();
        
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        lvNews = (ListView) view.findViewById(R.id.lv_news);
        return view;

    }

    @Override
    public void onStart() {

        data = new ArrayList<>();
        adapter = new NewsAdapter(getActivity(),data);
        task = new NewsTask(adapter);
        lvNews.setAdapter(adapter);
        task.execute(HttpConstant.NEWS_PATH);

        super.onStart();
    }
}
