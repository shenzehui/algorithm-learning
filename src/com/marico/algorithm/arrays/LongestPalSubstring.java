package com.marico.algorithm.arrays;

/**
 * 最长回文子串
 * Created by Marico on 2022/11/20
 */

public class LongestPalSubstring {
    // 暴力法  时间超时
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String str = s.substring(i, j + 1);
                StringBuffer reverse = new StringBuffer(str).reverse();
                if (str.equals(reverse.toString()) && str.length() > res.length()) {
                    res = str;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "ababbb";
        String s1 = new LongestPalSubstring().longestPalindrome(s);
        System.out.println(s1);
    }

}
