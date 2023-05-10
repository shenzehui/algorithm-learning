package com.marico.algorithm.arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * Created by Marico on 2022/11/15
 */

public class TwoSum {
    // 方法一：暴力法：穷举所有两数组合
    public int[] twoSum1(int[] nums, int target) {
        // 双重 for 循环
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        // 如果找不到，抛出异常
        throw new IllegalArgumentException("no solution");
    }

    // 方法二：哈希表保存所有数的信息
    public int[] twoSum2(int[] nums, int target) {
        // 定义一个哈希表
        Map<Integer, Integer> map = new HashMap<>();
        // 1.遍历数组，将数据全部保存入 hash 表
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // 2.再次遍历数组，寻找每个数对应的那个数是否存在
        for (int i = 0; i < nums.length; i++) {
            int thatNum = target - nums[i];
            // 如果那个数存在，并且不是当前数自身，就直接返回结果
            if (map.containsKey(thatNum) && map.get(thatNum) != i) {
                return new int[]{i, map.get(thatNum)};
            }
        }
        // 如果找不到，抛出异常
        throw new IllegalArgumentException("no solution");
    }

    // 方法三：改进，遍历一次 hash 表
    public int[] twoSum3(int[] nums, int target) {
        // 定义一个哈希表
        Map<Integer, Integer> map = new HashMap<>();

        // 遍历数组，寻找每个数对应的那个数是否存在（只向前寻找）
        for (int i = 0; i < nums.length; i++) {
            int thatNum = target - nums[i];
            // 如果那个数存在，并且不是当前数自身，就直接返回结果
            if (map.containsKey(thatNum)) {
                return new int[]{map.get(thatNum), i};
            }
            map.put(nums[i], i);
        }
        // 如果找不到，抛出异常
        throw new IllegalArgumentException("no solution");
    }

    public static void main(String[] args) {
        int[] input = {2, 7, 11, 15};
        int[] input2 = {3, 3};
        int target = 18;

        // 定义一个数组，进行性能测试
        int[] input3 = new int[1000000];
        for (int i = 0; i < input3.length; i++) {
            input3[i] = input3[i] - i;
        }

        int target3 = 567890;

        // 为了计算系统运行时间，开始计算和计算完成分别计时
        long startTime = System.currentTimeMillis();

        TwoSum twoSum = new TwoSum();
        int[] output = twoSum.twoSum1(input3, target3);

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        for (int i = 0; i < output.length; i++) {
            System.out.print(output[i] + "\t");
        }
    }
}
