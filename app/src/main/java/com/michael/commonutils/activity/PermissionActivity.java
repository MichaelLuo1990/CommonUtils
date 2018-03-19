package com.michael.commonutils.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.michael.commonutils.R;
import com.michael.commonutils.constant.PermissionConstants;
import com.michael.commonutils.utils.PermissionUtils;

import java.util.Arrays;

/**
 * Desc 权限管理
 * Created by Michael on 2018/1/29.
 */

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ListView listView = (ListView) findViewById(R.id.lv_permission);
        String[] array = {"申请单个权限", "申请多个权限"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        requestPermission();//检测单个权限
                        break;
                    case 1:
                        multiRequestPermission();//检测多个权限
                        break;
                }
            }
        });
    }

    // 申请一个权限
    private void requestPermission() {
        PermissionUtils.checkPermission(PermissionActivity.this, PermissionConstants.PERMISSION,
                new PermissionUtils.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        toCamera();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        showExplainDialog(permission, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PermissionUtils.requestPermission(PermissionActivity.this, PermissionConstants.PERMISSION, PermissionConstants.REQUEST_CODE_PERMISSION);
                            }
                        });
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                        PermissionUtils.requestPermission(PermissionActivity.this, PermissionConstants.PERMISSION, PermissionConstants.REQUEST_CODE_PERMISSION);
                    }
                });
    }

    /**
     * 启用相机
     */
    private void toCamera() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
        startActivity(intent);
    }

    /**
     * 解释权限的dialog
     */
    private void showExplainDialog(String[] permission, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(PermissionActivity.this)
                .setTitle("申请权限")
                .setMessage("我们需要" + Arrays.toString(permission) + "权限")
                .setPositiveButton("确定", onClickListener)
                .show();
    }

    /**
     * 多权限请求
     */
    private void multiRequestPermission() {
        PermissionUtils.checkMorePermissions(this, PermissionConstants.PERMISSIONS, new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
//                toCamera();
                Log.i("aaa", "onHasPermission");
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
                PermissionUtils.showExplainDialog(PermissionActivity.this, permission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.requestMorePermissions(PermissionActivity.this, PermissionConstants.PERMISSIONS, PermissionConstants.REQUEST_CODE_PERMISSIONS);
                    }
                });
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                PermissionUtils.requestMorePermissions(PermissionActivity.this, PermissionConstants.PERMISSIONS, PermissionConstants.REQUEST_CODE_PERMISSIONS);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionConstants.REQUEST_CODE_PERMISSION:
                if (PermissionUtils.isPermissionRequestSuccess(grantResults)) {
                    toCamera(); // 权限申请成功
                } else {
                    Toast.makeText(this, "打开相机失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case PermissionConstants.REQUEST_CODE_PERMISSIONS:
                PermissionUtils.onRequestMorePermissionsResult(this, PermissionConstants.PERMISSIONS, new PermissionUtils.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        Log.i("aaa", "onHasPermission");
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
//                        Toast.makeText(LoginWebviewActivity.this, "我们需要"+Arrays.toString(permission)+"权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
//                        Toast.makeText(LoginWebviewActivity.this, "我们需要"+ Arrays.toString(permission)+"权限", Toast.LENGTH_SHORT).show();
//                        showToAppSettingDialog();
                    }
                });
                break;
        }
    }
}
