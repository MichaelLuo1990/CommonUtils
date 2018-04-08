package com.michael.commonutils.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.DeviceInfoUtils;

/**
 * Desc 设备操作系统信息测试
 * Created by Michael on 2018/3/21.
 */

public class DeviceInfoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
    }

    /**
     * 获取联网IP地址（移动网络 or WiFi）
     *
     * @param view
     */
    public void getIPAddressClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_ip_address);
        textView.setText("ip地址：" + DeviceInfoUtils.getIPAddress(this));
    }

    /**
     * 设备是否root
     *
     * @param view
     */
    public void isRootedClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_root_status);
        if (DeviceInfoUtils.isRooted()) {
            textView.setText("当前设备已获取root权限");
        } else {
            textView.setText("当前设备未获取root权限");
        }
    }

    /**
     * 获取设备版本号与版本名称
     *
     * @param view
     */
    public void getSysSdkCodeNameClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_sys_code_name);
        textView.setText("SDK版本号：" + DeviceInfoUtils.getSDKVersionCode() + " / " + "SDK版本名：" + DeviceInfoUtils.getSDKVersionName());
    }

    /**
     * 获取手机IMEI号、手机厂商、手机型号、手机系统版本号
     *
     * @param view
     */
    public void getImeiManuTypeSysInfoClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_imei_manu_type_sys_info);
        textView.setText("IMEI号：" + DeviceInfoUtils.getDeviceIMEI(this) + " / " +
                "手机厂商：" + DeviceInfoUtils.getDeviceManufacturer() + " / " +
                "手机型号：" + DeviceInfoUtils.getDeviceModel() + " / " +
                "手机系统版本号：" + DeviceInfoUtils.getDeviceSystemVersion());
    }

    /**
     * 判断是否为指定设备
     *
     * @param view
     */
    public void isTargetDeviceClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_is_target_device);
        if (DeviceInfoUtils.isHuawei()) {
            textView.setText("该手机为华为手机");
        } else if (DeviceInfoUtils.isMeiZu()) {
            textView.setText("该手机为魅族手机");
        } else if (DeviceInfoUtils.isSamsung()) {
            textView.setText("该手机为三星手机");
        } else if (DeviceInfoUtils.isXiaoMi()) {
            textView.setText("该手机为小米手机");
        } else if (DeviceInfoUtils.isLenovo()) {
            textView.setText("该手机为联想手机");
        }
    }

    /**
     * 获取设备信息（详细）
     *
     * @param view
     */
    public void getDeviceInfoClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_device_info);
        textView.setText("设备信息：" + DeviceInfoUtils.getDeviceInfo());
    }

    /**
     * 获取CPU信息
     *
     * @param view
     */
    public void getCPUInfoClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_cpu_info);
        textView.setText("CPU信息(型号/频率)：" +  DeviceInfoUtils.getCpuInfo() + " / " + "CPU-ABI是否兼容：" + DeviceInfoUtils.hasCompatibleCPU());
    }

    /**
     * 获取内存信息
     *
     * @param view
     */
    public void getMemoryInfoClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_memory_info);
        textView.setText("总内存大小(/proc/meminfo)：" + DeviceInfoUtils.getMemTotal() + "字节（byte）" + "可用内（运）存容量：" + DeviceInfoUtils.getRAMAvailableSize(this) + "总内（运）存容量：" +  DeviceInfoUtils.getRAMTotalSize(this));
    }

}
