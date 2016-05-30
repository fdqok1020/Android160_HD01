package com.example.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {

    private List<T> data;
    protected Context context;
    protected LayoutInflater inflater;


    public AbsBaseAdapter(List<T> data, Context context) {
        super();
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public List<T> getData() {
        return data;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data!=null?data.size():0;
    }

    @Override
    public Object getItem(int position) {
        return data!=null?data.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent) ;

}
