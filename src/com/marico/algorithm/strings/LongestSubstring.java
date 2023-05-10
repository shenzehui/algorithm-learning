package com.marico.algorithm.strings;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 * Created by Marico on 2022/11/16
 */

public class LongestSubstring {

    // 方法一：暴力法
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] recordCount = new int[300];
            for (int j = i; j < s.length(); j++) {
                if (++recordCount[s.charAt(j)] > 1) {
                    break;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
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

    public static void main(String[] args) {
        LongestSubstring characters = new LongestSubstring();
        int pwwkew = characters.lengthOfLongestSubstring2("dvdf");
        System.out.println(pwwkew);
    }
}
