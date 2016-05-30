package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.overlayutil.TransitRouteOverlay;

public class HomeActivity extends AppCompatActivity {
    private TextView mCityNameTxt;
    private TextureMapView mMapView;
    private BaiduMap baiduMap;//用来操作地图的对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mCityNameTxt = (TextView) findViewById(R.id.local_city_name);
        Intent intent = getIntent();
        String location_name = intent.getStringExtra("location_name");
        mCityNameTxt.setText(location_name);
        mMapView = (TextureMapView) findViewById(R.id.baidu_mapview);
        baiduMap = mMapView.getMap();
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);


        //创建公交线路规划检索实例
        RoutePlanSearch mSearch = RoutePlanSearch.newInstance();

        //创建公交线路规划检索监听者
        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
                if (transitRouteResult == null || transitRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(HomeActivity.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                }
                if (transitRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                    return;
                }
                if (transitRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    TransitRouteOverlay overlay = new MyTransitRouteOverlay(baiduMap);
                    baiduMap.setOnMarkerClickListener(overlay);
                    overlay.setData(transitRouteResult.getRouteLines().get(0));
                    overlay.addToMap();
                    overlay.zoomToSpan();
                }
            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        };
        //设置公交线路规划检索监听者
        mSearch.setOnGetRoutePlanResultListener(listener);
        //准备检索起、终点信息；
        PlanNode stNode = PlanNode.withCityNameAndPlaceName("武汉", "金融港");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("武汉", "光谷广场");
        //第五步，发起公交线路规划检索；
        mSearch.transitSearch((new TransitRoutePlanOption()).from(stNode).city("武汉").to(enNode));
        //释放检索实例
        mSearch.destroy();





//
//        double location_lat = intent.getDoubleExtra("location_lat", 0);
//        double location_lng = intent.getDoubleExtra("location_lng", 0);
//        //定义Maker坐标点  114.436169,30.459964
//        LatLng point = new LatLng(30.459964, 114.436169);
//        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
//        OverlayOptions overlayOptions = new MarkerOptions().position(point).icon(bitmapDescriptor);
//        baiduMap.addOverlay(overlayOptions);
//
//        //114.428677,30.466345
//        LatLng point2 = new LatLng(30.466345, 114.428677);
//        BitmapDescriptor bitmapDescriptor2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
//        OverlayOptions overlayOptions2 = new MarkerOptions().position(point2).icon(bitmapDescriptor2);
//        baiduMap.addOverlay(overlayOptions2);
//
//        //设置地图的中心点
//        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(point2, 18);
//        baiduMap.setMapStatus(mapStatusUpdate);
//
//
//        //给标注物设置点击事件
//        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Button button = new Button(HomeActivity.this);
//                button.setText("司机信息");
//                LatLng position = marker.getPosition();
//                InfoWindow infoWindow = new InfoWindow(button, position, -60);
//                baiduMap.showInfoWindow(infoWindow);
//                return true;
//            }
//        });


        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
// 构造定位数据
//        MyLocationData locData = new MyLocationData.Builder()
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(location_lat)
//                .longitude(location_lng).build();
// 设置定位数据
//        baiduMap.setMyLocationData(locData);
// 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
//        mCurrentMarker = BitmapDescriptorFactory
//                .fromResource(R.drawable.icon_geo);
//        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
//        baiduMap.setMyLocationConfiguration();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


    private class MyTransitRouteOverlay extends TransitRouteOverlay {
        /**
         * 构造函数
         *
         * @param baiduMap 该TransitRouteOverlay引用的 BaiduMap 对象
         */
        public MyTransitRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }
    }
}
