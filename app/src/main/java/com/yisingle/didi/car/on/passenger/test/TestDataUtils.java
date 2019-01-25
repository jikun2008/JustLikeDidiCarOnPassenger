package com.yisingle.didi.car.on.passenger.test;

import com.amap.api.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jikun
 * Created by jikun on 2018/4/27.
 */
public class TestDataUtils {

    private static double[] coordsall = {
            30.55184472222222, 104.06796444444444,
            30.551818611111113, 104.06715277777778,
            30.551818611111113, 104.06715277777778,
            30.552443611111112, 104.06714416666667,
            30.55286027777778, 104.0671225,
            30.55291666666667, 104.06711805555555,
            30.5529775, 104.067105,
            30.553111944444446, 104.06707888888889,
            30.554270833333334, 104.0668011111111,
            30.554370555555554, 104.06679694444445,
            30.555920277777776, 104.06671444444444,
    };


    private static double[] coordsnow = {
            30.55184472222222, 104.06796444444444,
            30.551818611111113, 104.06715277777778,
            30.551818611111113, 104.06715277777778,
            30.552443611111112, 104.06714416666667,
            30.55286027777778, 104.0671225,
    };


    private static double[] coordsresume = {
            30.55291666666667, 104.06711805555555,
            30.5529775, 104.067105,
            30.553111944444446, 104.06707888888889,
            30.554270833333334, 104.0668011111111,
            30.554370555555554, 104.06679694444445,
            30.555920277777776, 104.06671444444444
    };


    private static double[] coordsallLong = {
            30.55184472222222, 104.06796444444444,
            30.551818611111113, 104.06715277777778,
            30.551818611111113, 104.06715277777778,
            30.552443611111112, 104.06714416666667,
            30.55286027777778, 104.0671225,
            30.55291666666667, 104.06711805555555,
            30.5529775, 104.067105,
            30.553111944444446, 104.06707888888889,
            30.554270833333334, 104.0668011111111,
            30.554370555555554, 104.06679694444445,
            30.555920277777776, 104.06671444444444,
            30.555976666666666, 104.06671444444444,
            30.55893222222222, 104.06665361111111,
            30.55893222222222, 104.06659277777777,
            30.55893222222222, 104.06659277777777,
            30.5588975, 104.0646613888889,
            30.558867222222222, 104.06287333333333,
            30.558884444444445, 104.0628125,
            30.558884444444445, 104.0625,
            30.55886277777778, 104.06060777777778,
            30.558828055555555, 104.05870666666667,
            30.558828055555555, 104.05864583333333,
            30.5588325, 104.05765194444444,
            30.55884111111111, 104.05680111111111,
            30.55884111111111, 104.05680111111111,
            30.560282222222224, 104.05679694444444,
            30.560412222222222, 104.05679694444444,
            30.561089444444445, 104.05678388888889,
            30.561319444444443, 104.05678833333333,
            30.56150166666667, 104.05681,
            30.56396277777778, 104.05741333333333,
            30.56901472222222, 104.05864583333333,
            30.56907111111111, 104.05866333333333,
            30.570299444444444, 104.05895833333334,
            30.570841944444446, 104.05911472222222,
            30.571071944444444, 104.05917527777778,
            30.57181, 104.05935333333333,
            30.57186638888889, 104.05920583333334,
            30.57186638888889, 104.05920583333334,
            30.571835833333335, 104.05759972222222,
            30.571831666666668, 104.05744805555555,
            30.571823055555555, 104.05712666666666,
            30.571853333333333, 104.05663638888889,
            30.571896666666667, 104.05627611111112,
            30.572061666666666, 104.05555111111111,
            30.57226138888889, 104.05485666666667,
            30.57226138888889, 104.05485666666667,
            30.573533055555554, 104.05480916666667,
            30.574236111111112, 104.05480027777777,
            30.574948055555556, 104.05477861111112,
            30.588524444444445, 104.06025611111112,
            30.5886675, 104.06025611111112,
            30.590108611111113, 104.06027777777778,
            30.590490555555554, 104.06027333333333,
            30.59145388888889, 104.06030388888888,
            30.593081666666667, 104.06034277777778,
            30.594240555555555, 104.06035166666666,
            30.594305555555554, 104.06035583333333,
            30.595034722222223, 104.06038638888889,
            30.595885555555554, 104.060395,
            30.595885555555554, 104.060395,
            30.596766388888888, 104.06041222222223,
            30.596922777777777, 104.06041222222223,
            30.598702222222222, 104.06043833333334,
            30.59963972222222, 104.06043416666667,
            30.60056861111111, 104.06042527777778,
            30.60237861111111, 104.06050361111112,
            30.602990555555557, 104.06053388888888,
            30.603103333333333, 104.06053833333333,
            30.603975833333333, 104.06058166666666,
            30.6040625, 104.06058166666666,
            30.604900277777777, 104.06059888888889,
            30.605273333333333, 104.06060777777778,
            30.607183055555556, 104.06065111111111,
            30.607908055555555, 104.06062944444444,
            30.608498333333333, 104.060625,
            30.608498333333333, 104.060625,
            30.608498333333333, 104.06135416666666,
            30.60846777777778, 104.0625,
            30.60846777777778, 104.06251305555556,
            30.608450555555557, 104.0636761111111,
            30.608459166666666, 104.06413194444444,
            30.608459166666666, 104.06478305555555,
            30.60844611111111, 104.06571194444444,
            30.608459166666666, 104.06589416666667,
            30.608493888888887, 104.06621527777777,
            30.608515555555556, 104.06731333333333,
            30.608654444444443, 104.067105,
            30.608654444444443, 104.067105,
            30.608741388888888, 104.06698361111111,
            30.608732777777778, 104.0663975,
            30.608745555555554, 104.06588111111111,
            30.608771666666666, 104.06421,
            30.608706666666667, 104.0640711111111,
            30.608698055555557, 104.06404083333334,
            30.60872388888889, 104.0625,
            30.608815, 104.05560333333334,
            30.608789166666668, 104.05514333333333,
            30.608719722222222, 104.05459194444444,
            30.60861111111111, 104.05412333333334,
            30.6085025, 104.0537586111111,
            30.608355, 104.0533725,
            30.608090277777777, 104.05282555555556,
            30.607925277777777, 104.05255222222222,
            30.607908055555555, 104.05253027777778,
            30.607708333333335, 104.05223972222223,
            30.60602, 104.05011277777778
    };


    private static double[] coordsnowLong = {
            30.55184472222222, 104.06796444444444,
            30.551818611111113, 104.06715277777778,
            30.551818611111113, 104.06715277777778,
            30.552443611111112, 104.06714416666667,
            30.55286027777778, 104.0671225,
            30.55291666666667, 104.06711805555555,
            30.5529775, 104.067105,
            30.553111944444446, 104.06707888888889,
            30.554270833333334, 104.0668011111111,
            30.554370555555554, 104.06679694444445,
            30.555920277777776, 104.06671444444444,
            30.555976666666666, 104.06671444444444,
            30.55893222222222, 104.06665361111111,
            30.55893222222222, 104.06659277777777,
            30.55893222222222, 104.06659277777777,
            30.5588975, 104.0646613888889,
            30.558867222222222, 104.06287333333333,
            30.558884444444445, 104.0628125,
            30.558884444444445, 104.0625,
            30.55886277777778, 104.06060777777778,
            30.558828055555555, 104.05870666666667,
            30.558828055555555, 104.05864583333333,
            30.5588325, 104.05765194444444,
            30.55884111111111, 104.05680111111111,
            30.55884111111111, 104.05680111111111,
            30.560282222222224, 104.05679694444444,
            30.560412222222222, 104.05679694444444,
            30.561089444444445, 104.05678388888889,
            30.561319444444443, 104.05678833333333,
            30.56150166666667, 104.05681,
            30.56396277777778, 104.05741333333333,
            30.56901472222222, 104.05864583333333,
            30.56907111111111, 104.05866333333333,
            30.570299444444444, 104.05895833333334,
            30.570841944444446, 104.05911472222222,
            30.571071944444444, 104.05917527777778,
            30.57181, 104.05935333333333,
            30.57186638888889, 104.05920583333334,
            30.57186638888889, 104.05920583333334,
            30.571835833333335, 104.05759972222222,
            30.571831666666668, 104.05744805555555,
            30.571823055555555, 104.05712666666666,
            30.571853333333333, 104.05663638888889,
            30.571896666666667, 104.05627611111112,
            30.572061666666666, 104.05555111111111,
            30.57226138888889, 104.05485666666667,
            30.57226138888889, 104.05485666666667,
            30.573533055555554, 104.05480916666667,
            30.574236111111112, 104.05480027777777,
            30.574948055555556, 104.05477861111112
    };


    private static double[] coordsresumeLong = {
            30.588524444444445, 104.06025611111112,
            30.5886675, 104.06025611111112,
            30.590108611111113, 104.06027777777778,
            30.590490555555554, 104.06027333333333,
            30.59145388888889, 104.06030388888888,
            30.593081666666667, 104.06034277777778,
            30.594240555555555, 104.06035166666666,
            30.594305555555554, 104.06035583333333,
            30.595034722222223, 104.06038638888889,
            30.595885555555554, 104.060395,
            30.595885555555554, 104.060395,
            30.596766388888888, 104.06041222222223,
            30.596922777777777, 104.06041222222223,
            30.598702222222222, 104.06043833333334,
            30.59963972222222, 104.06043416666667,
            30.60056861111111, 104.06042527777778,
            30.60237861111111, 104.06050361111112,
            30.602990555555557, 104.06053388888888,
            30.603103333333333, 104.06053833333333,
            30.603975833333333, 104.06058166666666,
            30.6040625, 104.06058166666666,
            30.604900277777777, 104.06059888888889,
            30.605273333333333, 104.06060777777778,
            30.607183055555556, 104.06065111111111,
            30.607908055555555, 104.06062944444444,
            30.608498333333333, 104.060625,
            30.608498333333333, 104.060625,
            30.608498333333333, 104.06135416666666,
            30.60846777777778, 104.0625,
            30.60846777777778, 104.06251305555556,
            30.608450555555557, 104.0636761111111,
            30.608459166666666, 104.06413194444444,
            30.608459166666666, 104.06478305555555,
            30.60844611111111, 104.06571194444444,
            30.608459166666666, 104.06589416666667,
            30.608493888888887, 104.06621527777777,
            30.608515555555556, 104.06731333333333,
            30.608654444444443, 104.067105,
            30.608654444444443, 104.067105,
            30.608741388888888, 104.06698361111111,
            30.608732777777778, 104.0663975,
            30.608745555555554, 104.06588111111111,
            30.608771666666666, 104.06421,
            30.608706666666667, 104.0640711111111,
            30.608698055555557, 104.06404083333334,
            30.60872388888889, 104.0625,
            30.608815, 104.05560333333334,
            30.608789166666668, 104.05514333333333,
            30.608719722222222, 104.05459194444444,
            30.60861111111111, 104.05412333333334,
            30.6085025, 104.0537586111111,
            30.608355, 104.0533725,
            30.608090277777777, 104.05282555555556,
            30.607925277777777, 104.05255222222222,
            30.607908055555555, 104.05253027777778,
            30.607708333333335, 104.05223972222223,
            30.60602, 104.05011277777778


    };


    private static double[] moveCoords = {
            30.55184472222222, 104.06796444444444,
            30.551818611111113, 104.06715277777778,
            30.551818611111113, 104.06715277777778,
            30.552443611111112, 104.06714416666667,
            30.55286027777778, 104.0671225,
            30.55291666666667, 104.06711805555555,
            30.5529775, 104.067105,
            30.553111944444446, 104.06707888888889,
            30.554270833333334, 104.0668011111111,
            30.554370555555554, 104.06679694444445,
            30.555920277777776, 104.06671444444444,
            30.555976666666666, 104.06671444444444,
            30.55893222222222, 104.06665361111111,
            30.55893222222222, 104.06659277777777,
            30.55893222222222, 104.06659277777777,
            30.5588975, 104.0646613888889,
            30.558867222222222, 104.06287333333333,
            30.558884444444445, 104.0628125,
            30.558884444444445, 104.0625,
            30.55886277777778, 104.06060777777778,
            30.558828055555555, 104.05870666666667,
            30.558828055555555, 104.05864583333333,
            30.5588325, 104.05765194444444,
            30.55884111111111, 104.05680111111111,
            30.55884111111111, 104.05680111111111,
            30.560282222222224, 104.05679694444444,
            30.560412222222222, 104.05679694444444,
            30.561089444444445, 104.05678388888889,
            30.561319444444443, 104.05678833333333,
            30.56150166666667, 104.05681,
            30.56396277777778, 104.05741333333333,
            30.56901472222222, 104.05864583333333,
            30.56907111111111, 104.05866333333333,
            30.570299444444444, 104.05895833333334,
            30.570841944444446, 104.05911472222222,
            30.571071944444444, 104.05917527777778,
            30.57181, 104.05935333333333,
            30.57186638888889, 104.05920583333334,
            30.57186638888889, 104.05920583333334,
            30.571835833333335, 104.05759972222222,
            30.571831666666668, 104.05744805555555,
            30.571823055555555, 104.05712666666666,
            30.571853333333333, 104.05663638888889,
            30.571896666666667, 104.05627611111112,
            30.572061666666666, 104.05555111111111,
            30.57226138888889, 104.05485666666667,
            30.57226138888889, 104.05485666666667,
            30.573533055555554, 104.05480916666667,
            30.574236111111112, 104.05480027777777,
            30.574948055555556, 104.05477861111112
    };


    public static List<LatLng> readLatLngsAll() {
        List<LatLng> points = new ArrayList<>();
        for (int i = 0; i < coordsall.length; i += 2) {
            points.add(new LatLng(coordsall[i], coordsall[i + 1]));
        }
        return points;
    }

    public static List<LatLng> readLatLngsnow() {
        List<LatLng> points = new ArrayList<>();
        for (int i = 0; i < coordsnowLong.length; i += 2) {
            points.add(new LatLng(coordsnowLong[i], coordsnowLong[i + 1]));
        }
        return points;
    }


    public static List<LatLng> readLatLngsresume() {

        List<LatLng> points = new ArrayList<>();
        for (int i = 0; i < coordsresumeLong.length; i += 2) {
            points.add(new LatLng(coordsresumeLong[i], coordsresumeLong[i + 1]));
        }
        return points;
    }


    public static List<LatLng> readLatLngscarMove() {

        List<LatLng> points = new ArrayList<>();
        for (int i = 0; i < moveCoords.length; i += 2) {
            points.add(new LatLng(moveCoords[i], moveCoords[i + 1]));
        }
        return points;
    }


}