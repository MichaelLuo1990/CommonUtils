package com.example;

import com.utils.RegexUtils;

import org.junit.Test;

/**
 * Desc 正则表达式验证测试
 * ref http://www.jb51.net/article/72867.htm
 * Created by Michael on 2018/3/19.
 */

public class RegexUtilsTest {

    public static void main(String[] args) {
        System.out.println(RegexUtils.checkEmail("zha2_ngsan@sina.com.cn"));
        System.out.println(RegexUtils.checkIdCard("432403193902273273"));
    }

    /**
     * 验证邮箱
     */
    @Test
    public void testCheckEmail() {
        boolean result = RegexUtils.checkEmail("zha2_ngsan@sina.com.cn");
        if(result) {
            System.out.println("邮箱可用");
        } else {
            System.out.println("邮箱不可用");
        }
    }

    /**
     * 验证身份证号码
     */
    @Test
    public void testCheckIdCard() {
        boolean result = RegexUtils.checkIdCard("432403193902273273");
        if(result) {
            System.out.println("身份证号码可用");
        } else {
            System.out.println("身份证号码不可用");
        }
    }

    /**
     * 验证手机号码
     */
    @Test
    public void testCheckMobile() {
        boolean result = RegexUtils.checkMobile("+8613620285733");
        if(result) {
            System.out.println("手机号码可用");
        } else {
            System.out.println("手机号码不可用");
        }
    }

    /**
     * 验证固定电话号码
     */
    @Test
    public void testCheckPhone() {
        boolean result = RegexUtils.checkPhone("+860738-4630706");
        if(result) {
            System.out.println("固定电话号码可用");
        } else {
            System.out.println("固定电话号码不可用");
        }
    }

    /**
     * 验证整数（正整数和负整数）
     */
    @Test
    public void testCheckDigit() {
        boolean result = RegexUtils.checkDigit("-67");
        if(result) {
            System.out.println("该整数为正整数");
        } else {
            System.out.println("该整数为负整数");
        }
    }

    /**
     * 验证小数或整数
     */
    @Test
    public void testCheckDecimals() {
        boolean result = RegexUtils.checkDecimals("5d55");
        if(result) {
            System.out.println("小数或整数");
        } else {
            System.out.println("非小数或整数");
        }
    }

    /**
     * 验证空白字符
     */
    @Test
    public void testCheckBlankSpace() {
        boolean result = RegexUtils.checkBlankSpace("           ");
        if(result) {
            System.out.println("存在空白字符");
        } else {
            System.out.println("无空白字符");
        }
    }

    /**
     * 匹配(纯)中文
     */
    @Test
    public void testCheckChinese() {
        boolean result = RegexUtils.checkChinese("中文");
        if(result) {
            System.out.println("该字符串为中文");
        } else {
            System.out.println("非中文字符串");
        }
    }

    /**
     * 验证日期
     * 1992/09/03   1992.09.03  两种格式
     */
    @Test
    public void testCheckBirthday() {
        boolean result = RegexUtils.checkBirthday("1992.9.03");
        if(result) {
            System.out.println("该字符串为日期");
        } else {
            System.out.println("非日期字符串");
        }
    }

    /**
     * 验证中国邮政编码 (6位数)
     */
    @Test
    public void testCheckPostcode() {
        boolean result = RegexUtils.checkPostcode("365500");
        if(result) {
            System.out.println("有效邮编");
        } else {
            System.out.println("无效邮编");
        }
    }

    /**
     * 验证URL地址
     * 测试数据：http://blog.csdn.com:80/xyang81/article/details?name=&abc=中文    www.baidu.com
     */
    @Test
    public void testCheckURL() {
        boolean result = RegexUtils.checkURL("weibo");
        if(result) {
            System.out.println("有效URL地址");
        } else {
            System.out.println("无效URL地址");
        }
    }

    /**
     * 验证IP地址
     * 测试数据：256.111.22.342      0.0.0.0     127.0.0.1    192.168.1.1   255.255.255.255
     */
    @Test
    public void testCheckIpAddress() {
        boolean result = RegexUtils.checkIpAddress("0.0.0.0");
        if(result) {
            System.out.println("有效IP地址");
        } else {
            System.out.println("无效IP地址");
        }
    }
}

