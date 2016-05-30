package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bean.Product;
import com.example.frg.ProductDetailsFragment;
import com.example.frg.ProductOtherInfoFragment;
import com.example.qianfeng.R;
import com.example.utils.ImageLoader;

public class ProductActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    //ui
    private ImageView ivPic;
    private TextView tvPrice;
    private TextView tvRealPrice;
    private TextView tvDiscount;
    private TextView tvName;
    private LinearLayout linearLayout;
    private FrameLayout llDetails;
    private RadioGroup group;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int skuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        Product.DataBean.ProductsBean productBean = (Product.DataBean.ProductsBean) intent.getSerializableExtra("product");
        initView();
        tvName.setText(productBean.getName());
        int price = productBean.getCurrent_sku().getList_price().getCNY();
        int realPrice = productBean.getCurrent_sku().getReal_price().getCNY();
        ivPic.setTag(productBean.getImages().get(0).getThumb());
        ImageLoader.loaderImage(productBean.getImages().get(0).getThumb(), ivPic);
        tvPrice.setText("¥" + String.valueOf(realPrice / 100 + "." + realPrice % 100));
        tvRealPrice.setText(String.valueOf(price / 100 + "." + price % 100));
        tvDiscount.setText(String.valueOf(String.valueOf((realPrice * 10) / price + "." + ((price * 100) / realPrice) % 10) + "折"));
        int score = productBean.getScore();
        for (int i = 0; i < score / 2; i++) {
            ImageView imag = new ImageView(this);
            imag.setImageResource(R.drawable.star_pink);
            int childCount = linearLayout.getChildCount();
            if (childCount >= 5) {
                break;
            }
            linearLayout.addView(imag);
        }
        for (int i = 0; i < (10 - score) / 2; i++) {
            ImageView imag = new ImageView(this);
            imag.setImageResource(R.drawable.star_gray);
            linearLayout.addView(imag);

        }
        skuId = productBean.getCurrent_sku().getId();
        //设置监听
        manager = getSupportFragmentManager();
        group.setOnCheckedChangeListener(this);
        onCheckedChanged(group,R.id.btn_details);
    }


    private void initView() {
        ivPic = (ImageView) findViewById(R.id.iv_logo);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvRealPrice = (TextView) findViewById(R.id.tv_realprice);
        tvDiscount = (TextView) findViewById(R.id.tv_discount);
        linearLayout = (LinearLayout) findViewById(R.id.ratingbar);
        llDetails = (FrameLayout) findViewById(R.id.ll_details);
        group = (RadioGroup) findViewById(R.id.btn_group);

    }

    public void click(View view) {
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction = manager.beginTransaction();
        Bundle bundle = null;
        switch (checkedId) {
            case R.id.btn_details:
                ProductDetailsFragment productFragmen = ProductDetailsFragment.newInstance();
                bundle = new Bundle();
                bundle.putInt("id", skuId);
                productFragmen.setArguments(bundle);
                transaction.replace(R.id.ll_details, productFragmen);
                break;
            case R.id.btn_otherinfo:
                ProductOtherInfoFragment otherInfoFragment = ProductOtherInfoFragment.newInstance();
                bundle = new Bundle();
                bundle.putInt("id", skuId);
                otherInfoFragment.setArguments(bundle);
                transaction.replace(R.id.ll_details, otherInfoFragment);
                break;
        }
        transaction.commit();
    }
}
