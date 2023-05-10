package com.marico.algorithm.hashmap;

import java.util.HashSet;

/**
 * 最长连续序列
 * Created by Marico on 2022/11/22
 */

public class LongestConsecutiveSequence {
    // 方法一：暴力法  O(n3)
    public int longestConsecutiveSequence1(int[] nums) {
        // 定于一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        // 遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 保存当前元素作为起始点
            int currNum = nums[i];
            // 保存当前连续序列长度
            int currLength = 1;

            // 寻找后续数组，组成连续序列
            while (contains(nums, currNum + 1)) {
                currLength++;
                currNum++;
            }

            // 判断当前连续序列长度是否为最大
            maxLength = Math.max(maxLength, currLength);
        }
        return maxLength;
    }

    // 定义一个方法，用于在数组中寻找某个元素
    public boolean contains(int[] nums, int x) {
        for (int num : nums) {
            if (num == x) {
                return true;
            }
        }
        return false;
    }

    // 方法二：利用哈希表改进 O(n2)  :省去了调用 contains 方法的时间复杂度
    public int longestConsecutiveSequence2(int[] nums) {
        // 定义一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        // 定义一个 HashSet，保存所有出现的数值
        HashSet<Integer> hashSet = new HashSet<>();

        // 1.遍历所有元素，保存到 HashSet
        for (int num : nums) {
            hashSet.add(num);
        }

        // 2.遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 保存当前元素作为起始点
            int currNum = nums[i];
            // 保存当前连续序列长度
            int currLength = 1;

            // 寻找后续数组，组成连续序列
            while (hashSet.contains(currNum + 1)) {
                currLength++;
                currNum++;
            }

            // 判断当前连续序列长度是否为最大
            maxLength = Math.max(maxLength, currLength);
        }
        return maxLength;
    }


    // 方法三：进一步改进 O(n)
    public int longestConsecutiveSequence3(int[] nums) {
        // 定义一个变量，保存当前最长连续序列的长度
        int maxLength = 0;

        // 定义一个 HashSet，保存所有出现的数值
        HashSet<Integer> hashSet = new HashSet<>();

        // 1.遍历所有元素，保存到 HashSet
        for (int num : nums) {
            hashSet.add(num);
        }

        // 2.遍历数组，以每个元素作为起始点，寻找连续序列
        for (int i = 0; i < nums.length; i++) {
            // 保存当前元素作为起始点
            int currNum = nums[i];
            // 保存当前连续序列长度
            int currLength = 1;

            // 判断：只有当前元素的前驱不存在的请求下，才去进行寻找连续序列操作
            if (!hashSet.contains(currNum - 1)) {
                // 寻找后续数组，组成连续序列
                while (hashSet.contains(currNum + 1)) {
                    currLength++;
                    currNum++;
                }

                // 判断当前连续序列长度是否为最大
                maxLength = Math.max(maxLength, currLength);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};

        int i = new LongestConsecutiveSequence().longestConsecutiveSequence3(nums1);
        int j = new LongestConsecutiveSequence().longestConsecutiveSequence3(nums2);

        System.out.println(i);
        System.out.println(j);
    }
}
