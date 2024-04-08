package com.lenve.algorithm.strings;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * Created by lenve on 2022/11/16
 */

public class LongestSubstring {

    // 方法一：暴力法
    public int lengthOfLongestSubstring(String s) {
        // 存储最长子串长度
        int maxLength = 0;
        // 外层循环，从字符串的第一个字符开始
        for (int i = 0; i < s.length(); i++) {
            // 这里也可以使用 boolean 数组
            int[] recordCount = new int[300];
            // 内存循环，从当前字符开始
            for (int j = i; j < s.length(); j++) {
                // 说明字符出现过，跳出内层循环
                if (++recordCount[s.charAt(j)] > 1) {
                    break;
                }
                // 更新字符串最大长度
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength; // 返回
    }

    // 方法二：双指针
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;

        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)));
            }
            maxLength = Math.max(maxLength, i - j + 1);
            map.put(s.charAt(i), i + 1);
        }
        return maxLength;
    }

    // 滑动窗口
    public int lengthOfLongestSubstring3(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            maxLength = Math.max(maxLength, i - j + 1);
            map.put(s.charAt(i), i);
            System.out.println(s.substring(j, i + 1));
        }
        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstring characters = new LongestSubstring();
        int pwwkew = characters.lengthOfLongestSubstring3("dvdf");
        System.out.println(pwwkew);
    }
}
