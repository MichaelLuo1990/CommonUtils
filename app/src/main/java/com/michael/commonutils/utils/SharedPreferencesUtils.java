package com.michael.commonutils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Desc SharedPreferences工具类
 * Created by Michael on 2018/1/31.
 */
public class SharedPreferencesUtils {

    /**
     * 保存数据-根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     * @param fileName 存储文件名
     */
    public static void setParam(Context context, String key, Object object, String fileName) {

        String type = object.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if ("String".equals(type)) {
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) object);
        } else if ("HashSet".equals(type)) {
            editor.putStringSet(key, (HashSet) object);
        }

        editor.commit();
    }


    /**
     * 获取保存数据-根据默认值类型获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getParam(Context context, String key, Object defaultObject, String fileName) {
        String type = defaultObject.getClass().getSimpleName();
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);

        if ("String".equals(type)) {
            return sp.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return sp.getLong(key, (Long) defaultObject);
        } else if ("HashSet".equals(type)) {
            return sp.getStringSet(key, (HashSet) defaultObject);
        }

        return null;
    }

    /**
     * 清除数据
     *
     * @param context
     * @param fileName
     */
    public static void clear(Context context, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 引用参考
     SharedPreferencesUtils.setParam(this, "String", "xiaanming");
     SharedPreferencesUtils.setParam(this, "int", 10);
     SharedPreferencesUtils.setParam(this, "boolean", true);
     SharedPreferencesUtils.setParam(this, "long", 100L);
     SharedPreferencesUtils.setParam(this, "float", 1.1f);

     SharedPreferencesUtils.getParam(TimerActivity.this, "String", "");
     SharedPreferencesUtils.getParam(TimerActivity.this, "int", 0);
     SharedPreferencesUtils.getParam(TimerActivity.this, "boolean", false);
     SharedPreferencesUtils.getParam(TimerActivity.this, "long", 0L);
     SharedPreferencesUtils.getParam(TimerActivity.this, "float", 0.0f);
     *
     */

    /*
     * 存储list\set对象
     */
//    private SharedPreferences preferences;
//    private SharedPreferences.Editor editor;
//
//    public SharedPreferencesUtils(Context mContext, String fileName) {
//        preferences = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
//        editor = preferences.edit();
//    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public static <T> void setDataList(Context context, String tag, List<T> datalist, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(Context context, String tag, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
        List<T> datalist = new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
        }.getType());
        return datalist;
    }

    /**
     * 获取存储json格式数据
     *
     * @param tag
     * @return
     */
    public static String getDataJsonStr(Context context, String tag, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getString(tag, null);
    }

    /**
     * 保存Set
     * @param tag
     * @param dataset
     */
    public <T> void setDataSet(Context context, String tag, Set<T> dataset, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (null == dataset || dataset.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(dataset);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * 获取Set
     * @param context
     * @param tag
     * @param fileName
     * @return
     */
    public <T> Set<T> getDataSet(Context context, String tag, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Set<T> dataSet=new HashSet<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return dataSet;
        }
        Gson gson = new Gson();
        dataSet = gson.fromJson(strJson, new TypeToken<Set<T>>() {
        }.getType());
        return dataSet;

    }
}
