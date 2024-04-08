package com.lenve.algorithm.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 只出现一次的数字
 * Created by lenve on 2022/11/22
 */

public class SingleNumber {
    // 方法一：暴力法
    public int singleNumber1(int[] nums) {
        // 定义一个列表，保存当前所有出现过一次的元素
        ArrayList<Integer> singleNumList = new ArrayList<>();

        // 遍历所有元素
        for (Integer num : nums) {
            if (singleNumList.contains(num)) {
                // 如果已经出现过，删除当前列表元素
                singleNumList.remove(num);
            } else {
                // 没有出现过，直接保存
                singleNumList.add(num);
            }
        }
        return singleNumList.get(0);
    }

    // 方法二：保存单独的元素到 HashMap  （O(n),O(n)）
    public int singleNumber2(int[] nums) {
        HashMap<Integer, Integer> singleNumMap = new HashMap<>();
        for (int num : nums) {
            if (singleNumMap.get(num) != null) {
                singleNumMap.remove(num);
            } else {
                singleNumMap.put(num, 1);
            }
        }
        return singleNumMap.keySet().iterator().next(); // 获取 HashMap 中的第一个元素
    }

    // 方法三：用 set 去重，a = 2 * (a+b+c) - (a+b+c+b+c)
    public int singleNumber3(int[] nums) {
        // 定义一个 hashSet 进行去重
        HashSet<Integer> set = new HashSet<>();
        int arraySum = 0;
        int setSum = 0;

        // 1. 遍历数组元素，保存到 set，并直接求和
        for (int num : nums) {
            set.add(num);
            arraySum += num;
        }
        // 2. 集合所有元素求和
        for (Integer num : set) {
            setSum += num;
        }
        return setSum * 2 - arraySum;
    }

    // 方法四：数学方法（做异或）
    public int singleNumber4(int[] nums) {
        int result = 0;
        // 遍历所有数据，按位做异或
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        int i = new SingleNumber().singleNumber4(nums);
        System.out.println(i);
    }
}
