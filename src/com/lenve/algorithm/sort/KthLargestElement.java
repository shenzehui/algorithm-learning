package com.lenve.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 数组中的第K个最大元素
 * Created by lenve on 2022/11/26
 */

public class KthLargestElement {

    // 方法一：直接排序
    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    // 方法二：基于快排的选择
    public int findKthLargest2(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);

    }

    // TODO 重点：实现快速选择方法
    private int quickSelect(int[] nums, int start, int end, int index) {
        // 找到 pivot 的位置返回
        int position = randomPartition(nums, start, end);

        // 判断当前 pivot 位置是否为 index
        if (position == index) {
            return nums[position];
        } else {
            // 递归调用
            return position > index ? quickSelect(nums, start, position - 1, index) : quickSelect(nums, position + 1, end, index);
        }
    }

    // 实现一个随机分区方法
    private int randomPartition(int[] nums, int start, int end) {
        Random random = new Random();
        // Random 随机函数：左闭右开
        int randIndex = start + random.nextInt(end - start + 1);  // 随机生成 pivot 的位置
        int pivot = nums[randIndex];
        int left = randIndex;
        int right = end;
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }

    // 方法二：TODO 新思路：基于堆排序的选择
    public int findKthLargest3(int[] nums, int k) {
        int n = nums.length;
        // 保存堆的大小，初始就是 n
        int heapSize = n;

        // 1. 构建大顶堆
        buildMaxHeap(nums, heapSize);

        // 2. 执行 n-1 次删除堆顶元素操作
        for (int i = n - 1; i > n - k; i--) {
            // 将堆顶元素交换到当前堆的末尾
            QuickSort.swap(nums, 0, i);
            heapSize--;
            // 交换元素之后，调整回大顶堆状态，传参：根节点索引和堆的大小
            maxHeapify(nums, 0, heapSize);
        }

        // 3. 返回当前堆顶元素
        return nums[0];
    }

    // 实现一个构建大顶堆的方法
    private void buildMaxHeap(int[] nums, int heapSize) {
        // 自底向上构建大顶堆
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, i, heapSize);
        }
    }

    // 定义一个实现大顶堆的方法
    private void maxHeapify(int[] nums, int top, int heapSize) {
        // 定义左右子节点
        int left = 2 * top + 1;
        int right = 2 * top + 2;

        // 保存当前最大位置的索引位置
        int largest = top;

        // 比较左右子节点，记录最大索引位置
        if (left < heapSize && nums[left] > nums[top]) {
            largest = left;
        }
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }
        // 将最大元素换到堆顶
        if (largest != top) {
            QuickSort.swap(nums, top, largest);
            // 递归调用，继续下沉
            maxHeapify(nums, largest, heapSize);
        }

    }


    public static void main(String[] args) {
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        KthLargestElement kthLargestElement = new KthLargestElement();
        int i = kthLargestElement.findKthLargest3(nums1, 2);
        int i2 = kthLargestElement.findKthLargest3(nums2, 4);
        System.out.println(i);
        System.out.println(i2);
    }
}
