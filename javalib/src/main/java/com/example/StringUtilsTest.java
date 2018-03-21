package com.example;

import com.utils.StringUtils;

import java.text.ParseException;

/**
 * Desc 字符串测试
 * Created by Michael on 2018/3/20.
 */

public class StringUtilsTest {

    public static void main(String[] args) {
        System.out.println("字符串是否为空：" + StringUtils.isEmpty(""));
        System.out.println("字符串是否为纯数字：" + StringUtils.isNumeric("4543"));
        System.out.println("字符串是否为纯字母：" + StringUtils.isAlphabet("fdsgf"));
        System.out.println("字符串是否为纯汉字：" + StringUtils.isChineseCharacters("你好"));
        System.out.println("字符串是否为YYYY-MM-DD格式：" + StringUtils.isDataFormat("1990-07-02"));
        System.out.println("字符串是否包含特殊字符：" + StringUtils.isExcptional("&#@*-"));
        System.out.println("字符串判断是否符合生产密码的约束条件，不能是纯数字，不能输纯字母，数字加字母结合的6到16位：" + StringUtils.isPasswordRestrict("asdf2344"));
        System.out.println("字符串判断是否手机号：" + StringUtils.isPhoneNumber("45635463"));
        System.out.println("字符串设置模糊手机号码：" + StringUtils.setBlurryPhone("13500980987"));
        System.out.println("小数转换成百分数：" + StringUtils.decimalToPercentage(0.888));
        System.out.println("格式化钱(保留两位小数)：" + StringUtils.moneyFormatDecimal(1000.808));
        System.out.println("格式化钱(三个数字加上逗号处理，保留两位小数)：" + StringUtils.moneyFormatDecimal(34734563.457));
        System.out.println("格式化钱(三个数字加上逗号处理)：" + StringUtils.moneyFormatSplit("34734563.457456"));
        try {
            String idCardValidate = StringUtils.IDCardValidate("350746196709132567");
            if(idCardValidate.equals("")) {
                System.out.println("验证身份证有效");
            } else {
                System.out.println("验证身份证无效：" + idCardValidate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
