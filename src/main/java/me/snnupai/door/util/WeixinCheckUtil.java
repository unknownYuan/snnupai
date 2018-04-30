package me.snnupai.door.util;

import java.util.regex.Pattern;

public class WeixinCheckUtil {
    public static boolean checkWeixin(String weixin){
        if(weixin == null){
            return false;
        }
        String pattern = "^[a-zA-Z][a-zA-Z0-9_-]{5,19}$";
        return Pattern.matches(pattern, weixin);
    }
//    public static void main(String[] args) {
//        boolean result = checkWeixin("1593028064");
//        System.out.println(result);
//    }
}
