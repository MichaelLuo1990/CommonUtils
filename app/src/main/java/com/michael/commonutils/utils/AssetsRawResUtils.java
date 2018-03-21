package com.michael.commonutils.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;

import com.michael.commonutils.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Desc 资源文件（assets与raw）读取
 * 两个文件夹下的文件都不会被编译成二进制文件，都会被原封不动的放到apk中
 * 不同点：
 * asset下的文件不会被映射到R文件中，raw下的文件会被映射到R文件中。
 * 因为raw文件可以映射到R文件中，所以可以使用R.raw.xxx的方法去引用资源。
 * asset下可以有目录结构，raw下不能有目录结构。
 * mark：相关方法在ResUtilsTest（test类中）测试
 * Created by Michael on 2018/2/1.
 */

public class AssetsRawResUtils {

    /**
     * 获取main/assets目录下文本文件(json文件)
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getTextFromAssets(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Drawable getImageFromAsserts(final Context context, String fileName) {
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            return Drawable.createFromStream(is, null);
        } catch (IOException e) {
            if (e != null) {
                e.printStackTrace();
            }
        } catch (OutOfMemoryError e) {
            if (e != null) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            if (e != null) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static AssetManager assetManager;

    public static MediaPlayer playVoiceFromAsserts(Context context) {
        MediaPlayer player = null;
        try {
            player = new MediaPlayer();
            assetManager = context.getAssets();
            AssetFileDescriptor fileDescriptor = assetManager.openFd("一万次悲伤.mp3");
            player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(),
                    fileDescriptor.getStartOffset());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return player;
    }

    /**
     * 获取res/raw目录下文件
     *
     * @param context
     * @param resId
     * @return
     */
    public static String getTextFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取raw目录下图片
     * @param context
     * @param resId
     * @return
     */
    public static Drawable getImageFromRaw(Context context, int resId) {
        InputStream inputStream = context.getResources().openRawResource(resId);
        Drawable drawable = Drawable.createFromStream(inputStream, null);
        return drawable;
    }

    /**
     * 获取raw目录下视频缩略图（本地）
     *
     * @param context
     * @param aVideoName
     * @return
     */
    public static Drawable getVideoThumbnailFromRaw(Context context, String aVideoName) {
        Drawable drawable = null;
        try {
            int id = R.raw.class.getDeclaredField(aVideoName).getInt(context);
            Uri videoURI = Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
            drawable = getThumbnail(context, videoURI);
        } catch (IllegalAccessException aE) {
            aE.printStackTrace();
        } catch (NoSuchFieldException aE) {
            aE.printStackTrace();
        }
        return drawable;
    }

    /**
     * 获取缩略图
     *
     * @param context
     * @param aVideoUri
     * @return
     */
    private static Drawable getThumbnail(Context context, Uri aVideoUri) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(context, aVideoUri);
        Bitmap bitmap = retriever
                .getFrameAtTime(1 * 1000 * 1000, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC);
        Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
        return drawable;
    }

    /**
     * 网络上获取视频缩略图
     * @param context
     * @param url
     * @param videoName
     * @return
     */
    public static Drawable getThumbnailFromNet(Context context, String url, String videoName) {
        Drawable drawable = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(url, new HashMap<String, String>());
        Bitmap bitmap = retriever.getFrameAtTime();
        FileOutputStream outStream;
        try {
            outStream = new FileOutputStream(new File(context.getExternalCacheDir().getAbsolutePath() + "/" + videoName + ".jpg"));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, outStream);
            drawable = new BitmapDrawable(context.getResources(), bitmap);
            outStream.close();
            retriever.release();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    private static final String SEPARATOR = File.separator;//路径分隔符

    /**
     * 复制assets中的文件到指定目录
     *
     * @param context     上下文
     * @param assetsPath  assets资源路径
     * @param storagePath 目标文件夹的路径
     */
    public static void copyFilesFromAssets(Context context, String assetsPath, String storagePath) {
        String temp = "";

        if (TextUtils.isEmpty(storagePath)) {
            return;
        } else if (storagePath.endsWith(SEPARATOR)) {
            storagePath = storagePath.substring(0, storagePath.length() - 1);
        }

        if (TextUtils.isEmpty(assetsPath) || assetsPath.equals(SEPARATOR)) {
            assetsPath = "";
        } else if (assetsPath.endsWith(SEPARATOR)) {
            assetsPath = assetsPath.substring(0, assetsPath.length() - 1);
        }

        AssetManager assetManager = context.getAssets();
        try {
            File file = new File(storagePath);
            if (!file.exists()) {//如果文件夹不存在，则创建新的文件夹
                file.mkdirs();
            }

            // 获取assets目录下的所有文件及目录名
            String[] fileNames = assetManager.list(assetsPath);
            if (fileNames.length > 0) {//如果是目录 apk
                for (String fileName : fileNames) {
                    if (!TextUtils.isEmpty(assetsPath)) {
                        temp = assetsPath + SEPARATOR + fileName;//补全assets资源路径
                    }

                    String[] childFileNames = assetManager.list(temp);
                    if (!TextUtils.isEmpty(temp) && childFileNames.length > 0) {//判断是文件还是文件夹：如果是文件夹
                        copyFilesFromAssets(context, temp, storagePath + SEPARATOR + fileName);
                    } else {//如果是文件
                        InputStream inputStream = assetManager.open(temp);
                        readInputStream(storagePath + SEPARATOR + fileName, inputStream);
                    }
                }
            } else {//如果是文件 doc_test.txt或者apk/app_test.apk
                InputStream inputStream = assetManager.open(assetsPath);
                if (assetsPath.contains(SEPARATOR)) {//apk/app_test.apk
                    assetsPath = assetsPath.substring(assetsPath.lastIndexOf(SEPARATOR), assetsPath.length());
                }
                readInputStream(storagePath + SEPARATOR + assetsPath, inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 复制res/raw中的文件到指定目录
     *
     * @param context     上下文
     * @param id          资源ID
     * @param fileName    文件名
     * @param storagePath 目标文件夹的路径
     */
    public static void copyFilesFromRaw(Context context, int id, String fileName, String storagePath) {
        InputStream inputStream = context.getResources().openRawResource(id);
        File file = new File(storagePath);
        if (!file.exists()) {//如果文件夹不存在，则创建新的文件夹
            file.mkdirs();
        }
        readInputStream(storagePath + SEPARATOR + fileName, inputStream);
    }

    /**
     * 读取输入流中的数据写入输出流
     *
     * @param storagePath 目标文件路径
     * @param inputStream 输入流
     */
    public static void readInputStream(String storagePath, InputStream inputStream) {
        File file = new File(storagePath);
        try {
            if (!file.exists()) {
                // 1.建立通道对象
                FileOutputStream fos = new FileOutputStream(file);
                // 2.定义存储空间
                byte[] buffer = new byte[inputStream.available()];
                // 3.开始读文件
                int lenght = 0;
                while ((lenght = inputStream.read(buffer)) != -1) {// 循环从输入流读取buffer字节
                    // 将Buffer中的数据写到outputStream对象中
                    fos.write(buffer, 0, lenght);
                }
                fos.flush();// 刷新缓冲区
                // 4.关闭流
                fos.close();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
