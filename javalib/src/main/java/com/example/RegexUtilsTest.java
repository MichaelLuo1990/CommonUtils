package com.example;

import com.utils.RegexUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc 正则表达式验证测试
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
    public static void testCheckEmail() {
        boolean result = RegexUtils.checkEmail("zha2_ngsan@sina.com.cn");
        Assert.assertTrue(result);
    }

    /**
     * 验证身份证号码
     */
    public void testCheckIdCard() {
        boolean result = RegexUtils.checkIdCard("432403193902273273");
        Assert.assertTrue(result);
    }

    /**
     * 验证手机号码
     */
    public void testCheckMobile() {
        boolean result = RegexUtils.checkMobile("+8613620285733");
        Assert.assertTrue(result);
    }

    /**
     * 验证电话号码
     */
    public void testCheckPhone() {
        boolean result = RegexUtils.checkPhone("+860738-4630706");
        Assert.assertTrue(result);
    }

    /**
     * 验证整数（正整数和负整数）
     */
    @Test
    public void testCheckDigit() {
        boolean result = RegexUtils.checkDigit("123132");
        Assert.assertTrue(result);
    }

    /**
     * 验证小数和整数（正负整数和正负小数）
     */
    public void testCheckDecimals() {
        boolean result = RegexUtils.checkDecimals("-33.2");
        Assert.assertTrue(result);
    }

    /**
     * 验证空白字符
     */
    @Test
    public void testCheckBlankSpace() {
        boolean result = RegexUtils.checkBlankSpace("           ");
        Assert.assertTrue(result);
    }

    /**
     * 匹配中文
     */
    public void testCheckChinese() {
        boolean result = RegexUtils.checkChinese("中文");
        Assert.assertTrue(result);
    }

    /**
     * 验证日期
     */
    @Test
    public void testCheckBirthday() {
        boolean result = RegexUtils.checkBirthday("1992/09/03");
        Assert.assertTrue(result);
    }

    /**
     * 验证中国邮政编码
     */
    public void testCheckPostcode() {
        boolean result = RegexUtils.checkPostcode("417100");
        Assert.assertTrue(result);
    }

    /**
     * 验证URL地址
     */
    public void testCheckURL() {
        boolean result = RegexUtils.checkURL("http://blog.csdn.com:80/xyang81/article/details?name=&abc=中文");
        Assert.assertTrue(result);
    }

    /**
     * 验证IP地址
     */
    public void testCheckIpAddress() {
        boolean result = RegexUtils.checkIpAddress("192.1.22.255");
        Assert.assertTrue(result);
    }
}

