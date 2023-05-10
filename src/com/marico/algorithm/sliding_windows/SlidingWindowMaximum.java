package com.marico.algorithm.sliding_windows;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * Created by Marico on 2022/11/18
 */

public class SlidingWindowMaximum {
    // 方法一：暴力法，遍历每一个窗口，对每个窗口遍历每个元素求最大值
    public int[] maxSlidingWindow1(int[] nums, int k) {
        // 定义一个结果数组，总共有 n-k+1 个窗口
        int[] result = new int[nums.length - k + 1];
        // 遍历数组，从0到n-k，作为滑动窗口的起始位置
        for (int i = 0; i <= nums.length - k; i++) {
            // 找窗口内的最大值，定义一个变量保存
            int max = nums[i];
            // 遍历窗口中的每一个元素，比较大小
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }
        return result;
    }

    // 方法二：使用大顶堆
    public int[] maxSlidingWindow2(int[] nums, int k) {
        // 定义一个结果数组
        int[] result = new int[nums.length - k + 1];

        // 用优先队列实现一个大顶堆（默认是小顶堆）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // 准备工作，构建大顶堆，将第一个窗口元素（前 k 个）放入堆中
        for (int i = 0; i < k; i++) {
            maxHeap.add(nums[i]);
        }

        // 当前大顶堆的堆顶元素就是第一个窗口的最大值
        result[0] = maxHeap.peek();

        // 遍历所有窗口
        for (int i = 1; i < nums.length - k + 1; i++) {
            maxHeap.remove(nums[i - 1]); //删除堆中上一个窗口的元素
            maxHeap.add(nums[i - 1 + k]); //添加当前窗口的最后一个元素进堆
            result[i] = maxHeap.peek();
        }
        return result;
    }

    // 方法三：使用双向队列
    public int[] maxSlidingWindow3(int[] nums, int k) {
        // 定义一个结果数组
        int[] result = new int[nums.length - k + 1];

        // 定义双向队列，保存元素的索引
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 初始化双向队列，处理第一个窗口的数据
        for (int i = 0; i < k; i++) {
            // 如果队尾元素小于当前元素，直接删除
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {  // 只要比加入的小，就弹出
                deque.removeLast();
            }
            deque.addLast(i);
        }
        result[0] = nums[deque.getFirst()]; // 第一个窗口的最大值

        // 遍历数组所有元素，作为窗口的结束位置
        for (int i = k; i < nums.length; i++) {
            // 判断如果上一个窗口删掉的就是窗口最大值，那么需要将队列中的最大值删掉，注意，queue 中保存的是索引值
            if (!deque.isEmpty() && deque.getFirst() == i - k) {
                deque.removeFirst();
            }

            // 判断新增元素是否可以删除队尾元素
            // 如果队尾元素小于当前元素，直接删除
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            result[i - k + 1] = nums[deque.getFirst()];
        }
        return result;
    }

    // 方法四：左右扫描
    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length;
        // 定义一个结果数组
        int[] result = new int[n - k + 1];

        // 定义存放块内最大值的 left 和 right 的数组
        int[] left = new int[n];
        int[] right = new int[n];

        // 遍历数组，左右扫描
        for (int i = 0; i < n; i++) {
            // 1. 从左到右
            if (i % k == 0) {
                // 如果能整除 k，就是块的起始位置
                left[i] = nums[i];
            } else {
                // 如果不是起始位置，就直接跟上一个元素取最大值即可
                left[i] = Math.max(nums[i], left[i - 1]);
            }
            // 2. 从右到左
            int j = n - 1 - i; // j 就是倒数的 i;
            if (j % k == k - 1 || j == n - 1) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(nums[j], right[j + 1]);
            }
        }

        // 对每个窗口计算最大值
        for (int i = 0; i < n - k + 1; i++) {
            result[i] = Math.max(right[i], left[i + k - 1]);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = new SlidingWindowMaximum().maxSlidingWindow4(nums, k);
        for (int i : result) {
            System.out.print(i + "\t");
        }
    }
}
