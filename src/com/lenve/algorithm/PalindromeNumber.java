package com.lenve.algorithm;

/**
 * Title: 回文数
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);

        StringBuffer reverseStr = new StringBuffer(s).reverse();

        if (s.equals(reverseStr.toString())) {
            return true;
        }

        return false;
    }

    public boolean isPalindrome1(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reverseNum = 0;
        while (x > reverseNum) {
            reverseNum = reverseNum * 10 + x % 10;
            x /= 10;
        }
        return x == reverseNum || x == reverseNum / 10;
    }


    public static void main(String[] args) {
        boolean res = new PalindromeNumber().isPalindrome(121);
        System.out.println(res);
    }

}
