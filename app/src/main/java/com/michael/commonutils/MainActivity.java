package com.michael.commonutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.michael.commonutils.Activity.KeyboardActivity;
import com.michael.commonutils.Activity.PermissionActivity;
import com.michael.commonutils.Activity.ToastActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.lv_main);
        String[] array = {"PermissionUtils-权限状态判断（网络、蓝牙、GPS等）", "ToastUtils-Toast显示（自定义）","KeyboardUtils-软键盘显示控制"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = null;
                switch (i) {
                    case 0:
                        intent = new Intent(MainActivity.this, PermissionActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, ToastActivity.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, KeyboardActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
