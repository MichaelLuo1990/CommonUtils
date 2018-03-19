package com.michael.commonutils.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.commonutils.R;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Desc 自定义Toast显示
 * Created by Michael on 2018/1/30.
 */

public class ToastUtils {
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param resId   资源id
     */
    public static void showShort(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param resId   资源id
     */
    public static void showLong(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义布局toast
     *
     * @param context
     * @param layoutId 自定义布局容器
     * @param message
     * @param duration
     */
    public static void showCustom(Context context, int layoutId, String message, int duration) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutId, null);//自定义布局
        TextView textView = (TextView) view.findViewById(R.id.toast_msg); //自定义toast文本
        textView.setText(message);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);//设置toast居中显示
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }

    /**
     * 居中短显示toast
     *
     * @param context
     * @param message
     */
    public static void showShortCenter(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 居中长显示toast
     *
     * @param context
     * @param message
     */
    public static void showLongCenter(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 顶部短显示toast
     *
     * @param context
     * @param message
     */
    public static void showShortTop(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * 顶部长显示toast
     *
     * @param context
     * @param message
     */
    public static void showLongTop(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * 显示图片toast
     * @param context
     * @param resId
     * @param duration
     * @param position
     */
    public static void showImage(Context context, int resId, int duration, int position) {
        Toast toast = Toast.makeText(context, "", duration);
        ImageView imageView = new ImageView(context);//创建图片控件
        imageView.setImageResource(resId);
        toast.setView(imageView);
        toast.setDuration(duration);
        toast.setGravity(position, 0, 0);
        toast.show();
    }

    /**
     * 显示多行文本toast
     * @param context
     * @param contents 文本内容
     * @param color 字体颜色
     * @param size 字体大小
     */
    public static void showMultiLineText(Context context, List<String> contents, int color, int size) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        LinearLayout linearLayout = new LinearLayout(context);//创建线性布局
        linearLayout.setOrientation(LinearLayout.VERTICAL);//设置布局垂直
        linearLayout.setBackgroundResource(R.drawable.shape_toast_bg);
        linearLayout.setPadding(10, 10, 10, 10);
        for (int i = 0; i < contents.size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(contents.get(i));
            textView.setTextSize(size);
            textView.setTextColor(color);
            linearLayout.addView(textView);
        }
        toast.setView(linearLayout);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 显示自定义时间toast-显示持续指定秒数
     * @param context
     * @param message
     * @param duration
     */
    public static void showCustomTime(Context context, String message, int duration) {
        final Toast toast=Toast.makeText(context,message, Toast.LENGTH_LONG);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);//系统默认显示为2S与3.5S，执行周期3S重复执行（显示toast）
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, duration);
    }

    private static Toast toast;
    private static TextView textView;
    private static String message;
    private static boolean canceled = true;

    /**
     * 自定义倒计时显示toast
     *
     * @param context
     * @param layoutId 自定义布局容器
     * @param msg      提示文字
     * @param duration 倒计时时长
     */
    public static void showCountdown(Context context, int layoutId, String msg, int duration) {
        message = msg;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutId, null);//自定义布局
        textView = (TextView) view.findViewById(R.id.toast_msg); //自定义toast文本
        textView.setText(message);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);//设置toast居中显示
        toast.setDuration(duration);
        toast.setView(view);
        ToastTimeCount timeCount = new ToastTimeCount(duration, 1000);
        if (canceled) {
            timeCount.start();
            canceled = false;
            showUntilCancel();
        }
    }

    private static void showUntilCancel() {
        if (canceled) { //如果已经取消显示，就直接return
            return;
        }
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showUntilCancel();
            }
        }, Toast.LENGTH_LONG);
    }

    /**
     * 自定义计时器
     */
    private static class ToastTimeCount extends CountDownTimer {

        public ToastTimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval); //millisInFuture总计时长，countDownInterval时间间隔(一般为1000ms)
        }

        @Override
        public void onTick(long millisUntilFinished) {
            textView.setText(message + ": " + millisUntilFinished / 1000 + "s后消失");
        }

        @Override
        public void onFinish() {
            //隐藏toast
            if (toast != null) {
                toast.cancel();
            }
            canceled = true;
        }
    }

}
