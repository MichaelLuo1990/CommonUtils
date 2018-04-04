package com.michael.commonutils.entity;

import java.io.Serializable;

/**
 * Desc
 * Created by Michael on 2018/4/3.
 */

public class Person implements Serializable {

    private static final long serialVersionUID = 2421263553592651152L;
    private String name;
    private int age;
    private String sex;
    private int height;
    private String educationBackground;
    private boolean isSingle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

}
