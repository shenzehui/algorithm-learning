package com.marico.algorithm.sliding_windows;

import java.util.HashMap;

/**
 * 最小覆盖子串
 * Created by Marico on 2022/11/19
 */

public class MinWindowSubString {

    // 方法一：暴力法，枚举 s 中所有子串  时间复杂度O(n3)
    public String minWindow1(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个 HashMap，保存 t 中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计 t 中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0); // 获取字符 c 的出现次数（没有则为0）
            tCharFrequency.put(c, ++count);
        }

        // 接下来在 s 中搜索覆盖子串
        // 遍历所有字符，作为当前子串的起始位置
        for (int i = 0; i <= s.length(); i++) {
            // 遍历 i 之后不小于 t 长度的位置，作为子串的结束位置
            for (int j = i + t.length(); j <= s.length(); j++) { //不包含
                // 统计 s 子串中每个字符出现的频次
                // 定义一个 HashMap，保存 s 子串中字符出现的频次
                HashMap<Character, Integer> subStringCharFrequency = new HashMap<>();

                // 统计子串中字符频次
                for (int k = i; k < j; k++) {
                    char c = s.charAt(k);
                    int count = subStringCharFrequency.getOrDefault(c, 0); // 获取字符 c 的出现次数（没有则为0）
                    subStringCharFrequency.put(c, ++count);
                }

                // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
                if (check(tCharFrequency, subStringCharFrequency) && (minSubString.equals("") || j - i < minSubString.length())) {
                    minSubString = s.substring(i, j);
                }
            }
        }
        return minSubString;
    }

    // 方法二：滑动窗口  时间复杂度O(n2)
    public String minWindow2(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个 HashMap，保存 t 中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计 t 中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0); // 获取字符 c 的出现次数（没有则为0）
            tCharFrequency.put(c, ++count);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = t.length();
        while (end <= s.length()) {
            // 统计 s 子串中每个字符出现的频次
            // 定义一个 HashMap，保存 s 子串中字符出现的频次
            HashMap<Character, Integer> subStringCharFrequency = new HashMap<>();

            // 统计子串中字符频次
            for (int k = start; k < end; k++) {
                char c = s.charAt(k);
                int count = subStringCharFrequency.getOrDefault(c, 0); // 获取字符 c 的出现次数（没有则为0）
                subStringCharFrequency.put(c, ++count);
            }
            // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
            if (check(tCharFrequency, subStringCharFrequency)) {
                if (minSubString.equals("") || start - end < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }
                // 只要是覆盖子串，就移动初始位置，缩小窗口，寻找当前的局部最优解
                start++;
            } else {
                // 如果不是覆盖子串，需要扩大窗口，继续寻找新的子串
                end++;
            }
        }
        return minSubString;
    }

    // 方法三：滑动窗口优化   优化了统计子串中字符频次  时间复杂度 O(n)
    public String minWindow3(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个 HashMap，保存 t 中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计 t 中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0); // 获取字符 c 的出现次数（没有则为0）
            tCharFrequency.put(c, ++count);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = 1;

        // 定义一个 HashMap，保存 s 子串中字符出现的频次
        HashMap<Character, Integer> subStringCharFrequency = new HashMap<>();

        while (end <= s.length()) {
            // end 增加之后，新增的字符
            char newChar = s.charAt(end - 1);

            // 新增字符频次+1
            if (tCharFrequency.containsKey(newChar)) {
                subStringCharFrequency.put(newChar, subStringCharFrequency.getOrDefault(newChar, 0) + 1);
            }

            // 如果当前子串符合覆盖子串的要求，并且比之前的最小子串要短，就替换
            while (check(tCharFrequency, subStringCharFrequency) && start < end) {
                if (minSubString.equals("") || start - end < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }

                char removeChar = s.charAt(start);

                // 对要删除的字符频次减一
                if (tCharFrequency.containsKey(removeChar)) {
                    subStringCharFrequency.put(removeChar, subStringCharFrequency.get(removeChar) - 1);
                }
                // 只要是覆盖子串，就移动初始位置，缩小窗口，寻找当前的局部最优解
                start++;
            }
            // 外层 while 循环 end++
            end++;
        }
        return minSubString;
    }

    // 方法四：进一步优化
    public String minWindow4(String s, String t) {
        // 定义最小子串，保存结果，初始为空字符串
        String minSubString = "";

        // 定义一个 HashMap，保存 t 中字符出现的频次
        HashMap<Character, Integer> tCharFrequency = new HashMap<>();

        // 统计 t 中字符频次
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            int count = tCharFrequency.getOrDefault(c, 0); // 获取字符 c 的出现次数（没有则为0）
            tCharFrequency.put(c, ++count);
        }

        // 定义左右指针，指向滑动窗口的起始和结束位置
        int start = 0, end = 1;

        // 定义一个 HashMap，保存 s 子串中字符出现的频次
        HashMap<Character, Integer> subStringCharFrequency = new HashMap<>();

        // 定义一个"子串贡献值"变量，统计 t 中的字符在子串中出现了多少
        int charCount = 0;

        while (end <= s.length()) {
            // end 增加之后，新增的字符
            char newChar = s.charAt(end - 1);

            // 新增字符频次+1
            if (tCharFrequency.containsKey(newChar)) {
                subStringCharFrequency.put(newChar, subStringCharFrequency.getOrDefault(newChar, 0) + 1);
                // 如果子串中频次小于 t 中频次，当前字符就有贡献
                if (subStringCharFrequency.get(newChar) <= tCharFrequency.get(newChar)) {
                    charCount++;  // 贡献值加一
                }
            }

            // 如果当前子串符合覆盖子串的要求（贡献值等于 t 的长度）
            while (charCount == t.length() && start < end) {
                // 记录子串
                if (minSubString.equals("") || start - end < minSubString.length()) {
                    minSubString = s.substring(start, end);
                }

                // 对要删除的字符，频次减一
                char removeChar = s.charAt(start);

                // 对要删除的字符频次减一
                if (tCharFrequency.containsKey(removeChar)) {
                    subStringCharFrequency.put(removeChar, subStringCharFrequency.get(removeChar) - 1);
                    // 如果子串中的频次如果不够t中的频次，贡献值减少
                    if (subStringCharFrequency.get(removeChar) < tCharFrequency.get(removeChar)) {
                        charCount--;
                    }
                }
                // 只要是覆盖子串，就移动初始位置，缩小窗口，寻找当前的局部最优解
                start++;
            }
            // 不满足覆盖子串的要求，外层 while 循环 end++
            end++;
        }
        return minSubString;
    }

    // 提炼一个方法，用于检查当前子串是否是一个覆盖 t 的子串
    public boolean check(HashMap<Character, Integer> tFreq, HashMap<Character, Integer> subStringFreq) {
        // 遍历 t 中每个字符的频次，与 subString 进行比较
        for (Character c : tFreq.keySet()) {
            if (subStringFreq.getOrDefault(c, 0) < tFreq.get(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String result = new MinWindowSubString().minWindow4(s, t);
        System.out.println(result);
    }
}
