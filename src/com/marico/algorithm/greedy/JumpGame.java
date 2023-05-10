package com.marico.algorithm.greedy;

/**
 * 跳跃游戏
 * Created by Marico on 2022/11/28
 */

public class JumpGame {
    // 贪心实现
    public boolean canJump(int[] nums) {
        // 定义一个变量，保存当前最远能跳到的位置
        int farthest = 0;
        // 遍历数组，更新 farthest
        for (int i = 0; i < nums.length; i++) {
            // 判断当前 i 在可以到达的范围内，更新 farthest
            if (i <= farthest) {
                farthest = Math.max(farthest, i + nums[i]);
                // 如果 farthest 已经达到了末尾，直接返回 true
                if (farthest >= nums.length - 1) {
                    return true;
                }
            } else {
                // 如果 farthest 已经到达不了 i 的位置
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};

        System.out.println(new JumpGame().canJump(nums1));
        System.out.println(new JumpGame().canJump(nums2));
    }
}
