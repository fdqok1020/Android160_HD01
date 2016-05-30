package com.example.app.adapter;

import android.content.Context;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app.R;
import com.example.app.constast.bean.Diary;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */
public class DiaryAdapter extends AbsBaseAdapter<Diary> {
    public DiaryAdapter(List<Diary> data, Context context) {
        super(data, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHold holder = null;
        if(convertView == null){
            holder = new ViewHold();
            convertView = inflater.inflate(R.layout.ltem_diary,null);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            holder.tvTime= (TextView) convertView.findViewById(R.id.ietm_tv_time);
           convertView.setTag(holder);
        }else{
            holder = (ViewHold) convertView.getTag();

        }
        Time time = new Time();
        time.setToNow();
        int year = time.year;
        int month = time.month+1;
        int day = time.monthDay;

        holder.tvTime.setText( year +
                "年 " + month +
                "月 " + day +
                "日 " );
    holder.tvTitle.setText(getData().get(position).getTitle());

        return convertView;
    }
    public static  class ViewHold{
        TextView tvTitle;
        TextView tvTime;
    }
    }


