package com.example.frg;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.bean.Banners;
import com.example.bean.GuideProduct;
import com.example.bean.HeadList;
import com.example.constant.HttpConstant;
import com.example.httputils.HttpUtil;
import com.example.httputils.IRequestCallBack;
import com.example.httputils.bitmap.ImageLoader;
import com.example.qianfeng.giftapplication.GuideWebActivity;
import com.example.qianfeng.giftapplication.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {
    //ui
    @BindView(R.id.selected_expand_listview)
    PullToRefreshExpandableListView mExpandListView;

   private RecyclerView mRecyclerView;
    //数据
    private List<String> groupData = new ArrayList<>();
    private Map<String, List<String>> childDataMap = new HashMap<>();
    private Map<String, List<String>> childUrlMap = new HashMap<>();
    private MyExpandAdapter mExpandAdapter;
    private ExpandableListView listView;
    private List<String> urls = new ArrayList<>();
    //上下文
    private Context mContext;
    private HeaderViewHolder headerViewHolder;
    private RecyclerViewAdapter recyclerViewAdapter;
//    private RecyclerView mRecyclerView;

    public GuideFragment() {
        // Required empty public constructor
    }

    public static GuideFragment newInstance(HttpConstant.UrlInfo info) {

        Bundle args = new Bundle();
        args.putSerializable("info", info);
        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guide, container, false);
        ButterKnife.bind(this, view);
//        mExpandListView = (PullToRefreshExpandableListView) view.findViewById(R.id.selected_expand_listview);
        mContext = getContext();
         View viewHead  = LayoutInflater.from(mContext).inflate(R.layout.recycle,null);
        mRecyclerView = (RecyclerView) viewHead.findViewById(R.id.home_recycler_view);
        setupHeaderView();
        setupExpandListView();
        setupRecyclerView();
        listView.addHeaderView(viewHead);


        //设置分割线
      listView.setDivider(null);
        return view;

    }


    private void setupRecyclerView() {

        //设置一个布局管理器
        LinearLayoutManager layputManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        //设置一个适配器
        mRecyclerView.setLayoutManager(layputManager);
        initData();

    }

    private List<HeadList.DataBean.SecondaryBannersBean> dataInfo = new ArrayList<>();

    private void initData() {
        HttpUtil.requestGet(HttpConstant.HEAD_LIST_PATH, new IRequestCallBack() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                HeadList head = gson.fromJson(result, HeadList.class);
                List<HeadList.DataBean.SecondaryBannersBean> secondary_banners = head.getData().getSecondary_banners();
                dataInfo.addAll(secondary_banners);
                recyclerViewAdapter = new RecyclerViewAdapter(dataInfo);
                mRecyclerView.setAdapter(recyclerViewAdapter);

            }
        });


    }


    //创建一个ViewHolder类继承RecyclerView.ViewHolder
    class MyViewHoleder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycle_view_item_img)
        public ImageView itemImage;

        public MyViewHoleder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<MyViewHoleder> {
        private List<HeadList.DataBean.SecondaryBannersBean> data;

        public RecyclerViewAdapter(List<HeadList.DataBean.SecondaryBannersBean> data) {
            this.data = data;
        }

        @Override
        public MyViewHoleder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.home_recycler_view_item, null);
            return new MyViewHoleder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHoleder holder, final int position) {
            Picasso.with(mContext).load(data.get(position).getImage_url()).into(holder.itemImage);
        }

        @Override
        public int getItemCount() {
            return dataInfo == null ? 0 : dataInfo.size();
        }
    }


    //设置图片自动跳转的时间
    @Override
    public void onStart() {
        super.onStart();
        headerViewHolder.convenientBanner.startTurning(5000);
    }

    //界面结束时将自动跳转关闭
    @Override
    public void onStop() {
        super.onStop();
        headerViewHolder.convenientBanner.stopTurning();
    }


    private void setupHeaderView() {

        View headView = LayoutInflater.from(mContext).inflate(R.layout.listview_header_view, null);
        headerViewHolder = new HeaderViewHolder(headView);
        HttpUtil.requestGet(HttpConstant.BANNERS_PATH, new IRequestCallBack() {
            List<String> list = new ArrayList<String>();

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Banners banners = gson.fromJson(result, Banners.class);
                List<Banners.DataBean.BannersBean> banners1 = banners.getData().getBanners();
                for (int i = 0; i < banners1.size(); i++) {
                    String cover_image_url = banners1.get(i).getImage_url();
                    list.add(cover_image_url);
                }
                urls.addAll(list);
                //动态加载
                headerViewHolder.convenientBanner.setPages(new CBViewHolderCreator<MyBannerHolder>() {
                    @Override
                    public MyBannerHolder createHolder() {
                        return new MyBannerHolder();
                    }
                }, urls)
                        //设置界面上的匹配关联的原点
                        .setPageIndicator(new int[]{R.drawable.selecter_dot, R.drawable.selecter_dot1})
                        //设置翻页的效果
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

            }
        });

                //从PullToRefreshExpandableListView中获取原始的ExpandLListView
        listView = mExpandListView.getRefreshableView();

        listView.addHeaderView(headView);



    }

    private class MyBannerHolder implements Holder<String> {
        ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, String path) {
            imageView.setTag(path);
            ImageLoader.loadImage(mContext, path, imageView);
        }
    }

    class HeaderViewHolder {
        @BindView(R.id.home_header_bannner)
        public ConvenientBanner convenientBanner;

        public HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    private void setupExpandListView() {
        //准备数据源
        groupData = new ArrayList<>();
        childDataMap = new HashMap<>();
        //创建适配器
HttpUtil.requestGet(HttpConstant.GUIDE_PRODUCT_PATH, new IRequestCallBack() {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        GuideProduct product =gson.fromJson(result,GuideProduct.class);
        final List<GuideProduct.DataBean.ItemsBean> items = product.getData().getItems();
        initGroupData(items);
        mExpandAdapter = new MyExpandAdapter(items,groupData,childDataMap);
        mExpandAdapter.notifyDataSetChanged();
        listView.setAdapter(mExpandAdapter);
        //设置点击事件
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(mContext, GuideWebActivity.class);

                String s = groupData.get(groupPosition);
                List<String> childUrl = childUrlMap.get(s);
                intent.putExtra("url",childUrl.get(childPosition));
                startActivity(intent);
                Toast.makeText(mContext, childUrl.get(childPosition), Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        //设置所有的GroupItem全部展开
        for (int i = 0; i < groupData.size(); i++) {
            listView.expandGroup(i);
        }
    }
});

    }

    //数据去重
    public static List<String> removeDuplicate(List<String> list)

    {
        Set set = new LinkedHashSet<String>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

//获取时间是星期几
private String getWeek(String pTime) {


    String Week = "";


    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c = Calendar.getInstance();
    try {

        c.setTime(format.parse(pTime));

    } catch (ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 1) {
        Week += "日";
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 2) {
        Week += "一";
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 3) {
        Week += "二";
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 4) {
        Week += "三";
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 5) {
        Week += "四";
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 6) {
        Week += "五";
    }
    if (c.get(Calendar.DAY_OF_WEEK) == 7) {
        Week += "六";
    }



    return Week;
}


    //设置数据

    private void initGroupData(List<GuideProduct.DataBean.ItemsBean> list) {

        for (int i = 0; i <list.size() ; i++) {
            long  dataTime = list.get(i).getCreated_at();
            double x = (double)dataTime*1000;
            //将毫秒数转化为时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String format1 = format.format(x);
            groupData.add(format1);
        }

        groupData = removeDuplicate(groupData);
        List<String> childPath  = null;
        List<String> chilUrl = null;
        for (int i = 0; i <groupData.size(); i++) {
            childPath = new ArrayList<>();
            chilUrl = new ArrayList<>();
            for (int j = 0; j <list.size() ; j++) {
                long  dataTime = list.get(j).getCreated_at();
                String url = list.get(j).getUrl();
                String path = list.get(j).getCover_image_url();
                double x = (double)dataTime*1000;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String format1 = format.format(x);
                if(groupData.get(i).equals(format1)){
                   childPath.add(path);
                    chilUrl.add(url);
                }
                childDataMap.put(groupData.get(i),childPath);
                childUrlMap.put(groupData.get(i),chilUrl);
            }
        }


    }


    //适配器
    class MyExpandAdapter extends BaseExpandableListAdapter {

        private  List<GuideProduct.DataBean.ItemsBean> items;

       private List<String> groupList = new ArrayList<>();
       private Map<String, List<String>> map = new HashMap<>();
        public MyExpandAdapter(List<GuideProduct.DataBean.ItemsBean> items,List<String> groupList,Map<String, List<String>> map) {
            this.items = items;
            this.groupList = groupList;
            this.map=map;
        }

        /**
         * 分组个数
         *
         * @return
         */
        @Override
        public int getGroupCount() {
            return groupList == null ? 0 : groupList.size();
        }

        /**
         * 每一个组中的Child个数
         *
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            String id = groupList.get(groupPosition);
            List<String> childList = map.get(id);
            return childList == null ? 0 : childList.size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = convertView;
            GroupViewHolder groupViewHolder = null;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.home_group_item_view, null);
                groupViewHolder = new GroupViewHolder(view);
            } else {
                groupViewHolder = (GroupViewHolder) view.getTag();
            }
            String s = groupList.get(groupPosition);
            String week = getWeek(s);
            groupViewHolder.dateText.setText(groupList.get(groupPosition)+"  星期"+week);
            return view;
        }

        class GroupViewHolder {
            @BindView(R.id.home_group_item_date_txt)
            public TextView dateText;
            @BindView(R.id.home_group_item_state_txt)
            public TextView stateText;

            public GroupViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this, view);
            }
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View childView = convertView;
            ChildViewHolder viewHolder = null;
            if (childView == null) {
                childView = LayoutInflater.from(mContext).inflate(R.layout.home_child_item_view, null);
                viewHolder = new ChildViewHolder(childView);
            } else {
                viewHolder = (ChildViewHolder) childView.getTag();
            }

            String id = groupList.get(groupPosition);
            List<String> childList = map.get(id);
            String path = childList.get(childPosition);
            Picasso.with(mContext).load(path).into(viewHolder.imageView);
            for (int i = 0; i <items.size() ; i++) {
                if (items.get(i).getCover_image_url().equals(path)){
                    viewHolder.tvTitle.setText(items.get(i).getTitle());
                    viewHolder.tvLikes.setText(String.valueOf(items.get(i).getLikes_count()));
                }

            }

            return childView;
        }

        class ChildViewHolder {
            @BindView(R.id.child_item_img)
            public ImageView imageView;
            @BindView(R.id.tv_title)
            public TextView tvTitle;
            @BindView(R.id.tv_child_like)
            public TextView tvLikes;
            public ChildViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this, view);
            }

//            @OnClick(R.id.child_item_img)
//            public void click(View view) {
//                Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
//            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

}
