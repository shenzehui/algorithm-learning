package com.lenve.algorithm.dynamic_programming;

/**
 * 斐波那契数列
 * Created by lenve on 2022/11/29
 */

public class Fibonacci {
    // 方法一：利用数学递推式，直接递归
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        // 递归调用
        return fib(n - 2) + fib(n - 1);
    }

    // 方法二：动态规划
    public int fib2(int n) {
        // 返回基准情况
        if (n == 1 || n == 2) {
            return 1;
        }
        // 定义一个状态数组，保存就是 fib2(n) 的计算结果
        int[] dp = new int[n];
        dp[0] = dp[1] = 1; // fib(1) 和 fib(2)

        // 状态转移递推
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n - 1];
    }

    // 方法三：动态规划空间优化
    public int fib3(int n) {
        // 返回基准情况
        if (n == 1 || n == 2) {
            return 1;
        }
        // 定义两个临时变量，保存前两个状态
        int pre2 = 1, pre1 = 1; // fib(1) fib(2)

        for (int i = 2; i < n; i++) {
            // 定义一个临时变量，保存当前的状态
            int curr = pre1 + pre2;
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }

    public static void main(String[] args) {
        int result = new Fibonacci().fib3(9);
        System.out.println(result);
    }
}
