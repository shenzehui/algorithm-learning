package com.marico.algorithm.strings;

/**
 * 字符串相加
 * Created by Marico on 2022/11/17
 */

public class AddStrings {
    public static String addStrings(String num1, String num2) {
        // 定义一个 StringBuffer，保存最终的结果
        StringBuffer result = new StringBuffer();

        //  定义遍历两个字符串的初始位置
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0; // 用一个变量保存进位

        // 从各位开始依次遍历所有数的数位，只要还有数没有计算，就继续；其他数位补0
        while (i >= 0 || j >= 0 || carry != 0) {
            // 取两数当前的对应数位
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0; // 字符要将 ascii 码转换为数字
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            // 对当前数位求和
            int sum = n1 + n2 + carry;

            // 把 sum 的个位保存到结果中，十位作为进位保存下来;
            result.append(sum % 10);
            carry = sum / 10;

            // 继续移动指针，继续遍历下一位
            i--;
            j--;
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "1234";
        String s = addStrings(num1, num2);
        System.out.println(s);

//        char c = '5';
//        int i = c - '0';
//        System.out.println(c);
//        System.out.println(i);
    }
}
