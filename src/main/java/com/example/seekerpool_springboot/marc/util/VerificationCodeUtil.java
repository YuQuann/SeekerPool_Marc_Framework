package com.example.seekerpool_springboot.marc.util;

import java.util.Random;

public class VerificationCodeUtil {

    public static String generateVerificationCode(int length) {

        //驗證碼用的字集
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random random = new Random();

        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            //隨機取得字集的某個索引
            int index = random.nextInt(characters.length());
            //取得上列索引的字
            char randomChar = characters.charAt(index);
            //填入字串內
            code.append(randomChar);
        }

        return code.toString();

    }

}
