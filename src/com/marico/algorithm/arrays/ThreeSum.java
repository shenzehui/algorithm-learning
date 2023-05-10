package com.marico.algorithm.arrays;

import java.util.*;

/**
 * 三数之和
 * Created by Marico on 2022/11/15
 */

public class ThreeSum {
    // 方法一：暴力法
    public List<List<Integer>> threeSum1(int[] nums) {
        // 定义结果列表
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        // 三重 for 循环，枚举所有的三数集合
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return result;
    }

    // 方法二：使用哈希表保存结果
    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // 定义一个 hashMap
        Map<Integer, List<Integer>> map = new HashMap<>();

        // 遍历数组，寻找每个数对应的那二个数
        for (int i = 0; i < n; i++) {
            int thatNum = 0 - nums[i];
            if (map.containsKey(thatNum)) {
                // 如果已经存在thatNum，就找到了一组解
                List<Integer> temList = new ArrayList<>(map.get(thatNum));
                temList.add(nums[i]); // 添加另一个数
                result.add(temList);
            }
            // 把当前数对应的两数组合都保存到 map 里
            for (int j = 0; j < i; j++) {
                // 以两数之和作为 key
                int newKey = nums[i] + nums[j];
                // 如果 key 不存在，则添加
                if (!map.containsKey(newKey)) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(nums[i]);
                    tempList.add(nums[j]);
                    map.put(newKey, tempList);
                }
            }
        }
        return result;
    }

    // 方法三：双指针法
    public List<List<Integer>> threeSum3(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        // 0.先对数组排序
        Arrays.sort(nums);

        // 1.遍历每一个元素，作为当前三元组中最小的那个（最矮个做核心）
        for (int i = 0; i < n; i++) {
            // 1.1 如果当前数已经大于0，直接退出循环
            if (nums[i] > 0) {
                break;
            }
            // 1.2 如果当前数据已经出现过，直接跳过(去重)
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 1.3 常规情况，以当前数作为最小数，定义左右指针
            int lp = i + 1;
            int rp = n - 1;
            // 只要左右指针不重叠，就继续移动指针
            while (lp < rp) {
                int sum = nums[i] + nums[lp] + nums[rp];
                // 判断 sum 与0做大小对比
                if (sum == 0) {
                    // 1.3.1 找到了一组解
                    result.add(Arrays.asList(nums[i], nums[lp], nums[rp]));
                    lp++;
                    rp--;
                    // 如果移动之后的元素相同，直接跳过(去重)
                    while (lp < rp && nums[lp] == nums[lp - 1]) {
                        lp++;
                    }
                    while (lp < rp && nums[rp] == nums[rp + 1]) {
                        rp--;
                    }
                } else if (sum < 0) { //1.3.2 小于0，较小的数增大，左指针右移
                    lp++;
                } else {  //1.3.3 大于0，较小的数减小，右指针左移
                    rp--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] input = {-1, 0, 1, 2, -1, -4};
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> result = threeSum.threeSum3(input);
        System.out.println(result);
    }
}
