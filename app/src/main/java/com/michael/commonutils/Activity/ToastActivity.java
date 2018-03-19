package com.michael.commonutils.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc Toast显示
 * Created by Michael on 2018/1/30.
 */
public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    /**
     * 显示系统默认短时间toast
     *
     * @param view
     */
    public void showShortToastClick(View view) {
        ToastUtils.showShort(this, "你点击了短时toast");
//        ToastUtils.showShort(this, R.string.app_name);//从String资源中获取赋值
    }

    /**
     * 显示系统默认长时间toast
     *
     * @param view
     */
    public void showLongToastClick(View view) {
        ToastUtils.showLong(this, "你点击了长时toast");
    }

    /**
     * 显示居中toast
     *
     * @param view
     */
    public void showCenterToastClick(View view) {
        ToastUtils.showShortCenter(this, "你点击了居中显示toast");
    }

    /**
     * 显示顶部toast
     *
     * @param view
     */
    public void showTopToastClick(View view) {
        ToastUtils.showShortTop(this, "你点击了顶部显示toast");
    }

    /**
     * 自定义横向居中toast
     *
     * @param view
     */
    public void showToastHorizontalViewClick(View view) {
        ToastUtils.showCustom(this, R.layout.ll_toast_center_horizontal, "自定义横向居中toast", Toast.LENGTH_LONG);
    }

    /**
     * 自定义纵向居中toast
     *
     * @param view
     */
    public void showToastVerticalViewClick(View view) {
        ToastUtils.showCustom(this, R.layout.ll_toast_center_vertical, "自定义纵向居中toast", Toast.LENGTH_LONG);
    }

    /**
     * 显示自定义持续时间toast
     * @param view
     */
    public void showCustomTimeToastClick(View view) {
        ToastUtils.showCustomTime(this, "启动toast，持续5S", 5000);
    }

    /**
     * 显示倒计时toast
     * @param view
     */
    public void showCountDownToastClick(View view) {
        ToastUtils.showCountdown(this, R.layout.ll_toast_center_countdown_horizontal, "倒计时toast显示", 5000);
    }

    /**
     * 显示图片toast
     * @param view
     */
    public void showToastImageClick(View view) {
        ToastUtils.showImage(this, R.drawable.img_car_audi, Toast.LENGTH_LONG, Gravity.CENTER);
    }

    public void showToastMultiLineTextClick(View view) {
        List<String> datas = new ArrayList<>();
        datas.add("床前明月光");
        datas.add("疑是地上霜");
        datas.add("举头望明月");
        datas.add("我是郭德纲");
        ToastUtils.showMultiLineText(this, datas, Color.DKGRAY, 16);
    }

}
