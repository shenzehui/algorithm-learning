package com.lenve.algorithm;

/**
 * Title:整数反转
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class ReverseInteger {
    public int reverse(int x) {
        String res = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            int n = Integer.parseInt(res);
            return (x < 0) ? n * -1 : n;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
