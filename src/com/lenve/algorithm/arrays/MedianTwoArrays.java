package com.lenve.algorithm.arrays;

import java.util.Arrays;

/**
 * Created by lenve on 2022/11/20
 */

public class MedianTwoArrays {
    public Double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[m + n];
        System.arraycopy(nums1, 0, result, 0, nums1.length);
        for (int i = 0; i < nums2.length; i++) {
            result[m + i] = nums2[i];
        }
        Arrays.sort(result);
        int length = result.length;
        if (length % 2 == 0) {
            return (result[length / 2] + result[length / 2 - 1]) / 2.0;
        }
        return result[length / 2] * 1.0;
    }

    // TODO 另寻解法

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        Double result = new MedianTwoArrays().findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
}
