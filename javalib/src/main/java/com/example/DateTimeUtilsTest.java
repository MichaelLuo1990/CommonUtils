package com.example;

import com.utils.DateTimeUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Desc 日期、时间装换测试
 * Created by Michael on 2018/3/23.
 */

public class DateTimeUtilsTest {

    public static void main(String[] args) {

        //====================================================转换相关测试=======================================================
        System.out.println("======================================string -> date || calendar======================================");
        System.out.println(DateTimeUtils.str2Date("2018-03-24 14:59:34"));//默认格式
        System.out.println(DateTimeUtils.str2Date("2018-03-24 14:59:34", DateTimeUtils.FORMAT_YMDHM));//自定义格式
        System.out.println(DateTimeUtils.str2Calendar("2018-03-24 14:59:34"));//默认
        System.out.println(DateTimeUtils.str2Calendar("2018-03-24 14:59:34", DateTimeUtils.FORMAT_YMD));//自定义

        System.out.println("======================================calendar -> string======================================");
        Calendar calendar = Calendar.getInstance();
        System.out.println(DateTimeUtils.calendar2Str(calendar));//默认
        System.out.println(DateTimeUtils.calendar2Str(calendar, DateTimeUtils.FORMAT_YMDHM_CN));//自定义

        System.out.println("======================================date -> string======================================");
        Date date = new Date(DateTimeUtils.getCurTimeMillis());
        System.out.println(DateTimeUtils.date2Str(date));//默认
        System.out.println(DateTimeUtils.date2Str(date, DateTimeUtils.FORMAT_YMDHMS_CN));//自定义

        System.out.println("======================================date <=> calendar======================================");
        System.out.println(DateTimeUtils.date2Calendar(date));
        System.out.println(DateTimeUtils.calendar2Date(calendar));

        System.out.println("======================================获取当前系统时间（毫秒）-》转String======================================");
        System.out.println(DateTimeUtils.millisecond2Str(DateTimeUtils.getCurTimeMillis()));//默认格式  2018-03-24 14:41:56
        System.out.println(DateTimeUtils.millisecond2Str(DateTimeUtils.getCurTimeMillis(), DateTimeUtils.FORMAT_FULL_CN));//自定义格式

        //获取当前时间字符串
        System.out.println(DateTimeUtils.getCurDateStr());//显示格式字符串  yyyy-MM-dd HH:mm:ss
        System.out.println(DateTimeUtils.getCurDateStr(DateTimeUtils.FORMAT_YMDHMS_CN));//自定义格式

    }

}
