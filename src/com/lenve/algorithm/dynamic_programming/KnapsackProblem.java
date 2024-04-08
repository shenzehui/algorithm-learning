package com.lenve.algorithm.dynamic_programming;

/**
 * 0-1 背包问题
 * Created by lenve on 2022/11/29
 */

// TODO 难点
public class KnapsackProblem {

    // 动态规划实现
    public int maxValue(int capacity, int[] weights, int[] values) {
        int n = weights.length;

        // 定义状态
        int[][] dp = new int[n + 1][capacity + 1];

        // 遍历所有的子问题，依次计算状态
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 判断当期的背包容量 j 是否能放下物品 i
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);  // 状态转移
                } else { // 放不下
                    dp[i][j] = dp[i - 1][j];  // 状态转移
                }
            }
        }
        return dp[n][capacity];
    }

    // 动态规划空间优化  滚动数组
    public int maxValue2(int capacity, int[] weights, int[] values) {
        int n = weights.length;

        // 定义状态，只保存一行的数据
        int[] dp = new int[capacity + 1];

        // 遍历所有的子问题，依次计算状态
        for (int i = 1; i <= n; i++) {
            for (int j = capacity; j > 0; j--) {
                // 判断当期的背包容量 j 是否能放下物品 i
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[capacity];
    }

    public static void main(String[] args) {
        int W = 150;
        int[] w = {35, 30, 60, 50, 40, 10, 25};
        int[] w2 = {25, 20, 10};
        int[] v = {10, 40, 30, 50, 35, 40, 30};
        int[] v2 = {28, 10, 20};
        int result = new KnapsackProblem().maxValue2(W, w, v);
        int result2 = new KnapsackProblem().maxValue2(30, w2, v2);
        System.out.println(result);
        System.out.println(result2);
    }
}
