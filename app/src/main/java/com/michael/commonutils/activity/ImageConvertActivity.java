package com.michael.commonutils.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.AssetsRawResUtils;
import com.michael.commonutils.utils.ImageConvertUtils;

import java.io.InputStream;

/**
 * Desc 图片格式装换
 * Created by Michael on 2018/3/29.
 */

public class ImageConvertActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_covert);
    }

    /**
     * drawable byte[] 互相转换
     *
     * @param view
     */
    public void transDrawableByteClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe.jpg");
        byte[] bytes = ImageConvertUtils.drawable2Bytes(imageFromAsserts, Bitmap.CompressFormat.JPEG);
        TextView textview = (TextView) findViewById(R.id.tv_trans_byte_drawable);
        textview.setText("bytes.toString()->" + bytes.toString());
        Drawable drawable = ImageConvertUtils.bytes2Drawable(this, bytes);
        ImageView imageView = (ImageView) findViewById(R.id.iv_trans_byte_drawable);
        imageView.setImageDrawable(drawable);
    }

    /**
     * drawable bitmap 相互转换
     *
     * @param view
     */
    public void transDrawableBitmapClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe_one.jpg");
        Bitmap drawable2Bitmap = ImageConvertUtils.drawable2Bitmap(imageFromAsserts);
        ImageView ivBitmap = (ImageView) findViewById(R.id.iv_drawable_trans_bitmap);
        ivBitmap.setImageBitmap(drawable2Bitmap);
        Drawable imageFromAsserts1 = AssetsRawResUtils.getImageFromAsserts(this, "kobe_three.jpg");
        Bitmap bitmap = ImageConvertUtils.drawable2Bitmap(imageFromAsserts1);
        ImageView ivDrawable = (ImageView) findViewById(R.id.iv_bitmap_trans_drawable);
        ivDrawable.setImageBitmap(bitmap);
    }

    /**
     * bitmap bytes 相互转换
     *
     * @param view
     */
    public void transBitmapBytesClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe_two.jpg");
        Bitmap bitmap = ImageConvertUtils.drawable2Bitmap(imageFromAsserts);
        byte[] bytes = ImageConvertUtils.bitmap2Bytes(bitmap, Bitmap.CompressFormat.JPEG);
        TextView textview = (TextView) findViewById(R.id.tv_bitmap_trans_byte);
        textview.setText("bytes.toString()->" + bytes.toString());
        Bitmap bytes2Bitmap = ImageConvertUtils.bytes2Bitmap(bytes);
        ImageView imageView = (ImageView) findViewById(R.id.iv_byte_trans_bitmap);
        imageView.setImageBitmap(bytes2Bitmap);
    }

    /**
     * view 转化 bitmap
     * 应用场景：将当前Android页面（布局与组件）转化为bitmap保存（上传/分享）
     *
     * @param view
     */
    public void transViewBitmapClick(View view) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        View inflateView = LayoutInflater.from(this).inflate(R.layout.rl_image_convert_view_trans_bitmap, null, false);
        layoutView(inflateView, width, height);//View和其内部的子View都具有了实际大小，完成了布局后，创建位图并绘制
        final ScrollView scrollView = (ScrollView) inflateView.findViewById(R.id.sv_image_convert);
        Bitmap bitmap = ImageConvertUtils.view2Bitmap(scrollView);//root view 或 child view
        ImageView imageView = (ImageView) findViewById(R.id.iv_view_trans_bitmap);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * 布局绘制
     * 整个View的大小 参数是左上角 和右下角的坐标
     * measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局,按示例调用layout函数后，View的大小将会变成你想要设置成的大小
     *
     * @param v
     * @param width
     * @param height
     */
    private void layoutView(View v, int width, int height) {
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.AT_MOST);
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }

    /**
     * Bitmap Drawable Bytes 转化 inputstream
     *
     * @param view
     */
    public void transBitmapDrawableBytesToInputStreamClick(View view) {
        Drawable drawable = AssetsRawResUtils.getImageFromAsserts(this, "kobe_one.jpg");
        InputStream drawable2InputStream = ImageConvertUtils.Drawable2InputStream(drawable);
        TextView textView = (TextView) findViewById(R.id.tv_draw_trans_is);
        textView.setText("drawable2InputStream:" + drawable2InputStream.toString());

        Drawable drawable1 = AssetsRawResUtils.getImageFromAsserts(this, "kobe_two.jpg");
        Bitmap bitmap = ImageConvertUtils.drawable2Bitmap(drawable1);
        InputStream bitmap2InputStream = ImageConvertUtils.Bitmap2InputStream(bitmap);
        TextView textView1 = (TextView) findViewById(R.id.tv_bitmap_trans_is);
        textView1.setText("bitmap2InputStream:" + bitmap2InputStream.toString());

        Drawable drawable2 = AssetsRawResUtils.getImageFromAsserts(this, "kobe_three.jpg");
        byte[] bytes = ImageConvertUtils.drawable2Bytes(drawable2, Bitmap.CompressFormat.JPEG);
        InputStream byte2InputStream = ImageConvertUtils.Byte2InputStream(bytes);
        TextView textView2 = (TextView) findViewById(R.id.tv_bytes_trans_is);
        textView2.setText("byte2InputStream:" + byte2InputStream.toString());

    }

    /**
     * inputstream 转化 Bitmap Drawable Bytes
     *
     * @param view
     */
    public void transInputStreamToBitmapDrawableBytesClick(View view) {
        Drawable drawable = AssetsRawResUtils.getImageFromAsserts(this, "kobe_four.jpg");
        InputStream drawable2InputStream = ImageConvertUtils.Drawable2InputStream(drawable);
        Bitmap inputStream2Bitmap = ImageConvertUtils.InputStream2Bitmap(drawable2InputStream);
        ImageView imageView = (ImageView) findViewById(R.id.iv_is_trans_bmap);
        imageView.setImageBitmap(inputStream2Bitmap);

        Drawable drawable1 = AssetsRawResUtils.getImageFromAsserts(this, "kobe_two.jpg");
        Bitmap bitmap = ImageConvertUtils.drawable2Bitmap(drawable1);
        InputStream bitmap2InputStream = ImageConvertUtils.Bitmap2InputStream(bitmap);
        Drawable InputStream2Drawable = ImageConvertUtils.InputStream2Drawable(this, bitmap2InputStream);
        ImageView imageView1 = (ImageView) findViewById(R.id.iv_is_trans_draw);
        imageView1.setImageDrawable(InputStream2Drawable);

        Drawable drawable2 = AssetsRawResUtils.getImageFromAsserts(this, "kobe_three.jpg");
        byte[] bytes = ImageConvertUtils.drawable2Bytes(drawable2, Bitmap.CompressFormat.JPEG);
        InputStream byte2InputStream = ImageConvertUtils.Byte2InputStream(bytes);
        byte[] inputStream2Bytes = ImageConvertUtils.InputStream2Bytes(byte2InputStream);
        TextView textView = (TextView) findViewById(R.id.tv_is_trans_bytes);
        textView.setText("inputStream2Bytes:" + inputStream2Bytes);

    }
}
