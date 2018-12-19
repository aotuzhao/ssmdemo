package com.zhao.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

public class Md5Util {

    public static String getSalt() {
        char[] array = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                .toCharArray();

        StringBuilder sbu = new StringBuilder("");
        Random random = new Random();

        for (int i = 1; i <= 6; i++) {
            int num = random.nextInt(array.length);
            sbu.append(array[num]);
        }

        return sbu.toString();
    }

    public static String encryption(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static boolean checkPassword(String src, String pwd) {
        if (src == null) {
            throw new RuntimeException();
        }
        if (pwd == null) {
            throw new RuntimeException();
        }
        return encryption(src).equals(pwd);
    }

}
