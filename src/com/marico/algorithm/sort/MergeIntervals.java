package com.marico.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Marico on 2022/11/26
 */

public class MergeIntervals {

    // 按区间左边界排序
    public int[][] merge(int[][] intervals) {
        // 定义一个结果数组
        ArrayList<int[]> result = new ArrayList<>();

        // 1. 将所有区间按照左边界排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 2. 遍历排序后的区间，逐个合并
        for (int[] interval : intervals) {
            // 记录当期的左右边界
            int left = interval[0], right = interval[1];

            // 获取结果数组长度
            int length = result.size();

            // 如果 left 比结果数组中最后一个数组的右边界大，不能合并，直接添加到结果数组
            if (length == 0 || left > result.get(length - 1)[1]) {
                result.add(interval);
            } else {
                // 可以合并
                int mergeLeft = result.get(length - 1)[0];
                int mergeRight = Math.max(result.get(length - 1)[1], right); // 取一个最大值作为右边界
                result.set(length - 1, new int[]{mergeLeft, mergeRight}); // 覆盖原结果数组
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = new MergeIntervals().merge(intervals);
        for (int[] ints : result) {
            QuickSort.printArray(ints);
        }
    }
}
