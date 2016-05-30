package com.example.frg;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bean.ProductDetails;
import com.example.qianfeng.R;
import com.example.task.ProductDetailsInterface;
import com.example.task.ProductDetailsTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductOtherInfoFragment extends Fragment {

    private TextView shoppingName;
    private TextView shoppingFrom;
    private TextView product;
    private TextView collcet;
    private LinearLayout shoppingSatr;
    private LinearLayout productSatr;
    private ProductDetailsTask task;

    public ProductOtherInfoFragment() {
        // Required empty public constructor
    }

    public static ProductOtherInfoFragment newInstance() {

        Bundle args = new Bundle();

        ProductOtherInfoFragment fragment = new ProductOtherInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_other_info, container, false);
        //商家信息
        shoppingName = (TextView) view.findViewById(R.id.shopping_form);//商品来自哪里
        shoppingFrom = (TextView) view.findViewById(R.id.tv_shopping);//商家名字
        shoppingSatr= (LinearLayout) view.findViewById(R.id.ll_star);//商家的评价
        //商品信息
        product = (TextView) view.findViewById(R.id.tv_product_name);//商品名称
        collcet = (TextView) view.findViewById(R.id.tv_soucang);//收藏
        productSatr = (LinearLayout) view.findViewById(R.id.ll_pingjia);//商品评价
        Bundle bundle = getArguments();
        String id = String.valueOf(bundle.getInt("id"));
        String path = "http://apicn.seashellmall.com/product/sku/"+id;
        task = new ProductDetailsTask(new ProductDetailsInterface() {
            @Override
            public void onSuccess(ProductDetails details) {
                shoppingName.setText(details.getData().getProduct().getLocation());
                shoppingFrom.setText(details.getData().getProduct().getMerchant().getName());
                product.setText(details.getData().getProduct().getName());
                collcet.setText(String.valueOf(details.getData().getProduct().getFav_count()));
                int x = details.getData().getProduct().getScore();
                for (int i = 0; i <x/2 ; i++) {
                    ImageView imag = new ImageView(getContext());
                    imag.setImageResource(R.drawable.star_pink);
                    int childCount = shoppingSatr.getChildCount();
                    if(childCount>=5){
                        break;
                    }

                    shoppingSatr.addView(imag);
                }
                for (int i = 0; i <(10-x)/2 ; i++) {
                    ImageView imag = new ImageView(getContext());
                    imag.setImageResource(R.drawable.star_gray);
                    shoppingSatr.addView(imag);

                }
                for (int i = 0; i <x/2 ; i++) {
                    ImageView imag = new ImageView(getContext());
                    imag.setImageResource(R.drawable.star_pink);
                    int childCount = productSatr.getChildCount();
                    if(childCount>=5){
                        break;
                    }

                    productSatr.addView(imag);
                }
                for (int i = 0; i <(10-x)/2 ; i++) {
                    ImageView imag = new ImageView(getContext());
                    imag.setImageResource(R.drawable.star_gray);

                    productSatr.addView(imag);
                }
            }
        });
        task.execute(path);
        return view;
    }

}
