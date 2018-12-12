package com.michael.commonutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.michael.commonutils.activity.CryptionActivity;
import com.michael.commonutils.activity.DeviceInfoActivity;
import com.michael.commonutils.activity.FileStorageActivity;
import com.michael.commonutils.activity.ImageConvertActivity;
import com.michael.commonutils.activity.KeyboardActivity;
import com.michael.commonutils.activity.NetStatusActivity;
import com.michael.commonutils.activity.PermissionActivity;
import com.michael.commonutils.activity.ScreenActivity;
import com.michael.commonutils.activity.SharedPreferenceActivity;
import com.michael.commonutils.activity.ToastActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_main);
        String[] array = {"PermissionUtils-权限状态判断（网络、蓝牙、GPS等）",
                "ToastUtils-Toast显示（自定义）",
                "KeyboardUtils-软键盘显示控制",
                "ScreenUtils-屏幕状态获取",
                "NetStatusUtils-网络状态判断获取",
                "DeviceInfoUtils-硬件设备信息操作/获取",
                "StringUtils&RegexUtils-字符串&正则表达式判断from javaModule",
                "HttpUrlConnectionUtils-http网络请求from javaModule",
                "DateTimeUtils-日期时间获取/装换from javaModule",
                "DisplayConvertUtils-屏幕分辨率单位dp\\px\\sp装换",
                "ImageConvertUtils-图片格式类型转换",
                "SharedPreferenceUtils-SP存储操作",
                "FileStorageUtils-文件内部、SD卡存储操作",
                "MD5Util/AESUtils/DESUtils/RSAUtils-数据加密/解密处理"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                switch (i) {
                    case 0:
                        intent = new Intent(MainActivity.this, PermissionActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ToastActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, KeyboardActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ScreenActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, NetStatusActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, DeviceInfoActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        Toast.makeText(MainActivity.this, "相关测试->javaModule->RegexUtilsTest&StringUtilsTest", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(MainActivity.this, "相关测试->javaModule->HttpUrlConnectionUtils", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(MainActivity.this, "相关测试->javaModule->DateTimeUtilsTest", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        intent = new Intent(MainActivity.this, ScreenActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        intent = new Intent(MainActivity.this, ImageConvertActivity.class);
                        startActivity(intent);
                        break;
                    case 11:
                        intent = new Intent(MainActivity.this, SharedPreferenceActivity.class);
                        startActivity(intent);
                        break;
                    case 12:
                        intent = new Intent(MainActivity.this, FileStorageActivity.class);
                        startActivity(intent);
                        break;
                    case 13:
                        intent = new Intent(MainActivity.this, CryptionActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
