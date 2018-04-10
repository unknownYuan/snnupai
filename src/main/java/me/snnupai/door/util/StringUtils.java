package me.snnupai.door.util;

public class StringUtils {
    public static boolean isNotBlank(String s){
        return s != null && !s.trim().equals("");
    }
    public static boolean isBlank(String s){
        return !isNotBlank(s);
    }
}
