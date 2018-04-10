package com.example;

import com.utils.DateTimeUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Desc 日期、时间装换测试
 * Created by Michael on 2018/3/23.
 */

public class DateTimeUtilsTest {

    public static void main(String[] args) {

        //====================================================工具类相关测试=======================================================
        /**
         * 验证日期格式是否合法
         */
        boolean validDate = DateTimeUtils.isValidDate("公元2018年03月26日 14时34分11秒", "Gyyyy年MM月dd日 HH时mm分ss秒");
        System.out.println("是否合法date格式" + validDate);

        //====================================================转换相关测试=======================================================
        System.out.println("======================================string ->  calendar || date || long（时间戳）======================================");
        System.out.println(DateTimeUtils.dateStr2Calendar("2018-03-24 14:59:34", "yyyy-MM-dd"));
        System.out.println(DateTimeUtils.dateStr2Date("2018-03-24 14:59:34", "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtils.dateStr2TimestampStr("2018-03-27 14:01:59", "yyyy-MM-dd HH:mm:ss", true));
        System.out.println(DateTimeUtils.dateStr2TimestampStr("2018-03-27 14:01:59.345", "yyyy-MM-dd HH:mm:ss.S", false));

        System.out.println("======================================calendar || date || long(获取当前系统时间（毫秒）或 时间戳) -> string======================================");
        Calendar calendar = Calendar.getInstance();
        System.out.println(DateTimeUtils.calendar2DateStr(calendar, DateTimeUtils.FORMAT_YMDHM_CN));
        Date date = new Date(DateTimeUtils.getCurTimestamp());
        System.out.println(DateTimeUtils.date2DateStr(date, DateTimeUtils.FORMAT_YMDHMS_CN));
        System.out.println(DateTimeUtils.timestampStr2DateStr(DateTimeUtils.getCurSecondStr(), "yyyy-MM-dd HH:mm:ss"));

        System.out.println("======================================date <=> calendar======================================");
        System.out.println(DateTimeUtils.date2Calendar(date));
        System.out.println(DateTimeUtils.calendar2Date(calendar));

        //====================================================其他测试=======================================================
        System.out.println("======================================获取当前时间字符串======================================");
        System.out.println(DateTimeUtils.getCurDateTiemStr());//显示 yyyy-MM-dd HH:mm:ss 格式字符串
        System.out.println(DateTimeUtils.getCurDateTimeStr(DateTimeUtils.FORMAT_YMDHMS_CN));//自定义格式字符串

        System.out.println("======================================获取指定时间距离当前时间前-差值======================================");
        System.out.println(DateTimeUtils.getBeforeCurTime("2018-03-27 14:59:34"));

        System.out.println("======================================指定时间增加整数（年 月 日 时 分 秒）======================================");
        Date dateStr2Date = DateTimeUtils.dateStr2Date("2018-01-10 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(DateTimeUtils.date2DateStr(DateTimeUtils.setDiffValueYMDHMS(dateStr2Date, Calendar.YEAR, -1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtils.date2DateStr(DateTimeUtils.setDiffValueYMDHMS(dateStr2Date, Calendar.MONTH, -1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtils.date2DateStr(DateTimeUtils.setDiffValueYMDHMS(dateStr2Date, Calendar.DAY_OF_MONTH, -1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtils.date2DateStr(DateTimeUtils.setDiffValueYMDHMS(dateStr2Date, Calendar.HOUR, -1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtils.date2DateStr(DateTimeUtils.setDiffValueYMDHMS(dateStr2Date, Calendar.MINUTE, -1), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateTimeUtils.date2DateStr(DateTimeUtils.setDiffValueYMDHMS(dateStr2Date, Calendar.SECOND, -1), "yyyy-MM-dd HH:mm:ss"));

        //====================================================原生对象相关测试=======================================================
        System.out.println("======================================Calendar获取详细测试======================================");
        Calendar now = Calendar.getInstance();
        System.out.println("年: " + now.get(Calendar.YEAR));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + now.get(Calendar.MINUTE));
        System.out.println("秒: " + now.get(Calendar.SECOND));
        System.out.println("毫秒：" + now.get(Calendar.MILLISECOND));
        System.out.println("当前总时间(毫秒)数：" + now.getTimeInMillis());

        System.out.println("======================================SimpleDateFormat与Date格式化操作测试======================================");
        SimpleDateFormat CeshiFmt0 = new SimpleDateFormat("Gyyyy年MM月dd日 HH时mm分ss秒");
        SimpleDateFormat CeshiFmt1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat CeshiFmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat CeshiFmt3 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        SimpleDateFormat CeshiFmt4 = new SimpleDateFormat("yyyy/MM/dd E");
        SimpleDateFormat CeshiFmt5 = new SimpleDateFormat(
                "一年中的第 D 天 ，第w个星期 ，一个月中第W个星期 ，k时 z时区");
        Date dt = new Date();
        System.out.println(CeshiFmt0.format(dt));
        System.out.println(CeshiFmt1.format(dt));
        System.out.println(CeshiFmt2.format(dt));
        System.out.println(CeshiFmt3.format(dt));
        System.out.println(CeshiFmt4.format(dt));
        System.out.println(CeshiFmt5.format(dt));
    }

}
