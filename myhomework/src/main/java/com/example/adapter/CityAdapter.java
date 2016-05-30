package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.app.R;
import com.example.bean.City;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 */
public class CityAdapter extends BaseAdapter {
    private List<City> data;
    private Context context;
    protected LayoutInflater inflater;

    public CityAdapter(Context context,List<City> data) {
        this.data=data;
        this.context=context;
        this.inflater=LayoutInflater.from(context);
    }
    public List<City> getData(){
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

            convertView = inflater.inflate(R.layout.item_city, null);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_show1);
            holder.tv1 = (TextView) convertView.findViewById(R.id.tv_show2);
            holder.tv2 = (TextView) convertView.findViewById(R.id.tv_show3);
            holder.tv3= (TextView) convertView.findViewById(R.id.tv_show4);
            holder.tv4 = (TextView) convertView.findViewById(R.id.tv_show5);
            holder.tv5 = (TextView) convertView.findViewById(R.id.tv_show6);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //数据映射
        holder.tv.setText("城市 :"+getData().get(position).getCity());
        holder.tv1.setText("风向 :"+getData().get(position).getTemp());
        holder.tv2.setText("湿度 :"+getData().get(position).getWD());
        holder.tv3.setText("时间："+getData().get(position).getSD());
        holder.tv4.setText("能见度: "+getData().get(position).getTime());
        holder.tv5.setText("温度: "+getData().get(position).getNjd());
        return convertView;

    }

    public static class ViewHolder {
        TextView tv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;

    }

}
