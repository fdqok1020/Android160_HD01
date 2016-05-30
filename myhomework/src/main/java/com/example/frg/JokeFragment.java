package com.example.frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.JokeAdapter;
import com.example.app.R;
import com.example.bean.Joke;
import com.example.constant.HttpConstant;
import com.example.task.JokeTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends Fragment {

//ui
    private ListView lvJoke;
    //适配器
    private JokeAdapter adapter;
    //数据
    private List<Joke> data;
    //异步任务
    private JokeTask task;

    public JokeFragment() {
        // Required empty public constructor
    }

    public static JokeFragment newInstance() {
        
        Bundle args = new Bundle();
        
        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joke, container, false);
    }

    @Override
    public void onStart() {

        lvJoke = (ListView) getActivity().findViewById(R.id.lv_joke);
        data = new ArrayList<>();
        adapter = new JokeAdapter(getActivity(),data);
        task = new JokeTask(adapter);
        lvJoke.setAdapter(adapter);
        task.execute(HttpConstant.JOKE_PATH);
        super.onStart();
    }
}
