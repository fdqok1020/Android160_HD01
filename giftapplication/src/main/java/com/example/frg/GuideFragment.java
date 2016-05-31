package com.example.frg;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.example.bean.Banners;
import com.example.constant.HttpConstant;
import com.example.httputils.HttpUtil;
import com.example.httputils.IRequestCallBack;
import com.example.httputils.bitmap.ImageLoader;
import com.example.qianfeng.giftapplication.R;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideFragment extends Fragment {
    //ui
    @BindView(R.id.selected_expand_listview)
    PullToRefreshExpandableListView mExpandListView;
    @BindView(R.id.home_guide_toolbar)
    Toolbar toolbar;
    //数据
    private List<String> groupData = new ArrayList<>();
    private Map<String, List<String>> childDataMap = new HashMap<>();
    private MyExpandAdapter adapter;
    private ExpandableListView listView;
    private List<String> urls = new ArrayList<>();
    //上下文
    private Context mContext;
    private HeaderViewHolder headerViewHolder;


    public GuideFragment() {
        // Required empty public constructor
    }

    public static GuideFragment newInstance() {

        Bundle args = new Bundle();

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
        //给有层级的ListView进行设置
        mContext = getContext();
        setupHeaderView();
        setupExpandListView();
        return view;
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
            List<String > list = new ArrayList<String>();
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Banners banners = gson.fromJson(result,Banners.class);
                List<Banners.DataBean.BannersBean> banners1 = banners.getData().getBanners();
                for (int i = 0; i <banners1.size() ; i++) {
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
                        .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                        //设置翻页的效果
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
                //从PullToRefreshExpandableListView中获取原始的ExpandLListView

            }
        });

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
            ImageLoader.loadImage(mContext,path,imageView);
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
        adapter = new MyExpandAdapter();
        //绑定适配器

        listView.setAdapter(adapter);
    }


    //适配器
    class MyExpandAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return groupData == null ? 0 : groupData.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            List<String> child = childDataMap.get(groupPosition);
            return child == null ? 0 : child.size();
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
            return null;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

}
