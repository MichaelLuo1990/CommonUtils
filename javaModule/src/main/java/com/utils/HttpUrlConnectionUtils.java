package com.utils;

import com.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc http请求测试
 * extends HttpRequestImplMode - http实现方式{
 *     Java相关 ：Apache（HTTPClient/HttpGet/HttpPost）（deprecated）、JDK-java.net包（Urlconnection）)
 *     Android相关 ： handle（message）+thread（runnable）、AsyncTask
 *     第三方框架： OKhttp etc...
 *     详情见HttpRequestImplMode测试project
 * }
 * Created by Michael on 2018/2/7.
 */
public class HttpUrlConnectionUtils {

    static ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     *
     * @param urlStr 请求地址
     * @param cls 返回对象
     * @param listener 回调监听接口
     * @param <T> 泛形参数
     */
    public static <T> void get(final String urlStr, final Class<T> cls, final HttpCallbackListener listener) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(urlStr); // 根据URL地址创建URL对象
                    httpURLConnection = (HttpURLConnection) url.openConnection();// 获取HttpURLConnection对象
                    httpURLConnection.setRequestMethod("GET");// 设置请求方式，默认为GET
                    httpURLConnection.setConnectTimeout(5000);// 设置连接超时
                    httpURLConnection.setReadTimeout(8000);// 设置读取超时
                    // 响应码为200表示成功，否则失败。
                    if (httpURLConnection.getResponseCode() == 200) {
                        InputStream is = httpURLConnection.getInputStream(); // 获取网络的输入流
                        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = bf.readLine()) != null) {
                            buffer.append(line);
                        }
                        bf.close();
                        is.close();
                        listener.onSuccess(cls);
                    } else {
                        if (listener != null) {
                            listener.onError(new Exception("response err code:" +
                                    httpURLConnection.getResponseCode()));
                        }
                    }
                } catch (MalformedURLException e) {
                    if (listener != null) {
                        listener.onError(new Exception(e));
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(new Exception(e));
                    }
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();// 释放资源
                    }
                }
            }
        });
    }

    /**
     * get请求
     * @param url 发送请求的URL
     * @param param 请求参数 name1=value1&name2=value2 的形式
     * @return result 字符串
     */
    public static String get(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl;
            if(param != null && !param.isEmpty() && !param.equals("")) {
                String urlNameString = url + "?" + param;
                realUrl = new URL(urlNameString);
            } else {
                realUrl = new URL(url);
            }
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * GET方法 返回数据会解析成cls对象
     *
     * @param urlStr   请求的路径
     * @param params   参数列表
     * @param cls      对象
     * @param listener 回调监听
     * @param <T>      监听泛型
     */
    public static <T> void post(final String urlStr, final Map<String, Object> params, final Class<T> cls, final HttpCallbackListener listener) {
        final StringBuffer paramsStr = new StringBuffer();
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry element = (Map.Entry) it.next();
            paramsStr.append(element.getKey());
            paramsStr.append("=");
            paramsStr.append(element.getValue());
            paramsStr.append("&");
        }
        if (paramsStr.length() > 0) {
            paramsStr.deleteCharAt(paramsStr.length() - 1);
        }
        // 因为网络请求是耗时操作，所以需要另外开启一个线程来执行该任务。
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(urlStr);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
                    printWriter.write(paramsStr.toString());
                    printWriter.flush();
                    printWriter.close();
                    if (httpURLConnection.getResponseCode() == 200) {
                        // 获取网络的输入流
                        InputStream is = httpURLConnection.getInputStream();
                        BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                        //最好在将字节流转换为字符流的时候 进行转码
                        StringBuffer buffer = new StringBuffer();
                        String line = "";
                        while ((line = bf.readLine()) != null) {
                            buffer.append(line);
                        }
                        bf.close();
                        is.close();
                        listener.onSuccess(buffer.toString());
                    } else {
                        listener.onError(new Exception("response err code:" +
                                httpURLConnection.getResponseCode()));
                    }
                } catch (MalformedURLException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        });
    }

    /**
     * post请求
     * @param url 发送请求的 URL
     * @param param 请求参数 name1=value1&name2=value2 的形式
     * @return result 字符串
     */
    public static String post(String url, String param) {
        String result = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

}
