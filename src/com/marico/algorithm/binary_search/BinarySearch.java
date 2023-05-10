package com.marico.algorithm.binary_search;

/**
 * 二分查找
 * Created by Marico on 2022/11/16
 */

public class BinarySearch {
    public int binarySearch(int[] a, int key) {
        // 定义初始查找范围，双指针
        int low = 0;
        int high = a.length - 1;

        // 排除特殊情况
        if (key < a[low] || key > a[high]) {
            return -1;
        }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] < key) {
                low = mid + 1; //取有半部分
            } else if (a[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 递归实现二分查找，增加搜索的上下界作为参数
    public int binarySearch2(int[] a, int key, int fromIndex, int toIndex) {
        // 基本判断，当起始位置大于结束位置时，直接返回 -1；特殊情况超出最大小值，直接返回 -1
        if (key < a[fromIndex] || key > a[toIndex] || fromIndex > toIndex) {
            return -1;
        }
        // 计算中间位置
        int mid = (fromIndex + toIndex) / 2;

        // 判断中间位置元素和 key 元素的大小，更改搜索范围，递归调用
        if (a[mid] > key) {
            return binarySearch2(a, key, fromIndex, mid - 1);
        } else if (a[mid] < key) {
            return binarySearch2(a, key, mid + 1, toIndex);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 10, 11};
        int key = 7;
        BinarySearch binarySearch = new BinarySearch();
        int index = binarySearch.binarySearch(arr, key);
        int index2 = binarySearch.binarySearch2(arr, key, 0, arr.length - 1);

        System.out.println(index);
        System.out.println(index2);
    }
}
