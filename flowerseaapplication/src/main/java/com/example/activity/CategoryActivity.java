package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.DetailsAdapter;
import com.example.adapter.MenuListAdapter;
import com.example.bean.Category;
import com.example.contanst.HttpURL;
import com.example.qianfeng.MainActivity;
import com.example.qianfeng.R;
import com.example.task.MenuListData;
import com.example.task.MenuListTask;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    //ui
    private ListView mListMenu;
    private ListView mListDetails;
    //适配器
    private MenuListAdapter menuAdapter;
    private List<Category.DataBean.CategoriesBean> data;
    //异步任务
    private MenuListTask task;
    private List<Category.DataBean.CategoriesBean.SubBean> sub;
    private int homeId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initView();
        mListMenu.setAdapter(menuAdapter);
        task.execute(HttpURL.CATEGORY_PATH);
        mListMenu.setOnItemClickListener(this);

    }
    private void initView() {
        mListMenu = (ListView) findViewById(R.id.lv_menu);
        mListDetails = (ListView) findViewById(R.id.lv_details);
        data = new ArrayList<>();
        menuAdapter = new MenuListAdapter(this, data);
        //使用接口回调的方式给右边的listVi赋初值
        task = new MenuListTask(menuAdapter, new MenuListData() {
            @Override
            public void getData(final List<Category.DataBean.CategoriesBean.SubBean> list) {
                DetailsAdapter  detaAdpter = new DetailsAdapter(getApplicationContext(), list);
                mListDetails.setAdapter(detaAdpter);
                //对右边的listView进行监听
                mListDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int subId = list.get(position).getId();
                        Intent intent = new Intent(getApplicationContext(),CategoryProductActivity.class);
                        intent.putExtra("homeId",homeId);
                        intent.putExtra("subId",subId);
                        startActivity(intent);
                    }
                });

            }
        });
    }
    //对menuList进行监听
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        homeId = data.get(position).getId();
        sub = data.get(position).getSub();
        DetailsAdapter  detaAdpter = new DetailsAdapter(this, sub);
        mListDetails.setAdapter(detaAdpter);
        mListDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int subId = sub.get(position).getId();
                Intent intent = new Intent(getApplicationContext(),CategoryProductActivity.class);
                intent.putExtra("homeId",homeId);
                intent.putExtra("subId",subId);
                startActivity(intent);
            }
        });

    }


    //监听
    public void click(View  view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);

    }
}
