package com.michael.commonutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc SharedPreferences工具类
 * Created by Michael on 2018/1/31.
 */
public class SharedPreferencesUtils {

    /**
     * 保存数据-根据类型调用不同的保存方法
     * SharedPreferencesUtils.setData(this, "String", "xiaanming");
     * SharedPreferencesUtils.setData(this, "int", 10);
     * SharedPreferencesUtils.setData(this, "boolean", true);
     * SharedPreferencesUtils.setData(this, "long", 100L);
     * SharedPreferencesUtils.setData(this, "float", 1.1f);
     *
     * @param context
     * @param key
     * @param object
     * @param fileName 存储文件名
     */
    public static void setData(Context context, String key, Object object, String fileName) {

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
     * SharedPreferencesUtils.getData(TimerActivity.this, "String", "");
     * SharedPreferencesUtils.getData(TimerActivity.this, "int", 0);
     * SharedPreferencesUtils.getData(TimerActivity.this, "boolean", false);
     * SharedPreferencesUtils.getData(TimerActivity.this, "long", 0L);
     * SharedPreferencesUtils.getData(TimerActivity.this, "float", 0.0f);
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object getData(Context context, String key, Object defaultObject, String fileName) {
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
     * 保存List
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
     * @param tag
     * @return
     */
    public static <T> List<T> getDataList(Context context, String tag, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        List<T> datalist=new ArrayList<T>();
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
     * 保存Set
     *
     * @param tag
     * @param dataset
     */
    public static <T> void setDataSet(Context context, String tag, Set<T> dataset, String fileName) {
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
     *
     * @param context
     * @param tag
     * @param fileName
     * @return
     */
    public static <T> Set<T> getDataSet(Context context, String tag, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Set<T> dataSet = new HashSet<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return dataSet;
        }
        Gson gson = new Gson();
        dataSet = gson.fromJson(strJson, new TypeToken<Set<T>>() {
        }.getType());
        return dataSet;
    }

    /**
     * 保存Map对象
     *
     * @param key key
     * @param map map数据
     * @return 保存结果
     */
    public static <K, V> boolean setDataMap(Context context, String key, Map<K, V> map, String fileName) {
        boolean result;
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(key, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 获取Map对象
     *
     * @param context
     * @param key
     * @param clsV
     * @param fileName
     * @param <T>
     * @return
     */
    public static <T> HashMap<String, T> getDataMap(Context context, String key, Class<T> clsV, String fileName) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        String json = preferences.getString(key, "");
        HashMap<String, T> map = new HashMap<>();
        if(!json.equals("")) {
            Gson gson = new Gson();
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                String entryKey = entry.getKey();
                JsonObject value = (JsonObject) entry.getValue();
                map.put(entryKey, gson.fromJson(value, clsV));
            }
        }
        return map;
    }

    /**
     * 保存图片
     *
     * @param context
     * @param fineName
     * @param bitmap
     * @param key
     */
    public static void setDataImage(Context context, String key, Bitmap bitmap, String fineName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fineName, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, baos);
        String imageBase64 = new String(Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT));
        editor.putString(key, imageBase64);
        editor.commit();
    }

    /**
     * 获取图片
     *
     * @param context
     * @param fineName
     * @param key
     * @return
     */
    public static Drawable getDataImage(Context context, String key, String fineName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(fineName, context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, "");
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        return Drawable.createFromStream(bais, "");
    }

    /**
     * 保存对象-base64方式
     *
     * @param context
     * @param key
     * @param object
     * @param fileName
     */
    public static void setDataObj(Context context, String key, Object object, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, objectVal);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取对象-base方式
     *
     * @param context
     * @param key
     * @param clazz
     * @param fileName
     * @param <T>
     * @return
     */
    public static <T> T getDataObj(Context context, String key, Class<T> clazz, String fileName) {
        SharedPreferences sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
