package com.michael.commonutils.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.commonutils.R;
import com.michael.commonutils.utils.AssetsRawResUtils;
import com.michael.commonutils.utils.FileStorageUtils;
import com.michael.commonutils.utils.ImageConvertUtils;

import java.io.File;

/**
 * Desc 存储相关测试(内部存储/外部SD卡存储)
 * Created by Michael on 2018/2/6.
 */

public class FileStorageActivity extends AppCompatActivity {

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
        long romTotalSize = FileStorageUtils.getRomTotalSize();
        long romAvailableSize = FileStorageUtils.getRomAvailableSize();
        tvRomSize.setText("总容量：" + romTotalSize + "M   /   " + "可用容量：" + romAvailableSize + "M");
    }

    /**
     * 获取SD卡存储容量与可用容量
     *
     * @param view
     */
    public void sdSizeClick(View view) {
        long sdCardTotalSize = FileStorageUtils.getSDCardTotalSize();
        long sdCardAvailableSize = FileStorageUtils.getSDCardAvailableSize();
        tvsdSize.setText("总容量：" + sdCardTotalSize + "M   /   " + "可用容量：" + sdCardAvailableSize + "M");
    }

    /**
     * 获取指定SD卡路径可用容量大小
     *
     * @param view
     */
    public void sdTargetSizeClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_sd_target_size);
        textView.setText("获取指定SD卡路径可用容量大小:" + FileStorageUtils.getTargetPathAvailableSize("/storage/sdcard0/Pictures") + "M");
    }

    /**
     * 获取系统/SD卡存储相关路径信息
     *
     * @param view
     */
    public void getSysSDStorageDirClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_get_sys_storage_dir);
        textView.setText("获取系统根目录:" + FileStorageUtils.getRootDirectoryPath() + "\n" +
                "SD卡公有目录的路径:" + FileStorageUtils.getSDCardPublicDir(Environment.DIRECTORY_PICTURES) + "\n" +
                "SD卡私有Cache目录的路径:" + FileStorageUtils.getSDCardPrivateCacheDir(this) + "\n" +
                "SD卡私有File目录的路径:" + FileStorageUtils.getSDCardPrivateFilesDir(this, ""));
    }

    /**
     * 往SD卡的公有目录下保存文件
     * @param view
     */
    public void saveFileToSDCardClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe_two.jpg");
        byte[] bytes = ImageConvertUtils.drawable2Bytes(imageFromAsserts, Bitmap.CompressFormat.JPEG);//为方便，从资源文件获取文件保存（也可拍照回调中获取byte[]对象测试）
        boolean isSaveSuccess = FileStorageUtils.saveFileToSDCardPublicDir(bytes, Environment.DIRECTORY_PICTURES, "kobe bryant.jpg");
        if(isSaveSuccess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();//保存至/storage/sdcard0/Pictures目录（可用RE浏览器手机中查看）
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 往SD卡根目录下自定义文件（名/夹）保存文件
     * @param view
     */
    public void saveFileToSDCardDirClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe_one.jpg");
        byte[] bytes = ImageConvertUtils.drawable2Bytes(imageFromAsserts, Bitmap.CompressFormat.JPEG);
        boolean isSucess = FileStorageUtils.saveFileToSDCardCustomDir(bytes, "PicTest", "kobePic.jpg");//单目录/多级目录： PicTest(文件名即可)/多级"/"分割 如 PicTest/TestLevel1/...
        if(isSucess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 往SD卡的私有Files与Cache目录下保存文件
     * @param view
     */
    public void savePriFilesCacheDirClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe_three.jpg");
        byte[] bytes = ImageConvertUtils.drawable2Bytes(imageFromAsserts, Bitmap.CompressFormat.JPEG);
        boolean isFilesSucess = FileStorageUtils.saveFileToSDCardPrivateFilesDir(bytes, Environment.DIRECTORY_PICTURES, "kobe.jpg", this);
        if(isFilesSucess) {
            Toast.makeText(this, "保存至Files成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存至Files失败", Toast.LENGTH_SHORT).show();
        }
        boolean isCacheSucess = FileStorageUtils.saveFileToSDCardPrivateCacheDir(bytes, "kobe.jpg", this);
        if(isCacheSucess) {
            Toast.makeText(this, "保存至Cache成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存至Cache失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存bitmap图片到SDCard的私有Cache目录
     * @param view
     */
    public void saveBitmapToCacheDirClick(View view) {
        Drawable imageFromAsserts = AssetsRawResUtils.getImageFromAsserts(this, "kobe_four.jpg");
        Bitmap bitmap = ImageConvertUtils.drawable2Bitmap(imageFromAsserts);
        boolean isSucess = FileStorageUtils.saveBitmapToSDCardPrivateCacheDir(bitmap, "kobeBitmap.jpg", this);
        if(isSucess) {
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从SD卡获取文件(byte[] 与 bitmap类型)
     * @param view
     */
    public void loadByteBitmapClick(View view) {
        byte[] bytes = FileStorageUtils.loadFileFromSDCard(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator +"kobe.jpg");// = /storage/sdcard0/com.michael.commonutils/files/Pictures
        Bitmap bitmap = ImageConvertUtils.bytes2Bitmap(bytes);
        ImageView ivLoadByte = (ImageView) findViewById(R.id.iv_load_byte_type);
        ivLoadByte.setImageBitmap(bitmap);
        Bitmap loadBitmapFromSDCard = FileStorageUtils.loadBitmapFromSDCard(this.getExternalCacheDir() + File.separator + "kobeBitmap.jpg");// = /storage/sdcard0/com.michael.commonutils/cache
        ImageView ivLoadBitmap = (ImageView) findViewById(R.id.iv_load_bitmap_type);
        ivLoadBitmap.setImageBitmap(loadBitmapFromSDCard);
    }

    /**
     * 文件是否存在&文件删除操作
     * @param view
     */
    public void isFileExistClick(View view) {
        TextView textView = (TextView) findViewById(R.id.tv_file_exist_del);
        boolean fileExist = FileStorageUtils.isFileExist(this.getExternalCacheDir() + File.separator + "kobeChildPic.jpg");
        if(!fileExist) {
            textView.setText("kobeChildPic文件不存在\n 测试删除其他目录图片\n删除文件目录：/storage/sdcard0/PicTest");
        }
        String url = FileStorageUtils.getSDCardBaseDir() + File.separator + "PicTest" + File.separator + "kobePic.jpg";
        boolean otherFileExist = FileStorageUtils.isFileExist(url);
        if(otherFileExist) {//根目录PicTest文件中删除文件
            boolean isDelSucess = FileStorageUtils.removeFileFromSDCard(url);
            if(isDelSucess) {
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
        }
    }
}
