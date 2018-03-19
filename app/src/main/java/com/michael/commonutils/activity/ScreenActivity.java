package com.michael.commonutils.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.ScreenUtils;

/**
 * Desc 屏幕相关测试
 * Created by Michael on 2018/3/8.
 */

public class ScreenActivity extends Activity {

    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        scrollView = (ScrollView) findViewById(R.id.sv_screen);
        scrollView.setVisibility(View.VISIBLE);
    }

    /**
     * 获取屏幕dip值
     *
     * @param view
     */
    public void getDpiClick(View view) {
        TextView tvScreenDpi = (TextView) findViewById(R.id.tv_screen_dpi);
        int dpi = ScreenUtils.getDpi(this);
        tvScreenDpi.setText("当前设备dpi为" + dpi);
    }

    /**
     * 是否为竖屏状态
     *
     * @param view
     */
    public void hasPortraitClick(View view) {
        TextView tvScreenOriatation = (TextView) findViewById(R.id.tv_screen_oriatation);
        if (ScreenUtils.isScreenOriatationPortrait(this)) {
            tvScreenOriatation.setText("当前为竖屏状态......");
        } else {
            tvScreenOriatation.setText("当前为横屏状态......");
        }
    }

    /**
     * 是否存在底部导航栏(底部虚拟按键)
     *
     * @param view
     */
    public void hasNavigationBarClick(View view) {
        TextView tvScreenOriatation = (TextView) findViewById(R.id.tv_screen_navigation_bar);
        if (ScreenUtils.hasNavigationBar(this)) {
            tvScreenOriatation.setText("当前设备存在底部导航栏");
        } else {
            tvScreenOriatation.setText("当前设备无底部导航栏");
        }
    }

    /**
     * 获取状态栏、标题栏、导航栏高度
     *
     * @param view
     */
    public void getStatusTitleBarNavigationBarClick(View view) {
        TextView tvParam = (TextView) findViewById(R.id.tv_screen_status_titlebar_navbar);
        int titleBarHeight = ScreenUtils.getTitleBarHeight(this);
        int statusHeight = ScreenUtils.getStatusHeight(this);
        int bottomNavBarHeight = ScreenUtils.getBottomNavBarHeight(this);
        tvParam.setText("状态栏高度：" + statusHeight + "  /  标题栏高度：" + titleBarHeight + "  /  导航栏高度：" + bottomNavBarHeight);
    }

    /**
     * dp  px  sp 单位装换
     * @param view
     */
    public void transferDpPxSpClick(View view) {
        TextView tvTranfer = (TextView) findViewById(R.id.tv_screen_dp_px_sp_transform);
        float px2dp = ScreenUtils.px2dp(this, 10);
        float dp2px = ScreenUtils.dp2px(this, 10);
        float sp2px = ScreenUtils.sp2px(this, 10);
        float px2sp = ScreenUtils.px2sp(this, 10);
        tvTranfer.setText("10px--->" + px2dp + "dp" + "  /  " + "10dp--->" + dp2px + "px"  + "  /  " +
                "10sp--->" + sp2px + "px" + "  /  " + "10px--->" + px2sp + "sp"  + "  /  " );
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     * @param view
     */
    public void showScreenShotStatusbarClick(View view) {
        final ImageView imageView = (ImageView) findViewById(R.id.iv_screen_shot_with_statusbar);
        Bitmap bitmap = ScreenUtils.screenShotWithStatusBar(this);
        imageView.setImageBitmap(bitmap);
        if (imageView.getVisibility() == View.GONE) {
            scrollView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     * @param view
     */
    public void showScreenShotWithoutStatusBarClick(View view) {
        final ImageView imageView = (ImageView) findViewById(R.id.iv_screen_shot_without_statusbar);
        Bitmap bitmap = ScreenUtils.screenShotWithoutStatusBar(this);
        imageView.setImageBitmap(bitmap);
        if (imageView.getVisibility() == View.GONE) {
            scrollView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            }
        });
    }

}
