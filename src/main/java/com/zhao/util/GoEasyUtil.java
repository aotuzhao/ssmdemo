package com.zhao.util;

import io.goeasy.GoEasy;

/**
 * @author aotu
 * @date 2018/12/28 14:17
 */
public class GoEasyUtil {
    private static final String REST_HOST = "rest-hangzhou.goeasy.io";
    private static final String CDN_HOST = "cdn-hangzhou.goeasy.io";
    private static final String APP_KEYS = "BC-6672cd4d416c4980b3921e3f24d341cd";

    public static GoEasy getInstance() {
        GoEasy goEasy = new GoEasy(REST_HOST, APP_KEYS);
        return goEasy;
    }
}
