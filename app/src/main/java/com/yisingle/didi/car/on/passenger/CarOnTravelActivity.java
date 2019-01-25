package com.yisingle.didi.car.on.passenger;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mapView = findViewById(R.id.textureMapView);

        initMapView(savedInstanceState, mapView);

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


    public void testRoute(View view) {


    }


    public void move(View view) {


        LatLonPoint end = new LatLonPoint(30.615152, 104.06728);

        List<LatLng> latLngList = TestDataUtils.readLatLngsresume();
        driveRouteView.moveToPoint(latLngList, latLngList.get(latLngList.size() - 1), 6000, false);

        zoomToSpan(latLngList.get(0), latLngList.get(latLngList.size() - 1));

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        driveRouteView.destoryView();
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
                    .newLatLngBounds(bounds, 50));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}

