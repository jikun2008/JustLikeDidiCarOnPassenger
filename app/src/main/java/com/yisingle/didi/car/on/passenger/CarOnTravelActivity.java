package com.yisingle.didi.car.on.passenger;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.yisingle.didi.car.on.passenger.base.BaseMapActivity;
import com.yisingle.didi.car.on.passenger.map.CarTravelOnRouteView;
import com.yisingle.didi.car.on.passenger.test.TestDataUtils;

import java.util.List;


/**
 * @author jikun
 */
public class CarOnTravelActivity extends BaseMapActivity {


    private TextureMapView mapView;

    private CarTravelOnRouteView driveRouteView;

    private Marker startMarker;

    private Marker endMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.textureMapView);

        initMapView(savedInstanceState, mapView);

        initMarker();

        driveRouteView = new CarTravelOnRouteView(getApplicationContext(), getAmap());

        driveRouteView.setOnCarTravelCallBack(new CarTravelOnRouteView.OnCarTravelCallBack() {
            @Override
            public void onDistanceTimeCallBack(float distance, long time) {
                Log.e("CarOnTravelActivity", "CarTravelOnRouteView--ditance=" + distance + "-----time=" + time);
            }

            @Override
            public void onRouteFailed(String info) {

            }
        });

    }

    private void initMarker() {
        MarkerOptions startOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.amap_start));

        MarkerOptions endOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.amap_end));
        startMarker = getAmap().addMarker(startOptions);
        endMarker = getAmap().addMarker(endOptions);
    }


    public void moveByList(View view) {

        //一次传递一个坐标点数组移动
        List<LatLng> moveList = TestDataUtils.readLatLngsresume();

        LatLng start = moveList.get(0);
        LatLng end = moveList.get(moveList.size() - 1);

        startMarker.setPosition(start);
        endMarker.setPosition(end);


        driveRouteView.moveToPoint(moveList, end, 80000, false);

        zoomToSpan(start, end);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        driveRouteView.destoryView();
        startMarker.destroy();
        endMarker.destroy();
    }


    public void zoomToSpan(LatLng startLatLng, LatLng endLatLng) {
        try {
            LatLonPoint start = new LatLonPoint(startLatLng.latitude, startLatLng.longitude);
            LatLonPoint end = new LatLonPoint(endLatLng.latitude, endLatLng.longitude);
            LatLngBounds.Builder b = LatLngBounds.builder();
            b.include(new LatLng(start.getLatitude(), start.getLongitude()));
            b.include(new LatLng(end.getLatitude(), end.getLongitude()));
            LatLngBounds bounds = b.build();
            getAmap().animateCamera(CameraUpdateFactory
                    .newLatLngBounds(bounds, 100));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

