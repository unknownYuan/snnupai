package me.snnupai.door.util;

public class QQCheckUtils {
    /*
     * 写一个功能实现校验 两个明确： 明确返回值类型：boolean 明确参数列表：String qq
     */
    public static boolean checkQQ(String qq) {
        if(qq == null){
            return false;
        }else {
            boolean result = true;
            // 校验长度
            if (qq.length() >= 5 && qq.length() <= 15) {
                // 0不能开头
                if (!qq.startsWith("0")) {
                    // 必须是数字
                    char[] chs = qq.toCharArray();
                    for (int x = 0; x < chs.length; x++) {
                        char ch = chs[x];
                        if (!Character.isDigit(ch)) {
                            result = false;
                            break;
                        }
                    }
                } else {
                    result = false;
                }
            } else {
                result = false;
            }
            return result;
        }
    }
}
