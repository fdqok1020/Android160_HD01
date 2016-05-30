package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.app.R;
import com.example.bean.DiscoveryList;
import com.example.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class DiscoveryAdapter extends AbsBaseAdapter<DiscoveryList> {


    public DiscoveryAdapter(Context context, List<DiscoveryList> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_discovery, null);
            holder.ivDiscovery = (ImageView) convertView.findViewById(R.id.iv_discovery);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ivDiscovery.setImageResource(R.mipmap.ic_launcher);
        holder.ivDiscovery.setTag(getData().get(position).getPhoto());
        ImageLoader.loaderImage(getData().get(position).getPhoto(), holder.ivDiscovery);
        return convertView;
    }

    public static class ViewHolder {
        ImageView ivDiscovery;

    }
}
