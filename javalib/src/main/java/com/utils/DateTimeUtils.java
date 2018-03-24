package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Desc 日期/时间处理工具类
 * Created by Michael on 2018/3/23.
 */

public class DateTimeUtils {
    private static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";//默认显示格式（常用）
    public static String FORMAT_Y = "yyyy";//英文简写如：2010
    public static String FORMAT_YM = "yyyy-MM";//英文简写如：2010-12
    public static String FORMAT_YMD = "yyyy-MM-dd";//英文简写（默认）如：2010-12-01
    public static String FORMAT_MD = "MM-dd";//英文简写如：1-12
    public static String FORMAT_MDHM = "MM-dd HH:mm";//英文简写如：1-12 12:01
    public static String FORMAT_HM = "HH:mm";//英文简写如：12:01
    public static String FORMAT_HMS = "HH:mm:ss";//英文简写如：12:01:06
    public static String FORMAT_YMDH = "yyyy-MM-dd HH";//英文全称  如：2010-12-01 23
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";//英文全称  如：2010-12-01 23:15
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";//英文全称  如：2010-12-01 23:15:06
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";//英文全称   精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
    //带中文显示
    public static String FORMAT_MD_CN = "MM月dd日";//中文简写  如：12月01日
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";//中文简写  如：2010年12月01日
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";//中文简写  如：2010年12月01日  12时
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";//中文简写  如：2010年12月01日  12时12分
    public static String FORMAT_HM_CN = "HH时mm分";//中文简写  如：12时12分
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日 HH时mm分ss秒";//中文全称  如：2010年12月01日  23时15分06秒
    public static String FORMAT_HMS_CN = "HH时mm分ss秒";//中文全称  如：23时15分06秒
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒S毫秒";//中文显示  精确到毫秒的完整时间    如：2018年03月24日 14时46分39秒443毫秒
//    public static Calendar calendar = null;

    //===============================================================transform======================================================================

    /**
     * 字符串转Date
     *
     * @param str
     * @param format
     * @return 指定格式类型Date
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT_DEFAULT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转Date
     *
     * @param str
     * @return 默认格式类型Date
     */
    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * 字符串转Calendar
     *
     * @param str
     * @param format
     * @return 指定格式Calendar
     */
    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 字符串转Calendar
     *
     * @param str
     * @return 默认格式类型Calendar
     */
    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }

    /**
     * Date转字符串
     *
     * @param d
     * @param format
     * @return 指定格式字符串
     */
    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT_DEFAULT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     * Date转字符串
     *
     * @param d
     * @return 默认格式字符串
     */
    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    /**
     * Calendar转字符串
     *
     * @param c
     * @param format
     * @return 指定格式字符串
     */
    public static String calendar2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    /**
     * Calendar转字符串
     *
     * @param c
     * @return 默认格式字符串
     */
    public static String calendar2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return calendar2Str(c, null);
    }

    /**
     * Date转Calendar
     *
     * @param date
     * @return
     */
    public static Calendar date2Calendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * Calendar转Date
     *
     * @param calendar
     * @return
     */
    public static Date calendar2Date(Calendar calendar) {
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 毫秒转化字符串
     *
     * @param time
     * @param format
     * @return 指定格式字符串
     */
    public static String millisecond2Str(long time, String format) {
        return new SimpleDateFormat(format).format(time);
    }

    /**
     * 毫秒转化字符串
     *
     * @param time
     * @return 默认格式字符串
     */
    public static String millisecond2Str(long time) {
        return new SimpleDateFormat(FORMAT_DEFAULT).format(time);//src  "yyyy-MM-dd-HH-mm-ss"
    }
    //===============================================================transform======================================================================

    /**
     * 方法一 System.currentTimeMillis();  最快
     * 方法二 Calendar.getInstance().getTimeInMillis();  速度最慢，处理时区转化
     * 方法三 new Date().getTime();  次之
     *
     * @return
     */
    public static long getCurTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     *
     * @return 默认显示格式字符串  yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + " " +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获取当前日期的字符串
     *
     * @param format 格式化的类型
     * @return 格式化的时间
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return calendar2Str(c, format);
    }


    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return 增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();

    }


    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return 增加之后的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();

    }


    /**
     * 获取距现在某一小时的时刻
     *
     * @param format 格式化时间的格式
     * @param h      距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return 获取距现在某一小时的时刻
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }


    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }


    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }


    /**
     * 获得默认的 date pattern
     *
     * @return 默认的格式
     */
    public static String getDatePattern() {
        return FORMAT_YMDHMS;
    }


    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return 提取字符串的日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());

    }


    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return 按默认格式的字符串距离今天的天数
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return 提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return 按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }
}