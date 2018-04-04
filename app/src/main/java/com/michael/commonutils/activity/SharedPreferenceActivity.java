package com.michael.commonutils.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.commonutils.R;
import com.michael.commonutils.entity.Person;
import com.michael.commonutils.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc sharedpreference使用测试
 * Created by Michael on 2018/4/2.
 */

public class SharedPreferenceActivity extends Activity {

    private final static String FILE_NAME = "SP_TEST";
    private final static String FILE_NEME_LIST_STRING = "SP_LIST_STRING";
    private final static String FILE_NEME_LIST_INTEGER = "SP_LIST_INTEGER";
    private final static String FILE_NEME_LIST_MAP = "SP_LIST_MAP";
    private final static String FILE_NEME_LIST_OBJ = "SP_LIST_OBJ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);

    }

    /**
     * 存储数据：(基本类型int Boolean float long) String hashSet 类型数据
     *
     * @param view
     */
    public void saveDataClick(View view) {
        SharedPreferencesUtils.setData(this, "String", "Michael", FILE_NAME);
        SharedPreferencesUtils.setData(this, "int", 28, FILE_NAME);
        SharedPreferencesUtils.setData(this, "float", 1.1f, FILE_NAME);
        SharedPreferencesUtils.setData(this, "long", 175L, FILE_NAME);
        SharedPreferencesUtils.setData(this, "boolean", true, FILE_NAME);
        HashSet<String> hashSet = new HashSet<>();//存储不重复元素
        hashSet.add("C");
        hashSet.add("Java");
        hashSet.add("Android");
        hashSet.add("Kotlin");
        hashSet.add("Android");
        SharedPreferencesUtils.setData(this, "HashSet", hashSet, FILE_NAME);
        Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取数据：(基本类型int Boolean float long) String hashSet 类型数据
     *
     * @param view
     */
    public void getDataClick(View view) {
        String name = String.valueOf(SharedPreferencesUtils.getData(this, "String", "", FILE_NAME));
        int age = Integer.valueOf(String.valueOf(SharedPreferencesUtils.getData(this, "int", 0, FILE_NAME)));
        float salary = Float.valueOf(String.valueOf(SharedPreferencesUtils.getData(this, "float", 0.0f, FILE_NAME)));
        long stature = Long.valueOf(String.valueOf(SharedPreferencesUtils.getData(this, "long", 0L, FILE_NAME)));
        boolean isSingle = Boolean.valueOf(String.valueOf(SharedPreferencesUtils.getData(this, "boolean", false, FILE_NAME)));
        HashSet<String> hashSet = new HashSet<>();
        hashSet = (HashSet<String>) SharedPreferencesUtils.getData(this, "HashSet", hashSet, FILE_NAME);
        String skillDesc = "";
        for (String hashSetStr : hashSet) {
            skillDesc += hashSetStr + "/";
        }
        TextView textView = (TextView) findViewById(R.id.tv_get_data_result);
        textView.setText("name:" + name + "\n" +
                "age:" + age + "\n" +
                "salary:" + salary + "\n" +
                "stature:" + stature + "\n" +
                "isSingle:" + isSingle + "\n" +
                "skillDesc:" + skillDesc);
    }

    /**
     * 存储list类型数据-List<String>  List<Integer>  List<Map<String,Object>>   List<JavaBean>  etc...
     * @param view
     */
    public void saveListDataClick(View view) {
        List<String> listString = new ArrayList<>();
        listString.add("Amy");
        listString.add("23");
        listString.add("sex");
        SharedPreferencesUtils.setDataList(this, "listString", listString, FILE_NEME_LIST_STRING);

        List<Integer> listInt = new ArrayList<>();
        listInt.add(12);
        listInt.add(23);
        listInt.add(34);
        SharedPreferencesUtils.setDataList(this, "listInt", listInt, FILE_NEME_LIST_INTEGER);

        List<Map<String,Object>> listMaps = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("a1", i);
            map.put("a2", "lalala" + i);
            listMaps.add(map);
        }
        SharedPreferencesUtils.setDataList(this, "listMaps", listMaps, FILE_NEME_LIST_MAP);

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setName("kobe");
        person.setAge(39);
        person.setSex("male");
        person.setSingle(false);
        person.setHeight(198);
        person.setEducationBackground("highSchool");
        persons.add(person);
        Person person1 = new Person();
        person1.setName("Jordan");
        person1.setAge(49);
        person1.setSex("male");
        person1.setSingle(false);
        person1.setHeight(198);
        person1.setEducationBackground("BCRLLSchool");
        persons.add(person1);
        SharedPreferencesUtils.setDataList(this, "persons", persons, FILE_NEME_LIST_OBJ);
        Toast.makeText(this, "存储list类型成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取list类型数据
     * @param view
     */
    public void getListDataClick(View view) {
        List<String> listString = SharedPreferencesUtils.getDataList(this, "listString", FILE_NEME_LIST_STRING);
        List<Integer> listInt = SharedPreferencesUtils.getDataList(this, "listInt", FILE_NEME_LIST_INTEGER);
        List<Map<String, Object>> listMaps = SharedPreferencesUtils.getDataList(this, "listMaps", FILE_NEME_LIST_MAP);
        List<Person> persons = SharedPreferencesUtils.getDataList(this, "persons", FILE_NEME_LIST_OBJ);
        String listDataStr = "";
        if(listString != null && listString.size() > 0) {
            listDataStr += "listString:" + listString + "\n";
        }
        if(listInt != null && listInt.size() > 0) {
            listDataStr += "listInt:" + listInt + "\n";
        }
        if(listMaps != null && listMaps.size() > 0) {
            listDataStr += "listMaps:" + listMaps + "\n";
        }
        if(persons != null && persons.size() > 0) {
            listDataStr += "persons:" + persons + "\n";
        }
        TextView textView = (TextView) findViewById(R.id.tv_get_data_result_list);
        if(listDataStr.equals("")) {
            textView.setText("暂无数据");
        } else {
            textView.setText(listDataStr);
        }
    }

    /**
     * 存储list set map类型数据
     *
     * @param view
     */
    public void saveSetMapClick(View view) {
        Set set = new HashSet();
        set.add("jack");
        set.add("rose");
        set.add("hmm");
        set.add("lilei");
        set.add("jack");
        SharedPreferencesUtils.setDataSet(this, "set", set, FILE_NAME);
        Person person = new Person();
        person.setName("kobe");
        person.setAge(39);
        person.setSex("male");
        person.setSingle(false);
        person.setHeight(198);
        person.setEducationBackground("highSchool");
        Person person1 = new Person();
        person1.setName("kobe1");
        person1.setAge(23);
        person1.setSex("male");
        person1.setSingle(false);
        person1.setHeight(198);
        person1.setEducationBackground("highSchool");
        Map<String, Object> map = new HashMap<>();
        map.put("p1", person);
        map.put("p2", person1);
        SharedPreferencesUtils.setDataMap(this, "map", map, FILE_NAME);
        Toast.makeText(this, "存储set map成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取list set map类型数据
     *
     * @param view
     */
    public void getSetMapClick(View view) {
        Set set = SharedPreferencesUtils.getDataSet(this, "set", FILE_NAME);
        String setDataStr = "";
        if(set != null && set.size() > 0) {
            setDataStr = "setDataObj:" + set;
        }
        Map map = SharedPreferencesUtils.getDataMap(this, "map", Map.class, FILE_NAME);
        String mapDataStr = "";
        if(map != null && map.size() > 0) {
            mapDataStr = "mapDataObj:" + map;
        }
        TextView textView = (TextView) findViewById(R.id.tv_get_data_result_other);
        if(setDataStr.equals("") && mapDataStr.equals("")) {
            textView.setText("暂无数据");
        } else {
            textView.setText(setDataStr + "\n" + mapDataStr);
        }
    }

    /**
     * 存储对象类型数据
     *
     * @param view
     */
    public void saveObjectClick(View view) {
        Person person = new Person();
        person.setName("kobe");
        person.setAge(39);
        person.setSex("male");
        person.setSingle(false);
        person.setHeight(198);
        person.setEducationBackground("highSchool");
        SharedPreferencesUtils.setDataObj(this, "obj", person, FILE_NAME);
        Toast.makeText(this, "存储对象成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取序列化对象类型数据
     *
     * @param view
     */
    public void getObjectClick(View view) {
        Person person = SharedPreferencesUtils.getDataObj(this, "obj", Person.class, FILE_NAME);
        TextView textView = (TextView) findViewById(R.id.tv_get_data_result_object);
        String str;
        if(person != null) {
            str = "person.getName():" + person.getName() + "\n" +
                    "person.getAge():" + person.getAge() + "\n" +
                    "person.getEducationBackground():" + person.getEducationBackground() + "\n" +
                    "person.getSex():" + person.getSex() + "\n" +
                    "person.getHeight():" + person.getHeight() + "\n" +
                    "person.isSingle():" + person.isSingle();
            textView.setText(str);
        } else {
            textView.setText("暂无数据");
        }
    }

    /**
     * 保存图片
     *
     * @param view
     */
    public void setImageClick(View view) {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.img_car_audi);
        SharedPreferencesUtils.setDataImage(this, "image", bitmap, FILE_NAME);
        Toast.makeText(this, "存储图片成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取图片
     *
     * @param view
     */
    public void getImageClick(View view) {
        Drawable drawable = SharedPreferencesUtils.getDataImage(this, "image", FILE_NAME);
        if(drawable != null) {
            ImageView imageView = (ImageView) findViewById(R.id.iv_base_image);
            imageView.setImageDrawable(drawable);
        } else {
            Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 清空数据
     *
     * @param view
     */
    public void clearDataClick(View view) {
        SharedPreferencesUtils.clear(this, FILE_NAME);
        SharedPreferencesUtils.clear(this, FILE_NEME_LIST_STRING);
        SharedPreferencesUtils.clear(this, FILE_NEME_LIST_INTEGER);
        SharedPreferencesUtils.clear(this, FILE_NEME_LIST_MAP);
        SharedPreferencesUtils.clear(this, FILE_NEME_LIST_OBJ);
        Toast.makeText(this, "清空成功", Toast.LENGTH_SHORT).show();
    }

}
