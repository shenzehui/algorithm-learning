package com.marico.algorithm.binary_search;

/**
 * 搜索二维矩阵
 * Created by Marico on 2022/11/17
 */

public class SearchMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;
        int[] newArray = new int[m * n];
        for (int i = 0; i < m; i++) {
            n = matrix[i].length;
            for (int j = 0; j < n; j++) {
                newArray[i * n + j] = matrix[i][j];
            }
        }
        int i = binarySearch(newArray, target, 0, n * m - 1);
        if (i == -1) {
            return false;
        }
        return true;
    }

    public static int binarySearch(int[] array, int target, int start, int end) {
        if (array[start] > target || array[end] < target || start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] > target) {
            return binarySearch(array, target, start, mid - 1);
        } else if (array[mid] < target) {
            return binarySearch(array, target, mid + 1, end);
        } else {
            return mid;
        }
    }

    public static boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        // 二分查找，定义左右指针
        int left = 0;
        int right = m * n - 1;
        while (left <= right) {
            // 计算中间位置
            int mid = (left + right) / 2;
            // 计算二维矩阵中对应的行列号，取出对应元素
            int midElement = matrix[mid / n][mid % n];

            //判断中间元素与 target 的关系
            if (midElement < target) {
                left = mid + 1;
            } else if (midElement > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 10;
        boolean b = searchMatrix1(matrix, target);
        System.out.println(b);
    }
}
