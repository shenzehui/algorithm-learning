package com.lenve.algorithm;

/**
 * Title: 字符串转换整数 (atoi)
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class StringToIntegerAtoi {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int sign = 1;
        int index = 0;

        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }

        // 存在符号处理
        if (s.charAt(0) == '-' || s.charAt(0) == '+') {
            sign = s.charAt(0) == '-' ? -1 : 1;
            index++;
        }

        // 提取数字
        StringBuilder sb = new StringBuilder();

        while (s.length() > index && Character.isDigit(s.charAt(index))) {
            sb.append(s.charAt(index));
            index++;
        }

        if(sb.length() == 0){
            return  0;
        }

        // 尝试转换
        int result = 0;
        try {
            result = Integer.parseInt(sb.toString());
        } catch (NumberFormatException e) {
            return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        int result = new StringToIntegerAtoi().myAtoi("words and 987");
        System.out.println(result);
    }


}
