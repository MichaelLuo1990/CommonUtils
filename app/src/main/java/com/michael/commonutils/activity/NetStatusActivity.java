package com.michael.commonutils.activity;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.NetStatusUtils;

/**
 * Desc 网络状态判断测试
 * Created by Michael on 2018/3/19.
 */

public class NetStatusActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_status);
    }

    public void getNetStatusInfoClick(View view) {
        String s = "";
        if(NetStatusUtils.isNetworkAvailable(this)){
            if(NetStatusUtils.isWifiConnection(this) && NetStatusUtils.getNetworkType(this) == ConnectivityManager.TYPE_WIFI) {
                s += "当前网络可用，为WiFi接入网络";
            } else if(NetStatusUtils.isGPRSConnection(this) && NetStatusUtils.getNetworkType(this) == ConnectivityManager.TYPE_MOBILE){
                s += "当前网络可用，为GPRS接入网络\n" + "运营商网络类型：" + NetStatusUtils.getISPNetworkType(this);
            }
        } else {
            s += "当前网络不可用";
        }
        TextView textView = (TextView) findViewById(R.id.tv_net_status_info);
        textView.setText(s);
    }

}
