package com.marico.algorithm.dynamic_programming;

/**
 * 买买股票的最佳时机
 * Created by Marico on 2022/11/29
 */

public class BestTimeToBuyAndSellStock {

    // 方法一：暴力法
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        // 遍历所有可能的买入卖出情况
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i; j < prices.length; j++) {
                int currProfit = prices[j] - prices[i];
                maxProfit = Math.max(maxProfit, currProfit);
            }
        }
        return maxProfit;
    }

    // 方法二：动态规划
    public int maxProfit2(int[] prices) {
        // 定义状态：保存到目前为止的最小价格，和最大利润
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        // 遍历数组元素，以当前价格作为卖出点进行比较
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};
        int i = new BestTimeToBuyAndSellStock().maxProfit2(prices);
        int j = new BestTimeToBuyAndSellStock().maxProfit2(prices2);
        System.out.println(i);
        System.out.println(j);
    }
}
