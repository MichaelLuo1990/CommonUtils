package com.michael.commonutils.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.michael.commonutils.R;
import com.michael.commonutils.entity.Person;
import com.michael.commonutils.utils.AesUtils;
import com.michael.commonutils.utils.Base64Utils;
import com.michael.commonutils.utils.Des3Utils;
import com.michael.commonutils.utils.MD5Util;
import com.michael.commonutils.utils.RSAUtils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

public class CryptionActivity extends AppCompatActivity {

    private EditText etRaw;
    private TextView tvAesDes;
    private TextView tvDesDes;
    private TextView tvMd5Des;
    private TextView tvRsaDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cryption);
        etRaw = (EditText) findViewById(R.id.et_raw);
        tvMd5Des = (TextView) findViewById(R.id.tv_md5_des);
        tvAesDes = (TextView) findViewById(R.id.tv_aes_des);
        tvDesDes = (TextView) findViewById(R.id.tv_des_des);
        tvRsaDes = (TextView) findViewById(R.id.tv_rsa_des);
    }

    private String getEncryptionStr() {
        String raw = etRaw.getText().toString();
        if (raw == null || raw.length() <= 0) {
            Toast.makeText(this, "请输入待加密字符串", Toast.LENGTH_LONG).show();
        }
        return raw;
    }

    /**
     * MD5加密测试
     * @param view
     */
    public void btnMD5Click(View view) {
        String encryptionStr = getEncryptionStr();
        if(!encryptionStr.isEmpty()) {
            String enStr = MD5Util.encrypBy(getEncryptionStr());
            tvMd5Des.setText("MD5的加密结果:" + enStr);
        }
    }

    /**
     * AES加/解密测试
     * @param view
     */
    public void btnAESClick(View view) {
        String encryptionStr = getEncryptionStr();
        if(!encryptionStr.isEmpty()) {
            try {
                String seed = "a";
                String enStr = AesUtils.encrypt(seed, encryptionStr);
                String deStr = AesUtils.decrypt(seed, enStr);
                String desc = String.format("AES加密结果:%s\nAES解密结果:%s", enStr, deStr);
                tvAesDes.setText(desc);
            } catch (Exception e) {
                e.printStackTrace();
                tvAesDes.setText("AES加密/解密失败");
            }
        }
    }

    /**
     * DES加/解密测试
     * @param view
     */
    public void btnDESClick(View view) {
        String encryptionStr = getEncryptionStr();
        if(!encryptionStr.isEmpty()) {
            String key = "a";
            String enStr = Des3Utils.encrypt(key, encryptionStr);
            String deStr = Des3Utils.decrypt(key, enStr);
            String desc = String.format("3DES加密结果:%s\n3DES解密结果:%s", enStr, new String(deStr));
            tvDesDes.setText(desc);
        }
    }

    /**
     * RSA加/解密测试
     * 可将list,obj等相关对象装换为json字符串格式加密
     * @param view
     */
    public void btnRSAClick(View view) {
        Toast.makeText(this,"RSA解密处理中，请稍后", Toast.LENGTH_SHORT).show();
        String encryptionStr = getEncryptionStr();
        if(!encryptionStr.isEmpty()) {
            String result = "";
            result += "加密源数据 ---->" + encryptionStr + "\n" + "加密源数据长度 ---->" + encryptionStr.length() +"字节" + "\n";
            KeyPair keyPair = RSAUtils.generateRSAKeyPair(RSAUtils.DEFAULT_KEY_SIZE);
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            result += "******" + "公钥加密&私钥解密" + "******" +"\n";
            //公钥加密
            long start = System.currentTimeMillis();
            byte[] encryptBytes = new byte[0];
            try {
                encryptBytes = RSAUtils.encryptByPublicKeyForSpilt(encryptionStr.getBytes(), publicKey.getEncoded());
            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            String encryStr = Base64Utils.encode(encryptBytes);
            result += "公钥加密耗时---->" + (end - start) +"毫秒" + "\n" + "加密后数据 ---->" + encryStr + "\n" + "加密后数据长度 ---->" + encryStr.length() + "字节" + "\n";
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
            result += "私钥解密耗时---->" + (end - start) +"毫秒" + "\n" + "解密后数据 ---->" + decryStr + "\n" ;
            result += "******" + "私钥加密&公钥解密" + "******" +"\n";
            //私钥加密
            start = System.currentTimeMillis();
            try {
                encryptBytes = RSAUtils.encryptByPrivateKeyForSpilt(encryptionStr.getBytes(), privateKey.getEncoded());
            } catch (Exception e) {
                e.printStackTrace();
            }
            end = System.currentTimeMillis();
            encryStr = Base64Utils.encode(encryptBytes);
            result += "私钥加密密耗时---->" + (end - start) + "毫秒" + "\n" + "加密后数据 ---->" + encryStr + "\n" + "加密后数据长度 ---->" + encryStr.length() + "字节" + "\n" ;
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
            result += "公钥解密耗时---->" + (end - start) +"毫秒" + "\n" + "解密后数据 ---->" + decryStr + "\n";
            tvRsaDes.setText(result);
        }
    }

    /**
     * 创建测试数据
     * @return
     */
    private List<Person> createData() {
        List<Person> personList = new ArrayList<>();
        int testMaxCount = 100;//测试的最大数据条数
        //添加测试数据
        for (int i = 0; i < testMaxCount; i++) {
            Person person = new Person();
            person.setSex("男");
            person.setHeight(175);
            person.setEducationBackground("highSchool");
            person.setSingle(true);
            person.setAge(i);
            person.setName(String.valueOf(i));
            personList.add(person);
        }
        return personList;
    }

}
