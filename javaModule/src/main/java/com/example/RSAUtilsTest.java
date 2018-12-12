package com.example;

import com.utils.Base64Utils;
import com.utils.RSAUtils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Desc
 * Created by Michael on 2018/11/22.
 */

public class RSAUtilsTest {

    public static void main(String[] args) {
//        List<Person> personList = new ArrayList<>();
//        int testMaxCount = 100;//测试的最大数据条数
//        //添加测试数据
//        for (int i = 0; i < testMaxCount; i++) {
//            Person person = new Person();
//            person.setAge(i);
//            person.setName(String.valueOf(i));
//            personList.add(person);
//        }
//        //FastJson生成json数据
//
//        String jsonData = JsonUtils.objectToJsonForFastJson(personList);
        String dataSrcStr = "abcdefg1234567";
        System.out.println("加密源数据 ---->" + dataSrcStr);
        System.out.println("加密源数据长度 ---->" + dataSrcStr.length() +"字节");
        KeyPair keyPair = RSAUtils.generateRSAKeyPair(RSAUtils.DEFAULT_KEY_SIZE);
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        System.out.println("<------------------------公钥加密&私钥解密------------------------------->");
        //公钥加密
        long start = System.currentTimeMillis();
        byte[] encryptBytes = new byte[0];
        try {
            encryptBytes = RSAUtils.encryptByPublicKeyForSpilt(dataSrcStr.getBytes(), publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("公钥加密耗时---->" + (end - start) +"毫秒");
        String encryStr = Base64Utils.encode(encryptBytes);
        System.out.println("加密后数据 ---->" + encryStr);
        System.out.println("加密后数据长度 ---->" + encryStr.length() + "字节");
        //私钥解密
        start = System.currentTimeMillis();
        byte[] decryptBytes = new byte[0];
        try {
            decryptBytes = RSAUtils.decryptByPrivateKeyForSpilt(Base64Utils.decode(encryStr), privateKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String decryStr = new String(decryptBytes);
        end = System.currentTimeMillis();
        System.out.println("私钥解密耗时---->" + (end - start) +"毫秒");
        System.out.println("解密后数据 ---->" + decryStr);

        System.out.println("<------------------------私钥加密&公钥解密------------------------------->");
        //私钥加密
        start = System.currentTimeMillis();
        try {
            encryptBytes = RSAUtils.encryptByPrivateKeyForSpilt(dataSrcStr.getBytes(), privateKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        end = System.currentTimeMillis();
        System.out.println("私钥加密密耗时---->" + (end - start) + "毫秒");
        encryStr = Base64Utils.encode(encryptBytes);
        System.out.println("加密后数据 ---->" + encryStr);
        System.out.println("加密后数据长度 ---->" + encryStr.length() + "字节");
        //公钥解密
        start = System.currentTimeMillis();
        try {
            decryptBytes = RSAUtils.decryptByPublicKeyForSpilt(Base64Utils.decode(encryStr), publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        decryStr = new String(decryptBytes);
        end = System.currentTimeMillis();
        System.out.println("公钥解密耗时---->" + (end - start) +"毫秒");
        System.out.println("解密后数据 ---->" + decryStr);
    }

}
