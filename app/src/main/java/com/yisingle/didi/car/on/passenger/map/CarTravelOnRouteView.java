package com.yisingle.didi.car.on.passenger.map;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.TMC;
import com.amap.api.services.route.WalkRouteResult;
import com.yisingle.didi.car.on.passenger.R;
import com.yisingle.didi.car.on.passenger.utils.DistanceUtils;
import com.yisingle.didi.car.on.passenger.utils.MoveUtils;
import com.yisingle.didi.car.on.passenger.utils.PointsUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jikun
 * Created by jikun on 2019/1/22.
 */
public class CarTravelOnRouteView {


    private Polyline polyline;

    private Marker carMarker;

    private AMap aMap;

    private Context context;

    private TrafficParams trafficParams;

    private LatLng end;

    MoveUtils moveUtils;

    private float speed = 1;

    private OnCarTravelCallBack onCarTravelCallBack;

    private @RouteSearchState
    int routeSearchState = RouteSearchState.SearchFailed;

    public AMap getAmap() {
        return aMap;
    }


    public CarTravelOnRouteView(@NonNull Context context, @NonNull AMap aMap) {
        this(context, aMap, null);
    }

    public CarTravelOnRouteView(@NonNull Context context, @NonNull AMap aMap, LatLng end) {
        this.context = context;
        this.aMap = aMap;
        this.end = end;
        trafficParams = new TrafficParams();
        MarkerOptions markerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.move_car))
                .anchor(0.5f, 0.5f);
        carMarker = getAmap().addMarker(markerOptions);
    }


    public void moveToPoint(LatLng latLng) {
        List<LatLng> list = new ArrayList<>();
        list.add(latLng);
        moveToPoint(list, this.end, 500, true);
    }

    public void moveToPoint(LatLng latLng, final LatLng endLatLng, long time) {

        List<LatLng> list = new ArrayList<>();
        list.add(latLng);
        moveToPoint(list, endLatLng, time, true);

    }

    /**
     * @param moveLatLngList 移动的坐标点集合
     * @param endLatLng      终点坐标
     * @param time           时间 毫秒1000
     * @param isContinue     是否从加到上次未完成的点。
     */
    public void moveToPoint(List<LatLng> moveLatLngList, final LatLng endLatLng, long time, boolean isContinue) {
        this.end = endLatLng;
        if (null != moveUtils) {
            moveUtils.stop();
            moveUtils.destory();
            moveUtils = null;
        }
        if (routeSearchState == RouteSearchState.Searching) {
            Log.e(CarTravelOnRouteView.class.getSimpleName(), "searching so renturn do nothing");
            return;
        }
        moveUtils = new MoveUtils();
        moveUtils.setCallBack(new MoveUtils.OnCallBack() {
            @Override
            public void onSetLatLng(LatLng latLng, float rotate) {
                updateCarMarkPosition(latLng, rotate);

                switch (routeSearchState) {
                    case RouteSearchState.Searching:

                        break;
                    case RouteSearchState.SearchSuccess:

                        List<LatLng> latLngList = getAllLatLngPoints();
                        Pair<Integer, LatLng> latLngPair = PointsUtil.calShortestDistancePoint(latLngList, latLng);
                        if (null != latLngPair && null != latLngPair.second) {
                            float distance = AMapUtils.calculateLineDistance(latLngPair.second, latLng);

                            if (distance <= 50) {
                                updatePolyLineAfterPass(latLngList, latLng);
                            } else {
                                search(latLng, end);
                            }
                        }
                        break;
                    case RouteSearchState.SearchFailed:

                        search(latLng, end);
                        break;
                    default:
                        break;
                }

            }
        });
        moveUtils.startMove(moveLatLngList, time, isContinue);

    }

    /**
     * 设置终点坐标
     *
     * @param end
     */
    public void setEnd(LatLng end) {
        this.end = end;
    }

    public void stopMove() {
        if (null != moveUtils) {
            moveUtils.stop();
            moveUtils.destory();
            moveUtils = null;
        }
    }

    public void destoryView() {
        onCarTravelCallBack = null;
        stopMove();
        if (null != carMarker) {
            carMarker.destroy();
        }


    }


    private void updatePolyLineAfterPass(List latLngList, LatLng latLng) {

        Pair<Integer, LatLng> latLngPair = PointsUtil.calShortestDistancePoint(latLngList, latLng);
        List<Integer> integerList = getCustomTextureIndexList();
        if (latLngPair.first >= 1) {
            for (int i = 0; i < latLngPair.first; i++) {
                if (latLngList.size() > 0) {
                    latLngList.remove(0);
                }
                if (integerList.size() > 0) {
                    integerList.remove(0);
                }
            }

            if (null != polyline) {
                polyline.setPoints(latLngList);
                polyline.setCustemTextureIndex(integerList);
            }
        } else if (latLngPair.first == 0) {
            if (latLngList.size() > 0) {
                latLngList.set(0, latLngPair.second);
                if (null != polyline) {
                    polyline.setPoints(latLngList);
                    polyline.setCustemTextureIndex(integerList);
                }
            }
        }
        if (null != onCarTravelCallBack) {
            float distance = DistanceUtils.calcaulateListDistanceLatLng(latLngList);


            long time = (long) (distance / speed);
            onCarTravelCallBack.onDistanceTimeCallBack(distance, time);
        }


    }

    private void search(@NonNull LatLng start, @NonNull LatLng end) {
        LatLonPoint stratLatLonPoint = new LatLonPoint(start.latitude, start.longitude);
        LatLonPoint endLatLonPoint = new LatLonPoint(end.latitude, end.longitude);
        routeSearchState = RouteSearchState.Searching;
        RouteSearch routeSearch = new RouteSearch(context);
        routeSearch.setRouteSearchListener(new RouteSearch.OnRouteSearchListener() {
            @Override
            public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

            }

            @Override
            public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int code) {

                if (code == 1000) {


                    if (null != driveRouteResult && null != driveRouteResult.getPaths() && driveRouteResult.getPaths().size() > 0) {
                        //根据DrivePath 得到 所有的DriveStupz中所有的 TMC 集合数组
                        //根据TMC集合数组 来组装数据
                        DrivePath drivePath = driveRouteResult.getPaths().get(0);
                        speed = drivePath.getDuration() / drivePath.getDuration();
                        drawLine(drivePath);
                        routeSearchState = RouteSearchState.SearchSuccess;
                        if (null != onCarTravelCallBack) {
                            //距离和时间 callBack
                            onCarTravelCallBack.onDistanceTimeCallBack(drivePath.getDistance(), drivePath.getDuration());
                        }
                    }


                } else {
                    routeSearchState = RouteSearchState.SearchFailed;
                    if (null != onCarTravelCallBack) {
                        onCarTravelCallBack.onRouteFailed("车辆行驶路径规划失败:" + code);
                    }
                }
            }

            @Override
            public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

            }

            @Override
            public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

            }
        });

        RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(stratLatLonPoint, endLatLonPoint);
        RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, RouteSearch.DRIVING_SINGLE_SHORTEST, null, null, "");
        routeSearch.calculateDriveRouteAsyn(query);
    }

    /**
     * 更新车辆位置
     *
     * @param latLng 车辆位置
     * @param rotate 车辆角度
     */
    private void updateCarMarkPosition(LatLng latLng, float rotate) {
        if (null != carMarker && !carMarker.isRemoved()) {
            carMarker.setPosition(latLng);
            //车辆方向
            float carDirection = 360.0F - rotate + getAmap().getCameraPosition().bearing;
            carMarker.setRotateAngle(carDirection);
        }
    }


    private void drawLine(DrivePath drivePath) {
        if (null != polyline) {
            polyline.remove();
            polyline = null;
        }

        //所有TMC数据  交通数组集合
        List<TMC> tmcs = new ArrayList<>();
        //组装数据
        List<DriveStep> driveStepList = drivePath.getSteps();
        for (int i = 0; i < driveStepList.size(); i++) {
            DriveStep step = driveStepList.get(i);
            tmcs.addAll(step.getTMCs());
        }
        //polyLineOption添加数据
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(30);


        //放用一个数组来存纹理数组
        List<BitmapDescriptor> texturesList = trafficParams.getBitmapTexturesList();

        //设置分段纹理index数组
        List<Integer> texIndexList = new ArrayList<>();


        List<LatLng> allLatLngPoints = new ArrayList<>();

        if (tmcs.size() > 0 && tmcs.get(0).getPolyline().size() > 0) {
            allLatLngPoints.add(new LatLng(tmcs.get(0).getPolyline().get(0).getLatitude(), tmcs.get(0).getPolyline().get(0).getLongitude()));
            texIndexList.add(0);
        }
        //根据交通状态来向texturesList和texIndexList添加数据
        for (int i = 0; i < tmcs.size(); i++) {
            TMC tmc = tmcs.get(i);
            String status = tmc.getStatus();
            //获取纹理数组对应的交通状态 数组下标
            int textIndex = trafficParams.getTrafficBitmapDescriptorInt(status);

            for (int j = 1; j < tmc.getPolyline().size(); j++) {
                texIndexList.add(textIndex);
                allLatLngPoints.add(new LatLng(tmc.getPolyline().get(j).getLatitude(), tmc.getPolyline().get(j).getLongitude()));
            }

        }

        texIndexList.add(0);

        polylineOptions.setPoints(allLatLngPoints);

        polylineOptions.lineCapType(PolylineOptions.LineCapType.LineCapRound);

        //加入对应的颜色,使用setCustomTextureList 即表示使用多纹理；
        polylineOptions.setCustomTextureList(texturesList);
        //设置纹理对应的Index
        polylineOptions.setCustomTextureIndex(texIndexList);

        polyline = getAmap().addPolyline(polylineOptions);


    }


    public void setOnCarTravelCallBack(OnCarTravelCallBack onCarTravelCallBack) {
        this.onCarTravelCallBack = onCarTravelCallBack;
    }

    public List<Integer> getCustomTextureIndexList() {
        return null != polyline ? polyline.getOptions().getCustomTextureIndex() : new ArrayList<Integer>();
    }

    public List<LatLng> getAllLatLngPoints() {
        return null != polyline ? polyline.getPoints() : new ArrayList<LatLng>();
    }


    //添加支持注解的依赖到你的项目中，需要在build.gradle文件中的依赖块中添加：
    //dependencies { compile 'com.android.support:support-annotations:24.2.0' }
    @IntDef({RouteSearchState.Searching, RouteSearchState.SearchSuccess, RouteSearchState.SearchFailed})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RouteSearchState {
        int Searching = 1;
        int SearchSuccess = 2;
        int SearchFailed = 3;

    }

    public interface OnCarTravelCallBack {

        void onDistanceTimeCallBack(float distance, long time);

        void onRouteFailed(String info);

    }


}
