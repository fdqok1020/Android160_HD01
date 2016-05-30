package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.HeadView;
import com.example.qianfeng.R;
import com.example.utils.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class DiscoverAdapter extends AbsBaseAdapter<HeadView.DataBean.TopicsBean> {
    public DiscoverAdapter(Context context, List<HeadView.DataBean.TopicsBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold hold = null;
        if (convertView == null) {
            hold = new ViewHold();
            convertView = inflater.inflate(R.layout.ltem_dislist, null);
            hold.iv = (ImageView) convertView.findViewById(R.id.iv_dis);
            hold.tv = (TextView) convertView.findViewById(R.id.tv_dis);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

            hold.tv.setText(getData().get(position).getTitle());
            hold.iv.setTag(getData().get(position).getImage());
            ImageLoader.loaderImage(getData().get(position).getImage(), hold.iv);

        return convertView;
    }

    public class ViewHold {
        private TextView tv;
        private ImageView iv;
    }

}

