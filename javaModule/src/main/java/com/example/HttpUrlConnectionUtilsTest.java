package com.example;

import com.HttpCallbackListener;
import com.utils.HttpUrlConnectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc http接口请求测试
 * Created by Michael on 2018/2/7.
 */
public class HttpUrlConnectionUtilsTest {

    //天气开放API  Get请求测试
    public static String getUrlWeather = "http://www.weather.com.cn/data/cityinfo/101190408.html";
    public static String urlWeather = "http://www.sojson.com/open/api/weather/json.shtml";
    public static String urlParamWeather = "city=福州";
    //智能机器人API   Get请求测试
    public static String urlSmart = "http://api.qingyunke.com/api.php";
    public static String urlParamSmart = "key=free&appid=0&msg=你好";

    public static void main(String[] args) {
        System.out.println("===========================json字符串返回==================================");
        String sendGet = HttpUrlConnectionUtils.get(getUrlWeather, null);
        System.out.println(sendGet);
        String sendPost = HttpUrlConnectionUtils.post(urlSmart, urlParamSmart);
        System.out.println(sendPost);

        System.out.println("===========================泛型对象返回==================================");
        HttpUrlConnectionUtils.get("http://www.weather.com.cn/data/cityinfo/101190408.html", String.class, new HttpCallbackListener() {
            @Override
            public void onSuccess(Object response) {
                System.out.println(response.toString());
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.toString());
            }
        });

        Map<String,Object> getParams = new HashMap<>();
        getParams.put("key", "free");
        getParams.put("appid", "0");
        getParams.put("msg", "天气");
        HttpUrlConnectionUtils.post("http://api.qingyunke.com/api.php", getParams, String.class, new HttpCallbackListener() {
            @Override
            public void onSuccess(Object response) {
                System.out.println(response.toString());
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.toString());
            }
        });

    }
}
