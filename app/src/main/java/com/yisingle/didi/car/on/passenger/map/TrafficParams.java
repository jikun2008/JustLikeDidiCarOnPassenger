package com.yisingle.didi.car.on.passenger.map;

import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.yisingle.didi.car.on.passenger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jikun
 * Created by jikun on 2019/1/23.
 */
public class TrafficParams {

    private BitmapDescriptor defaultRoute = null;
    private BitmapDescriptor unknownTraffic = null;
    private BitmapDescriptor smoothTraffic = null;
    private BitmapDescriptor slowTraffic = null;
    private BitmapDescriptor jamTraffic = null;
    private BitmapDescriptor veryJamTraffic = null;


    public TrafficParams() {
        defaultRoute = BitmapDescriptorFactory.fromResource(R.drawable.amap_route_color_texture_6_arrow);
        smoothTraffic = BitmapDescriptorFactory.fromResource(R.drawable.amap_route_color_texture_4_arrow);
        unknownTraffic = BitmapDescriptorFactory.fromResource(R.drawable.amap_route_color_texture_0_arrow);
        slowTraffic = BitmapDescriptorFactory.fromResource(R.drawable.amap_route_color_texture_3_arrow);
        jamTraffic = BitmapDescriptorFactory.fromResource(R.drawable.amap_route_color_texture_2_arrow);
        veryJamTraffic = BitmapDescriptorFactory.fromResource(R.drawable.amap_route_color_texture_9_arrow);
    }


    /**
     * getBitmapTexturesList 返回的图片顺序 与  getTrafficBitmapDescriptorInt的下标是一致的
     *
     * @return 交通状态的图片数组
     */
    public List<BitmapDescriptor> getBitmapTexturesList() {
        List<BitmapDescriptor> list = new ArrayList<>();
        //对应   下标为0时对应 "畅通"
        list.add(smoothTraffic);
        //对应   下标为0时对应 "缓行"
        list.add(slowTraffic);
        //对应   下标为0时对应 "拥堵"
        list.add(jamTraffic);
        //对应   下标为0时对应 "严重拥堵"
        list.add(veryJamTraffic);
        //对应   下标为0时对应 "默认"
        list.add(defaultRoute);
        return list;
    }

    /**
     * @param status 交通状态
     * @return 返回交通状态图片下标 与getBitmapTexturesList 一一对应
     */
    public int getTrafficBitmapDescriptorInt(String status) {
        if (status.equals("畅通")) {
            return 0;
        } else if (status.equals("缓行")) {
            return 1;
        } else if (status.equals("拥堵")) {
            return 2;
        } else if (status.equals("严重拥堵")) {
            return 3;
        } else {
            return 4;
        }
    }
}
