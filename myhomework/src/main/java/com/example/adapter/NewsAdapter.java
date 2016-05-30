package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.R;
import com.example.bean.News;

import java.util.List;

/**
 * Created by Administrator on 2016/5/5.
 */
public class NewsAdapter extends BaseAdapter {
    private List<News> data;
    private Context context;
    protected LayoutInflater inflater;

    public NewsAdapter(Context context, List<News> data) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public List<News> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null ? data.get(position) : null;
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
            convertView = inflater.inflate(R.layout.item_news, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_news_title);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_news);
            holder.tv2 = (TextView) convertView.findViewById(R.id.tv_news_source);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv.setText(getData().get(position).getTitle());
//        Log.i("TAG","xxxxxxx"+getData().get(position).getTitle());
        holder.tv2.setText(getData().get(position).getSource());
       holder.iv.setTag(getData().get(position).getFirstImg());
//        new BitmapTask(holder.iv).execute(getData().get(position).getFirstImg());
//        Log.i("TAG","111"+getData().get(position).getFirstImg());
        return  convertView;
    }
    public static class ViewHolder {
        TextView tv;
        ImageView iv;
        TextView tv2;

    }
}
