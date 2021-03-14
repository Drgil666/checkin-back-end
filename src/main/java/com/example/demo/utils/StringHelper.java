package com.example.demo.utils;

/**
 * @author Happier233
 */
public final class StringHelper {

    public static String defaultString(String value) {
        return value == null ? "" : value;
    }

    public static String defaultString(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }

}
