package com.example;

import com.utils.HttpUtils;

/**
 * Desc http接口请求测试
 * Created by Michael on 2018/2/7.
 */
public class HttpRequestTest {

    //天气开放API  Get请求测试
    public static String urlWeather = "http://www.sojson.com/open/api/weather/json.shtml";
    public static String urlParamWeather = "city=福州";
    //智能机器人API   Get请求测试
    public static String urlSmart = "http://api.qingyunke.com/api.php";
    public static String urlParamSmart = "key=free&appid=0&msg=你好";

    public static void main(String[] args) {
        String sendGet = HttpUtils.sendGet(urlSmart, urlParamSmart);
        System.out.println(sendGet);

        //待测试完善
//        String sendPost = HttpUtils.sendPost(urlSmart, urlParamSmart);
//        System.out.println(sendPost);
    }
}
