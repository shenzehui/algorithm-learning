package com.marico.algorithm.sort;

import java.util.Arrays;

/**
 * 颜色分类
 * Created by Marico on 2022/11/26
 */

public class SortColors {

    // 方法一：调库  O(nlog2n)
    public void sortColors1(int[] nums) {
        Arrays.sort(nums);
    }

    // 方法二：基于选择排序  O(n)
    public void sortColors2(int[] nums) {
        // 定义一个指针，指向当前应该填入元素的位置
        int curr = 0;

        // 1. 遍历数组，将所有 0 交换到数组头部
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                QuickSort.swap(nums, curr++, i);
            }
        }

        // 2. 遍历数组，将所有 1 交换到中间位置，接着之前的 curr 继续
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                QuickSort.swap(nums, curr++, i);
            }
        }
    }

    // 方法三：TODO: 新思路：基于计数排序  O(n)
    public void sortColors3(int[] nums) {
        int count0 = 0;  // 0 的个数
        int count1 = 0;  // 1 的个数

        // 遍历数组，统计 0,1,2 的个数
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            }
        }

        // 将 0,1,2 按照个数依次填入 nums 数组
        for (int i = 0; i < nums.length; i++) {
            if (i < count0) {
                nums[i] = 0;
            } else if (i < count0 + count1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    // 方法四：基于快速排序(一次遍历) O(n)
    public void sortColors4(int[] nums) {
        // 定义左右指针
        int left = 0, right = nums.length - 1;
        // 定义一个遍历所有元素的指针
        int i = left;

        // 循环判断，遍历元素
        while (left < right && i <= right) {
            // 1. 如果是2，换到末尾，右指针左移
            while (i <= right && nums[i] == 2) { // 若换的元素相等，会一直交换，知道 num[i] 被交换的数是 0 或者 1
                QuickSort.swap(nums, i, right);
                right--;
            }
            // 2. 判断，如果是9，换到头部，左指针右移
            if (nums[i] == 0) {
                QuickSort.swap(nums, i, left++);
            }
            // 3. i++,继续遍历
            i++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors3(nums);
        QuickSort.printArray(nums);
    }
}
