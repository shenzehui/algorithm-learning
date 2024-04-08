package com.lenve.algorithm.arrays;

import java.util.Arrays;

/**
 * 下一个排列
 * Created by lenve on 2022/11/16
 */

public class NextPermutation {

    /**
     * 思路：从后向前找到升序子序列，然后确定调整后子序列的最高位，剩余部分升序排列
     */
    public void nextPermutation1(int[] nums) {
        int n = nums.length;

        // 1.从后向前找到升序子序列，找到一次下降的数，位置记为 k
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }

        // 找到 k,就是需要调整位置的最高位

        // 2.如果 k = -1，说明所有数降序排列，改成升序排列
        if (k == -1) {
            Arrays.sort(nums);
            return;
        }

        // 3.一般情况，k >=0
        // 3.1 依次遍历剩余降序排列部分，找到要替换最高位的那个数
        int i = k + 2;  // k+1 肯定比它大
        while (i < n && nums[i] > nums[k]) {
            i++;
        }
        // 当前的 i 就是后面部分第一个比 nums[k] 小的数，num[i-1] 就是比当前数大的最小的数，就是要替换的数

        // 3.2 交换 i -1 和 k 位置上的数
        int temp = nums[k];
        nums[k] = nums[i - 1];
        nums[i - 1] = temp;

        // 3.3 k 之后的剩余部分变成升序排列，直接前后替换
        int start = k + 1;
        int end = n - 1;
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }

    /**
     * 方法改进：将降序数组反转的操作提取出来
     */
    public void nextPermutation2(int[] nums) {
        int n = nums.length;

        // 1.从后向前找到升序子序列，找到一次下降的数，位置记为 k
        int k = n - 2;
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }

        // 找到 k,就是需要调整位置的最高位

        // 2.如果 k = -1，说明所有数降序排列，改成升序排列
        if (k == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        // 3. 一般情况，k >=0
        // 3.1 依次遍历剩余降序排列部分，找到要替换最高位的那个数
        int i = k + 2;  // k+1肯定比它大
        while (i < n && nums[i] > nums[k]) {
            i++;
        }
        // 当前的 i 就是后面部分第一个比 nums[k] 小的数，num[i-1] 就是比当前数大的最小的数，就是要替换的数

        // 3.2 交换 i -1 和 k 位置上的数
        sway(nums, k, i - 1);

        // 3.3 k 之后的剩余部分变成升序排列，直接前后替换
        reverse(nums, k + 1, n - 1);
    }

    /**
     * 定义一个方法，交换数组中两个元素
     */
    private void sway(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 定义一个反转数组的方法
     */
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            sway(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        NextPermutation permutation = new NextPermutation();
        permutation.nextPermutation2(nums);

        for (int num : nums) {
            System.out.print(num + "\t");
        }
    }
}
