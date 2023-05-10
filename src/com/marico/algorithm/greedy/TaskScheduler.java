package com.marico.algorithm.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * 任务调度器
 * Created by Marico on 2022/11/28
 */

public class TaskScheduler {
    // 方法一：模拟法
    public int leastInterval(char[] tasks, int n) {
        // 用 HashMap 统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }
        // 统计当前的任务种类数量
        int t = countMap.size();

        // 定义两个状态列表
        ArrayList<Integer> restCount = new ArrayList<>(countMap.values()); // 任务剩余次数
        ArrayList<Integer> nextAvailableTime = new ArrayList<>(Collections.nCopies(t, 1)); // 任务下次执行时间
        int time = 0; // 模拟 CPU 时钟

        // 遍历任务选择执行
        for (int i = 0; i < tasks.length; i++) {
            time++;
            int minNextAvailable = Integer.MAX_VALUE;
            // 1. 获取所有任务中最早可执行的时间
            for (int j = 0; j < t; j++) {
                // 取还没做完的任务
                if (restCount.get(j) != 0) {
                    minNextAvailable = Math.min(minNextAvailable, nextAvailableTime.get(j));
                }
            }

            // 2. 直接推进时间，执行任务
            time = Math.max(time, minNextAvailable);

            // 3. 选取可执行任务中剩余次数最多的那个执行
            int maxRestCountTask = -1; // 保存要执行任务的索引
            for (int j = 0; j < t; j++) {
                if (restCount.get(j) > 0 && nextAvailableTime.get(j) <= time) {
                    // 如果比之前保存的最大剩余任务数还大，就更新
                    if (maxRestCountTask == -1 || restCount.get(j) > restCount.get(maxRestCountTask)) {
                        maxRestCountTask = j;
                    }
                }
            }

            // 4. 执行任务，更新状态列表
            nextAvailableTime.set(maxRestCountTask, time + n + 1);  // 该任务下一个执行时间更新
            restCount.set(maxRestCountTask, restCount.get(maxRestCountTask) - 1); // 该任务数减一
        }
        return time;
    }

    // 方法二：TODO 构造法  十分巧妙的一种方式
    public int leastInterval1(char[] tasks, int n) {
        // 用 HashMap 统计每个任务出现的次数
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char task : tasks) {
            countMap.put(task, countMap.getOrDefault(task, 0) + 1);
        }
        // 任务种类数量
        int t = countMap.size();

        int maxCount = 0; // 相同任务种类最大个数
        int maxNum = 0;  // 相同任务种类最大个数的最大种类个数

        // 1. 计算 maxCount
        for (Integer count : countMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

        // 2. 计算 maxNum
        for (Character task : countMap.keySet()) {
            if (countMap.get(task) == maxCount) {
                maxNum++;
            }
        }
        // 3. 按照公式直接返回
        return Math.max(tasks.length, (maxCount - 1) * (n + 1) + maxNum); // task.length 就是如果任务数很多很多，冷却时间就不需要再考虑了，时间就是当前任务个数
    }

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int result = new TaskScheduler().leastInterval1(tasks, 2);
        System.out.println(result);
    }
}
