package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bean.Diary;
import com.example.qianfeng.R;

import java.util.List;


/**
 * Created by Administrator on 2016/4/30.
 */
public class DiaryAdapter extends AbsBaseAdapter<Diary> {


    public DiaryAdapter(Context context, List<Diary> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHold holder = null;
        if(convertView == null){
            holder = new ViewHold();
            convertView = inflater.inflate(R.layout.item_listsearch,null);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
           convertView.setTag(holder);
        }else{
            holder = (ViewHold) convertView.getTag();

        }

    holder.tvContent.setText(getData().get(position).getContent());

        return convertView;
    }
    public static  class ViewHold{
        TextView tvContent;
    }
    }


