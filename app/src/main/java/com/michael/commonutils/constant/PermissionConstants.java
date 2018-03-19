package com.michael.commonutils.constant;

import android.Manifest;

/**
 * Desc 权限管理（常用）
 * Created by Michael on 2018/1/30.
 */

public class PermissionConstants {

    public static final int REQUEST_CODE_PERMISSION = 1;//请求单个权限返回码（相机为例）
    public static final int REQUEST_CODE_PERMISSIONS = 2;//请求多个权限返回码
    public static final String PERMISSION = Manifest.permission.CAMERA;//单个权限
    //多个权限
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,//允许程序通过访问网络来大致确定自己设备的位置，如通过wifi或是蜂窝网络
            Manifest.permission.ACCESS_FINE_LOCATION,//允许通过访问信息源来精确的获得设备的地理位置，如功过GPS,wifi或是蜂窝网络(同上，推荐使用这个or两个同时添加)
            Manifest.permission.ACCESS_NETWORK_STATE,//允许程序访问有关GSM网络信息
            Manifest.permission.ACCESS_WIFI_STATE,//允许程序访问Wi-Fi网络状态信息
            Manifest.permission.INTERNET,//允许程序打开网络套接字
            Manifest.permission.READ_PHONE_STATE,//读取电话状态
            Manifest.permission.WRITE_EXTERNAL_STORAGE,//写入外部储存SD卡
            Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,//允许应用程序访问额外的位置提供命令
            Manifest.permission.READ_EXTERNAL_STORAGE,//读取外部存储SD卡
            Manifest.permission.READ_CALENDAR,//允许程序读取用户日历数据
            Manifest.permission.CAMERA//请求访问使用照相设备
    };

    /**
     * other
     * 允许应用程序连接到已经配对的蓝牙设备上
     * Manifest.permission.BLUETOOTH
     * 允许应用程序能够发现和配对蓝牙设备
     * Manifest.permission.BLUETOOTH_ADMIN
     * 允许应用不通过启动电话的键盘输入界面而直接打电话
     * Manifest.permission.CALL_PHONE
     * 能拨打任何电话号码，而不通过号码键盘
     * Manifest.permission.CALL_PRIVILEGED
     * 允许一个应用程序获取电池使用的统计信息(剩余电量、电池的耗电情况(各主要应用程序耗电占总耗电的百分比等)等
     * Manifest.permission.BATTERY_STATS
     * 开启NFC权限
     * Manifest.permission.NFC
     * ...
     * ref API：http://www.android-doc.com/reference/android/Manifest.permission.html
     */

}
