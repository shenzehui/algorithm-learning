package com.lenve.algorithm;

import java.util.Arrays;

/**
 * Title: 寻找两个正序数组的中位数
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

public class FindMedianSortedArrays {

    // 简单实现
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // 创建一个新数组
        int[] temp = new int[nums1.length + nums2.length];

        // 复制第一个数组
        System.arraycopy(nums1, 0, temp, 0, nums1.length);

        // 复制第二个数组
        for (int i = 0; i < nums2.length; i++) {
            temp[nums1.length + i] = nums2[i];
        }

        // 排序
        Arrays.sort(temp);

        // 中位数
        double num = temp[temp.length / 2];

//        if (temp.length % 2 == 0) {

        // 偶数    位运算：二进制中奇数最低位永远都是 1  而偶数永远都是 0
        if ((temp.length & 1) == 0) {
            num = (temp[temp.length / 2] + temp[temp.length / 2 - 1]) * 1.0 / 2;
        }
        return num;

    }


    // 归并排序
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int remNum1 = 0, remNum2 = 0;

        boolean flag = ((n + m) & 1) == 1;

        int point1 = 0, point2 = 0;


        for (int i = 0; i <= (n + m) / 2; i++) {
            remNum2 = remNum1;

            if (point1 < n && (point2 >= m || nums1[point1] < nums2[point2])) {
                remNum1 = nums1[point1++];
            } else {
                remNum1 = nums2[point2++];
            }
        }
        if (flag) {
            // 奇数
            return (remNum1 + remNum2) / 2.0;
        }
        return remNum1 * 1.0;
    }


}
