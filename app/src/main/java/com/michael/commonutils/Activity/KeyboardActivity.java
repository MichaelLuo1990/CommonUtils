package com.michael.commonutils.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.KeyboardUtils;

/**
 * Desc 软键盘控制
 * Created by Michael on 2018/2/1.
 */

public class KeyboardActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);
        editText = (EditText) findViewById(R.id.et_keyboard);

    }

    /**
     * 除子控件判定焦点事件外，点击父级容器，有父级容器处理响应事件
     * ref :http://blog.csdn.net/zhaowei_zhang/article/details/50222023
     * @param view
     */
    public void llParentClick(View view) {
        if (KeyboardUtils.isSoftInputShow(this)) {
            KeyboardUtils.closeKeybord(editText, this);
        }
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public void showKeyboardClick(View view) {
        KeyboardUtils.openKeybord(editText, this);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public void hideKeyboardClick(View view) {
        if (KeyboardUtils.isSoftInputShow(this)) {
            KeyboardUtils.closeKeybord(editText, this);
        }
    }
}
