package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.bean.Joke;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class JokeAdapter extends BaseAdapter {
    private List<Joke> data;
    private Context context;
    protected LayoutInflater inflater;

    public JokeAdapter(Context context,List<Joke> data) {
        this.data=data;
        this.context=context;
        this.inflater=LayoutInflater.from(context);
    }
    public List<Joke> getData(){
        return data;
    }

    @Override
    public int getCount() {
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
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_joke, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv1 = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv2 = (TextView) convertView.findViewById(R.id.tv_source);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(getData().get(position).getTitle());
        holder.tv1.setText(getData().get(position).getDigest());
        holder.tv2.setText(getData().get(position).getSource());

        return convertView;
    }

    public static class ViewHolder {
        TextView tv;
        TextView tv1;
        TextView tv2;

    }
}
