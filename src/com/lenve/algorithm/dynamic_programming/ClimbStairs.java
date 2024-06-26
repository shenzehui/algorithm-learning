package com.lenve.algorithm.dynamic_programming;

/**
 * 爬楼梯
 * Created by lenve on 2022/11/29
 */

public class ClimbStairs {

    // 方法一：动态规划，底层就是斐波那契数列
    public int climbStairs(int n) {
        // 定义两个临时变量，保存前两个状态
        int pre2 = 1, pre1 = 1; // fib(1) fib(2)

        int curr;
        for (int i = 1; i < n; i++) {
            // 定义一个临时变量，保存当前的状态
            curr = pre1 + pre2;
            pre2 = pre1;
            pre1 = curr;
        }
        return pre1;
    }

    // 方法二：数学公式方法
    public int climbStairs1(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib = (Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2, n + 1)) / sqrt_5;
        return ((int) fib);
    }

    public static void main(String[] args) {
        int result = new ClimbStairs().climbStairs1(4);
        System.out.println(result);
    }
}
