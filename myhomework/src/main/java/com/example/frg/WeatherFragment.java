package com.example.frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapter.CityAdapter;
import com.example.app.R;
import com.example.bean.City;
import com.example.constant.HttpConstant;
import com.example.task.WeatherTask;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    public WeatherFragment() {
        // Required empty public constructor
    }


    //ui
    private ListView lvCity;
    private List<City> data;
    //适配器
    private CityAdapter adapter;
    //异步任务
    private WeatherTask task;

    public static WeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);




    }

    @Override
    public void onStart() {
        lvCity = (ListView) getActivity().findViewById(R.id.lv_city);
        data = new ArrayList<>();
        adapter = new CityAdapter(getActivity(),data);
        lvCity.setAdapter(adapter);
        task = new WeatherTask(adapter);
        task.execute(HttpConstant.WEATHEA_PATH);
        super.onStart();
    }
}