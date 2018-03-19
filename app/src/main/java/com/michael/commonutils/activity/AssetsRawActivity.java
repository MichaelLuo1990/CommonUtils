package com.michael.commonutils.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.AssetsRawResUtils;

/**
 * Desc assets raw资源获取
 * Created by Michael on 2018/2/2.
 */

public class AssetsRawActivity extends AppCompatActivity {

    private TextView tvShowAssetsRes;
    private ImageView ivShowAssetsPhoto;
    private LinearLayout llShowAssetsHtml;
    private WebView wvAssetsHtml;

    private TextView tvShowRawRes;
    private ImageView ivShowRawPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        tvShowAssetsRes = (TextView) findViewById(R.id.tv_show_assets_res);
        ivShowAssetsPhoto = (ImageView) findViewById(R.id.iv_get_assets_photo);
        llShowAssetsHtml = (LinearLayout) findViewById(R.id.ll_assets_html_res);
        llShowAssetsHtml.setVisibility(View.GONE);
        wvAssetsHtml = (WebView) findViewById(R.id.wv_show_assets_html);
        tvShowRawRes = (TextView) findViewById(R.id.tv_show_raw_res);
        ivShowRawPhoto = (ImageView) findViewById(R.id.iv_get_raw_photo);
    }

    /**
     * 获取assets文本内容(获取json格式-实际使用gson or fastjson相关解析为响应实体类)
     * @param view
     */
    public void getAssetsTextClick(View view) {
        String fileFromAssets = AssetsRawResUtils.getTextFromAssets(this, "test.json");
        tvShowAssetsRes.setText(fileFromAssets);
    }

    /**
     * 读取图片
     * @param view
     */
    public void getAssetsImageClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe.jpg");
        ivShowAssetsPhoto.setImageDrawable(imageFromAsserts);
    }

    /**
     * 显示HTML资源
     * @param view
     */
    public void getAssetsHtmlClick(View view) {
        wvAssetsHtml.loadUrl("file:///android_asset/baiduIndex.html");
        if(llShowAssetsHtml.getVisibility() == View.GONE) {
            llShowAssetsHtml.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 返回、隐藏HTML
     * @param view
     */
    public void backClick(View view) {
        if(llShowAssetsHtml.getVisibility() == View.VISIBLE) {
            llShowAssetsHtml.setVisibility(View.GONE);
        }
    }

    /**
     * 获取raw文本内容
     * @param view
     */
    public void getRawTextClick(View view) {
        String fileFromRaw = AssetsRawResUtils.getTextFromRaw(this, R.raw.test);
        tvShowRawRes.setText(fileFromRaw);
    }

    /**
     * 获取raw图片资源
     * @param view
     */
    public void getRawImageClick(View view) {
        Drawable drawable = AssetsRawResUtils.getImageFromRaw(this, R.raw.kobe);
        ivShowRawPhoto.setImageDrawable(drawable);
    }
}
