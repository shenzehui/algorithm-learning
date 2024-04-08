package com.lenve.algorithm.strings;

import java.util.HashMap;
import java.util.Stack;

/**
 * 去除重复字母
 * Created by lenve on 2022/11/18
 */

public class RemoveDuplicateLetters {
    // 方法一：暴力法，贪心策略递归
    public String removeDuplicateLetters1(String s) {
        // 递归的基准情形
        if (s.length() == 0) {
            return "";
        }

        // 希望找到当前最左侧的字母，位置记为 position
        int position = 0;
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 要求只有当前字母比已经找到的 position 位置的字母要小，才有资格继续判断
            if (s.charAt(i) < s.charAt(position)) {
                // 定义一个 boolean 变量，表示当前 i 位置的字母是否可以替换 position 位置的字母
                boolean isReplaceable = true;

                // 遍历 i 之前的所有字母，判断是否在 i 后面重复出现
                for (int j = position; j < i; j++) {
                    // 定义一个布尔类型变量，表示 j 位置的字母是否重复出现
                    boolean isDuplicated = false;
                    // 遍历 i 后面的字母，看 j 位置的字母是否重复出现
                    for (int k = i + 1; k < s.length(); k++) {
                        if (s.charAt(k) == s.charAt(j)) {
                            isDuplicated = true;
                            break;
                        }
                    }
                    // 如果任意字母不重复出现，就不能替换当前 position，后面的字母不用判断了
                    if (!isDuplicated) {
                        isReplaceable = false;
                        break;
                    }
                }
                if (isReplaceable) {
                    position = i;
                }
            }
        }
        // 遍历结束，position 位置的字母就是结果中最左侧的元素
        return s.charAt(position) + removeDuplicateLetters1(s.substring(position + 1).replaceAll(s.charAt(position) + "", ""));
    }

    // 方法二：贪心策略改进
    public String removeDuplicateLetters2(String s) {
        // 递归的基准情形
        if (s.length() == 0) {
            return "";
        }

        // 希望找到当前最左侧的字母，位置记为 position
        int position = 0;

        // 定义一个 count 数组，保存所有 26 个字母在字符串中出现的频次
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++; // count[0] 保存 a 的个数，count[b] 保存 b 的个数
        }

        // 遍历字符串，找到当前最左边字母
        for (int i = 0; i < s.length(); i++) {

            // 把当前字符和 position 位置比较，如果更小就替换
            if (s.charAt(i) < s.charAt(position)) {
                position = i;
            }

            // 每遇到一个字符，count 值就要减一
            count[s.charAt(i) - 'a']--;
            // 如果当前遇到 count 减为0，就直接退出，以当前 position 作为最左端字母
            if (count[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        // 递归调用
        return s.charAt(position) + removeDuplicateLetters2(s.substring(position + 1).replaceAll(s.charAt(position) + "", ""));
    }


    // 方法三：使用栈进行优化
    public String removeDuplicateLetters3(String s) {
        // 定义一个字符栈，保存去重之后的结果
        Stack<Character> stack = new Stack<>();

        // 为了快速判断一个字符是否在栈中出现过，我们用 Set 保存元素是否出现
//        HashSet<Character> seenSet = new HashSet<>();

        // 为了快速判断一个字符是否在某个位置之后重复出现，用一个 HashMap 来保存字母出现在字符串的最后位置
        HashMap<Character, Integer> lastOccur = new HashMap<>();

        // 遍历字符串，将最后一次出现的位置存入 map   
        for (int i = 0; i < s.length(); i++) {
            lastOccur.put(s.charAt(i), i);
        }

        // 遍历字符串，判断每个字符是否需要入栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 只有在 c 没有出现过的情况下，才判断是否入栈
            if (!stack.contains(c)) {
                // c入栈之前，要判断之前栈顶元素，是否在后面会重复出现，如果重复出现就可以删除
                while (!stack.isEmpty() && c < stack.peek() && lastOccur.get(stack.peek()) > i) {
                    stack.pop();  // 弹出栈顶元素，因为该元素在后面会出现
//                    seenSet.remove(stack.peek());  // 删除 seenSet 中的元素
                }
                stack.push(c);
//                seenSet.add(c);
            }
        }
        // 将栈中的元素保存成字符串输出
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character : stack) {
            stringBuilder.append(character.charValue());
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        String str1 = "bcabc";
        String str2 = "cbacdcbc";
        String s1 = new RemoveDuplicateLetters().removeDuplicateLetters2(str1);
        String s2 = new RemoveDuplicateLetters().removeDuplicateLetters2(str2);
        System.out.println(s1);
        System.out.println(s2);
    }
}
