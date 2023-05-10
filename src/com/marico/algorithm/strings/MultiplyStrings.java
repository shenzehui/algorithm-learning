package com.marico.algorithm.strings;

/**
 * 两数相乘
 * Created by Marico on 2022/11/17
 */

public class MultiplyStrings {

    // 方法一：基于字符串相加的竖式乘法
    public String multiply1(String num1, String num2) {
        // 处理特殊情况，有一个乘数为 0 ，结果就为 0
        if (num1.equals('0') || num2.equals('0')) {
            return "0";
        }
        // 定义输出结果，直接定义为 String，调用字符串相加方法
        String result = "0";

        // 从个位开始遍历 num2 的每一位，跟 num1 相乘，并叠加计算结果
        for (int i = num2.length() - 1; i >= 0; i--) {
            // 取出 num2 的当前数位，作为当前乘法的第二个乘数
            int n2 = num2.charAt(i) - '0';
            // 用 StringBuffer 保存乘积结果
            StringBuffer curResult = new StringBuffer();
            int carry = 0;  // 定义进位

            // 1. 因为结果是倒序，所以当前 n2 对应数位要补的0，应该先写入 curResult，补 n - 1- i个0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                curResult.append("0");
            }
            // 2. 从个位开始遍历 num1 中的每一位，与 n2 相乘，并叠加
            for (int j = num1.length() - 1; j >= 0; j--) {
                // 取出 num1 中的当前位数，作为当前乘法的第一个乘数
                int n1 = num1.charAt(j) - '0';
                // 计算当前数位的乘积结果
                int product = n1 * n2 + carry;

                curResult.append(product % 10);
                carry = product / 10;
            }
            // 3. 所有数位乘法计算完毕，如果有进位，需要将进位单独作为一位保存下来
            if (carry != 0) {
                curResult.append(carry);
            }

            // 现在就得到了 num1 和 当前位 n2 的最终乘积

            // 4. 将当前乘积叠加到 result 中
            result = AddStrings.addStrings(result, curResult.reverse().toString());

        }
        return result;
    }

    // 方法二：竖式乘法优化
    public String multiply2(String num1, String num2) {
        // 处理特殊情况，有一个乘数为 0 ，结果就为 0
        if (num1.equals('0') || num2.equals('0')) {
            return "0";
        }

        //  定义一个数组，保存计算结果的每一位
        int[] resultArray = new int[num1.length() + num2.length()];

        // 遍历 num1 和 num2 的每个数位，做乘积，然后找到对应数位，填入结果数组
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                // 计算乘积
                int product = n1 * n2;

                // 保存到结果数组
                int sum = product + resultArray[i + j + 1];
                resultArray[i + j + 1] = sum % 10; // 叠加结果的个位保存到 i+j+1 的位置
                resultArray[i + j] += sum / 10;   // 这里的十位可能会出现两位数，等下一轮循环过来的时候，会将这个十位看成个位，然后做 sum%10 操作，就将该位数处理成了一位数了。
            }
        }
        // 将结果数组转成 String 输出
        StringBuffer result = new StringBuffer();
        int start = resultArray[0] == 0 ? 1 : 0; // 如果最高位为0，从1开始遍历
        for (int i = start; i < resultArray.length; i++) {
            result.append(resultArray[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        MultiplyStrings multiplyStrings = new MultiplyStrings();
        String multiply = multiplyStrings.multiply2(num1, num2);
        System.out.println(multiply);
    }
}
