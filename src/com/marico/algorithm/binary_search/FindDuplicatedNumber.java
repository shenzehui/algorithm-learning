package com.marico.algorithm.binary_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 寻找重复数
 * Created by Marico on 2022/11/17
 */

public class FindDuplicatedNumber {
    // 方法一：使用 HashMap 保存每个数出现的次数
    public static int findDuplicate1(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        // 遍历所有元素，统计 count 值
        for (int num : nums) {
            // 判断当前 num 是否在 map 中出现过
            if (countMap.containsKey(num)) {
                return num; //  如果出现过,num 就是重复数
            } else {
                countMap.put(num, 1);
            }
        }
        return -1;
    }

    // 方法二：使用 HashSet 保存数据，判断是否出现过
    public static int findDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        // 遍历所有元素，添加到 set 中
        for (int num : nums) {
            // 判断当前 num 是否在 map 中出现过
            if (set.contains(num)) {
                return num; //  如果出现过,num 就是重复数
            } else {
                set.add(num);
            }
        }
        return -1;
    }

    // 方法三：先排序，然后找相邻的相同元素
    public static int findDuplicate3(int[] nums) {
        Arrays.sort(nums);  // 修改了 nums
        // 遍历数组元素，遇到跟前一个相同的，就是重复元素
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // 方法四：二分查找，查找 1~N 的自然数序列，寻找 target
    public static int findDuplicate4(int[] nums) {
        // 定义左右指针
        int left = 1;
        int right = nums.length - 1;

        while (left <= right) {
            // 计算中间值
            int mid = (left + right) / 2;

            // 对当前的 mid 计算 count 值
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= mid) {
                    count++;
                }
            }
            // 判断 count 和 mid 本身的关系
            if (count <= mid) {
                left = mid + 1; // count <= mid 自身，说明 mid 比 target 小，左指针右移
            } else {
                right = mid; // 注意这里是 mid，不能减一
            }
            // 左右指针重合时，找到 target
            if (left == right) {
                return left;
            }
        }
        return -1;
    }

    // 方法五：快慢指针
    public static int findDuplicate5(int[] nums) {
        // 定义快慢指针
        int fast = 0, slow = 0;
        // 1. 寻找环内的相遇点
        do {
            // 快指针一次走两步，慢指针一次走一步
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (fast != slow);

        // 循环结束，slow 和 fast 相等，都是相遇点
        // 2. 寻找环的入口点
        // 另外定义两个指针，固定间距
        int before = 0, after = slow;
        while (before != after) {
            before = nums[before];
            after = nums[after];
        }

        // 循环结束，相遇点就是环的入口点，也就是重复元素
        return before;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        int result = findDuplicate5(nums);
        System.out.println(result);
    }
}
