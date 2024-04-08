package com.lenve.algorithm.arrays;

import com.lenve.algorithm.strings.LongestSubstring;
import sun.security.util.Length;

import java.util.function.LongFunction;

/**
 * 最长回文子串
 * Created by lenve on 2022/11/20
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

    // 递归求斐波那契数列
    public int fib(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 动态规范求斐波那契数列
    public int fib2(int n) {
        int[] f = new int[n + 1];
        f[1] = f[2] = 1;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }


    // 动态规划
    public String longestPalindrome2(String s) {
        StringBuffer rev = new StringBuffer(s).reverse();
        int n = s.length();

        int maxLength = 1;
        int beginPost = 0;

        int[][] array = new int[n][n];

        // 初始化列
        for (int j = 0; j < n; j++) {
            if (rev.charAt(j) == s.charAt(0)) {
                array[0][j] = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            // 初始化行
            if (rev.charAt(0) == s.charAt(i)) {
                array[i][0] = 1;
            }
            for (int j = 1; j < n; j++) {
                if (s.charAt(i) == rev.charAt(j)) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                }
                if (array[i][j] > maxLength) {
                    int begPost = n - j - 1;

//                    if (beginPost + array[i][j] - 1 == i) {
                    beginPost = begPost;
                    maxLength = array[i][j];
//                    }
                }
            }
        }
        return s.substring(beginPost, beginPost + maxLength);
    }

    // 动态规划解法二
    public String longestPalindrome3(String s) {
        int n = s.length();
        boolean[][] array = new boolean[n][n];

        int maxLength = 1;
        String res = "";

        for (int len = 0; len < n; len++) {
            for (int start = 0; start + len < n; start++) {
                int end = start + len;

                if (len == 0) {
                    array[start][end] = true;
                } else if (len == 1) {
                    array[start][end] = s.charAt(start) == s.charAt(end);
                } else {
                    array[start][end] = (s.charAt(start) == s.charAt(end)) && array[start + 1][end - 1];
                }
                if (array[start][end] && len + 1 > maxLength) {
                    maxLength = len + 1;
                    res = s.substring(start, end + 1);
                }
            }
        }
        return res;
    }


    /**
     * 中心拓展法   最简单
     */
    public String longestPalindrome4(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // 返回回文长度
    }

    /**
     * 马拉车算法
     * <a href="https://www.cxyxiaowu.com/2665.html">马拉车算法</a>
     */
    public String longestPalindrome5(String s) {


        return null;

    }


    public static void main(String[] args) {
        String s = "babad";
        String s1 = new LongestPalSubstring().longestPalindrome4(s);
        System.out.println(s1);
        // 1 1 2 3 5 8 13 21 34 55
//        int res = new LongestPalSubstring().fib2(10);
//        System.out.println(res);
    }

}
