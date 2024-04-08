package com.lenve.algorithm.binary_tree;

/**
 * n的阶乘
 * Created by lenve on 2022/11/27
 */

public class Recursion {
    // 定义一个计算阶乘的方法  存在递归调用栈问题，栈内存容易溢出
    public static int factorial(int n) {
        if (n == 0) { // 基准情形
            return 1;
        }
        return factorial(n - 1) * n;
    }

    //  解决：TODO 尾递归实现
    public static int factorial2(int n, int acc) {
        if (n == 0) {
            return acc;
        }
        return factorial2(n - 1, acc * n);  // 将当前计算结果作为参数继续递归调用
    }

    public static void main(String[] args) {
        int result = factorial(6);
        int result1 = factorial2(6,1);
        System.out.println(result);
        System.out.println(result1);
    }
}
