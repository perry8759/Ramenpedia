package com.ramenpedia.service;


import java.security.SecureRandom;
import java.util.Calendar;

public class CodeGeneratingService {

    private final static String[] numArray = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    public static String getUid() {
        // 第一碼規則：個人戶1-8，(年份-2022)取8的餘數+1
        String firstCharacter = String.valueOf((Calendar.getInstance().get(Calendar.YEAR) - 2022) % 8 + 1);
        String uid = firstCharacter + getRandomString(9);

        return uid;
    }

    private static String getRandomString(int digit) {
        String[] charPool = numArray;
        int partitionLength = 4;
        SecureRandom rand = new SecureRandom();
        int num = rand.nextInt(Integer.MAX_VALUE);
        StringBuilder result = new StringBuilder();
        while (digit > partitionLength) {
            digit -= partitionLength;
            for (int i = 0; i < partitionLength; i++) {
                result.insert(0, charPool[num % charPool.length]);
                num = num / charPool.length;
            }
        }
        num = rand.nextInt(Integer.MAX_VALUE);
        for (int i = 0; i < digit; i++) {
            result.insert(0, charPool[num % charPool.length]);
            num = num / charPool.length;
        }
        return result.toString();
    }
}
