package com.michael.commonutils.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.StorageUtils;

/**
 * Desc 存储相关测试
 * Created by Michael on 2018/2/6.
 */

public class StorageActivity extends AppCompatActivity {

    private TextView tvRomSize;
    private TextView tvsdSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        tvRomSize = (TextView) findViewById(R.id.tv_rom_size);
        tvsdSize = (TextView) findViewById(R.id.tv_sd_size);

    }

    /**
     * 获取本机存储容量与可用容量
     *
     * @param view
     */
    public void romSizeClick(View view) {
        long romTotalSize = StorageUtils.getRomTotalSize();
        long romAvailableSize = StorageUtils.getRomAvailableSize();
        tvRomSize.setText("总容量：" + romTotalSize + "M   /   " + "可用容量：" + romAvailableSize + "M");
    }

    /**
     * 获取SD卡存储容量与可用容量
     *
     * @param view
     */
    public void sdSizeClick(View view) {
        long sdCardTotalSize = StorageUtils.getSDCardTotalSize();
        long sdCardAvailableSize = StorageUtils.getSDCardAvailableSize();
        tvsdSize.setText("总容量：" + sdCardTotalSize + "M   /   " + "可用容量：" + sdCardAvailableSize + "M");
    }

}
