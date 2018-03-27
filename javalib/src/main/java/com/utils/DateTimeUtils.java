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
    public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";//默认显示格式（常用）
    public static String FORMAT_Y = "yyyy";//英文简写如：2010
    public static String FORMAT_YM = "yyyy-MM";//英文简写如：2010-12
    public static String FORMAT_YMD = "yyyy-MM-dd";//英文简写（默认）如：2010-12-01
    public static String FORMAT_MD = "MM-dd";//英文简写如：1-12
    public static String FORMAT_MDHM = "MM-dd HH:mm";//英文简写如：1-12 12:01
    public static String FORMAT_HM = "HH:mm";//英文简写如：12:01
    public static String FORMAT_HMS = "HH:mm:ss";//英文简写如：12:01:06
    public static String FORMAT_YMDH = "yyyy-MM-dd HH";//英文全称  如：2010-12-01 23
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";//英文全称  如：2010-12-01 23:15
    //    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";//英文全称  如：2010-12-01 23:15:06
//    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";//英文全称   精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
    //带中文显示
    public static String FORMAT_MD_CN = "MM月dd日";//中文简写  如：12月01日
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";//中文简写  如：2010年12月01日
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";//中文简写  如：2010年12月01日  12时
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";//中文简写  如：2010年12月01日  12时12分
    public static String FORMAT_HM_CN = "HH时mm分";//中文简写  如：12时12分
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日 HH时mm分ss秒";//中文全称  如：2010年12月01日  23时15分06秒
    public static String FORMAT_HMS_CN = "HH时mm分ss秒";//中文全称  如：23时15分06秒
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日 HH时mm分ss秒S毫秒";//中文显示  精确到毫秒的完整时间    如：2018年03月24日 14时46分39秒443毫秒

    /**
     * 验证日期格式是否合法
     *
     * @param curStr     待验证日期字符串
     * @param patternStr 验证模板
     * @return 是否合法     如果throw java.text.ParseException或者NullPointerException，就说明格式不对
     */
    public static boolean isValidDate(String curStr, String patternStr) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(patternStr);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(curStr);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    //===============================================================transform======================================================================

    /**
     * 日期格式字符串转Date
     *
     * @param str
     * @param format
     * @return 指定格式类型Date
     */
    public static Date dateStr2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            return null;
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
     * 日期格式字符串转Calendar
     *
     * @param str
     * @param format
     * @return 指定格式Calendar
     */
    public static Calendar dateStr2Calendar(String str, String format) {
        Date date = dateStr2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * Date转日期格式字符串
     *
     * @param d
     * @param format
     * @return 指定格式字符串
     */
    public static String date2DateStr(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     * Calendar转日期格式字符串
     *
     * @param c
     * @param format
     * @return 指定格式字符串
     */
    public static String calendar2DateStr(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2DateStr(c.getTime(), format);
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
     * 时间戳字符串转换成日期格式字符串
     *
     * @param timestampStr 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timestampStr2DateStr(String timestampStr, String format) {
        if (timestampStr == null || timestampStr.isEmpty() || timestampStr.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(timestampStr + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳字符串
     *
     * @param dateStr  字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd HH:mm:ss.S
     * @param isSecond 是否精确到秒(毫秒)
     * @return
     */
    public static String dateStr2TimestampStr(String dateStr, String format, boolean isSecond) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (isSecond) {
                return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
            } else {
                return String.valueOf(sdf.parse(dateStr).getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //===============================================================transform======================================================================

    /**
     * 获取当前时间戳(精确到毫秒)
     * 方法一 System.currentTimeMillis();  最快
     * 方法二 Calendar.getInstance().getTimeInMillis();  速度最慢，处理时区转化
     * 方法三 new Date().getTime();  次之
     *
     * @return
     */
    public static long getCurTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 取得当前时间戳字符串（精确到秒）
     *
     * @return
     */
    public static String getCurSecondStr() {
        long time = System.currentTimeMillis();
        String t = String.valueOf(time / 1000);
        return t;
    }

    /**
     * 获取当前日期/时间字符串
     *
     * @return 默认显示格式字符串  yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDateTiemStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + " " +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }

    /**
     * 获取当前日期/日期的字符串
     *
     * @param format 格式化的类型
     * @return 自定义格式化的时间
     */
    public static String getCurDateTimeStr(String format) {
        Calendar c = Calendar.getInstance();
        return calendar2DateStr(c, format);
    }

    /**
     * 计算距离当前的时间（前后）差值
     *
     * @param time
     * @return
     */
    public static String getBeforeCurTime(String time) {
        long nowTime = System.currentTimeMillis();  //获取当前时间的毫秒数
        String msg = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//指定时间格式
        Date setTime = null;  //指定时间
        try {
            setTime = sdf.parse(time);  //将字符串转换为指定的时间格式
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long reset = setTime.getTime();   //获取指定时间的毫秒数
        long dateDiff = nowTime - reset;
        long abs = Math.abs(dateDiff);
        long dateTemp1 = abs / 1000; //秒
        long dateTemp2 = dateTemp1 / 60; //分钟
        long dateTemp3 = dateTemp2 / 60; //小时
        long dateTemp4 = dateTemp3 / 24; //天数
        long dateTemp5 = dateTemp4 / 30; //月数
        long dateTemp6 = dateTemp5 / 12; //年数
        if (dateDiff < 0) {
            if (dateTemp6 > 0) {
                msg = dateTemp6 + "年后";
            } else if (dateTemp5 > 0) {
                msg = dateTemp5 + "个月后";
            } else if (dateTemp4 > 0) {
                msg = dateTemp4 + "天后";
            } else if (dateTemp3 > 0) {
                msg = dateTemp3 + "小时后";
            } else if (dateTemp2 > 0) {
                msg = dateTemp2 + "分钟后";
            } else if (dateTemp1 > 0) {
                msg = "刚刚";
            }
        } else {
            if (dateTemp6 > 0) {
                msg = dateTemp6 + "年前";
            } else if (dateTemp5 > 0) {
                msg = dateTemp5 + "个月前";
            } else if (dateTemp4 > 0) {
                msg = dateTemp4 + "天前";
            } else if (dateTemp3 > 0) {
                msg = dateTemp3 + "小时前";
            } else if (dateTemp2 > 0) {
                msg = dateTemp2 + "分钟前";
            } else if (dateTemp1 > 0) {
                msg = "刚刚";
            }
        }
        return msg;
    }

    /**
     * @param date    在指定日期上增加/减少整（年 月 日 时 分 秒）数
     * @param CalType 年-Calendar.YEAR
     *                月-Calendar.MONTH
     *                日-Calendar.DAY_OF_MONTH
     *                时-Calendar.HOUR
     *                分-Calendar.MINUTE
     *                秒-Calendar.SECOND
     * @param i       增(1)/减(-1)数量
     * @return
     */
    public static Date setDiffValueYMDHMS(Date date, int CalType, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(CalType, i);
        return cal.getTime();
    }

}
