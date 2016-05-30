package com.example.qianfeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.activity.CategoryActivity;
import com.example.activity.DiscoverActivity;
import com.example.activity.DiscoverProductActivity;
import com.example.activity.HelpActivity;
import com.example.activity.OrderActivity;
import com.example.activity.ProductActivity;
import com.example.activity.SearchActivity;
import com.example.activity.SettingsActivity;
import com.example.adapter.FirstPageAdapter;
import com.example.adapter.HeadViewPagerAdapter;
import com.example.bean.HeadView;
import com.example.bean.Product;
import com.example.contanst.HttpURL;
import com.example.task.FirstPageTask;
import com.example.task.HeadTask;
import com.example.task.HeadViewInterface;
import com.example.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.GridViewWithHeaderAndFooter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    //UI控件
    private GridViewWithHeaderAndFooter mGridView;
    private ImageView logoImg;
    private ImageView ivSearch;
    //headPager适配器
    private FirstPageAdapter adapterGirdView;
    private HeadViewPagerAdapter adapter;
    private FirstPageTask task;
    private HeadTask task1;
    private View view;
    private SlidingPaneLayout sliding;
    private List<View> imgList;
    private LinearLayout llDots;
    //数据源
    private List<Product.DataBean.ProductsBean> dataProduct;
    private List<HeadView.DataBean.TopicsBean> dataHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapterGirdView = new FirstPageAdapter(this, dataProduct);
        task = new FirstPageTask(adapterGirdView);
        task.execute(HttpURL.PRODUCT_PATH);
        addHead();
        mGridView.setAdapter(adapterGirdView);
        mGridView.setOnItemClickListener(this);

    }
//添加头部文件
    public void addHead() {
        LayoutInflater inflater = LayoutInflater.from(this);
        view = inflater.inflate(R.layout.viewpager_head, null);
        llDots = (LinearLayout) view.findViewById(R.id.ll_dots);
       final ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager_head);
        task1 = new HeadTask(new HeadViewInterface() {
            @Override
            public void onSuccess(List<HeadView.DataBean.TopicsBean> list) {

                dataHead.addAll(list);
                for (int i = 0; i < dataHead.size(); i++) {
                    View headView = getLayoutInflater().inflate(R.layout.viewpager_item, null);
                    ImageView iv = (ImageView) headView.findViewById(R.id.iv);
                    iv.setTag(dataHead.get(i).getImage());
                    ImageLoader.loaderImage(dataHead.get(i).getImage(), iv);

//                    Log.i("androidxxx", "+++++++"+dataHead.get(i).getImage());
                    imgList.add(headView);
//                    Log.i("androidxxx", "+++++++"+imgList.size());
                    ImageView imageView = new ImageView(MainActivity.this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(10, 0, 0, 0);
                    imageView.setLayoutParams(params);
                    //设置显示的图片
                    imageView.setImageResource(R.drawable.selecter_dot);
                    //设置为不可编辑
                    imageView.setEnabled(true);
                    //添加到集合
                    llDots.addView(imageView);
                    final int x = i;
                    imageView.setTag(i);
                    //添加事件
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int pos = (int) v.getTag();
                            pager.setCurrentItem(pos);
                        }
                    });
                }
                llDots.getChildAt(0).setEnabled(false);
                adapter = new HeadViewPagerAdapter(imgList);
//                Log.i("androidxxx", "22222+++++++" + imgList.size());
                pager.setAdapter(adapter);
                pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        for (int i = 0; i < llDots.getChildCount(); i++) {
                            //将所有的点都变成灰色
                            llDots.getChildAt(i).setEnabled(true);
                        }
                        //将当前选中的点变成红色
                        llDots.getChildAt(position).setEnabled(false);
                        int discoverId = dataHead.get(position).getId();
                        String path = dataHead.get(position).getImage();
                        Intent intent = new Intent(getApplicationContext(),DiscoverProductActivity.class);
                        intent.putExtra("id",discoverId);
                        intent.putExtra("path",path);
                        startActivity(intent);
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
        });
        task1.execute(HttpURL.HEADVIEW_PATH);
        mGridView.addHeaderView(view);

    }

    //初始化控件
    private void initView() {
        sliding = (SlidingPaneLayout) findViewById(R.id.sliding);
        logoImg = (ImageView) findViewById(R.id.logo);
        ivSearch = (ImageView) findViewById(R.id.iv_search);
        imgList = new ArrayList<>();
        mGridView = (GridViewWithHeaderAndFooter) findViewById(R.id.gv);
        dataProduct = new ArrayList<>();
        dataHead = new ArrayList<>();
    }
    //设置监听
    public void click(View view){
        Intent intent = null;

        switch (view.getId()){
            case R.id.logo:
                if (sliding.isOpen()){
                    sliding.closePane();
                }else{
                    sliding.openPane();
                }
                break;
            case R.id.ll_home:
                if (sliding.isOpen()){
                    sliding.closePane();
                }
                break;
            case R.id.ll_discover:
                intent = new Intent(this, DiscoverActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
            case R.id.ll_category:
                intent = new Intent(this, CategoryActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
            case R.id.iv_search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_orders:
                intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_help:
                intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
        }


    }

//对GridView 进行监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product.DataBean.ProductsBean productsBean = dataProduct.get(position);
        Intent intent = new Intent(this, ProductActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("product",productsBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
