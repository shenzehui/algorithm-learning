package com.marico.algorithm.stack_and_queue;

import java.util.Stack;

/**
 * 柱状图中最大的矩形
 * Created by Marico on 2022/11/24
 */

public class LargestRectangleInHistorgram {

    // 方法一：暴力法（遍历所有可能的宽度）
    public int largestRectangleArea1(int[] heights) {
        // 定义变量保存最大面积
        int largestArea = 0;

        // 遍历数组，作为矩形左边界
        for (int left = 0; left < heights.length; left++) {
            // 定义变量保存当前矩形高度
            int currentHeight = heights[left];

            // 遍历数组，选取矩形右边界
            for (int right = left; right < heights.length; right++) {
                // 确定当前矩形高度
                currentHeight = Math.min(currentHeight, heights[right]);

                // 计算当前矩形面积
                int curArea = (right - left + 1) * currentHeight;

                //更新最大面积
                largestArea = Math.max(curArea, largestArea);
            }
        }
        return largestArea;
    }

    // 方法二：双指针（遍历所有可能的高度）
    public int largestRectangleArea2(int[] heights) {
        // 定义变量保存最大面积
        int largestArea = 0;

        // 遍历数组，以每个柱子高度作为最终矩形的高度
        for (int i = 0; i < heights.length; i++) {
            // 保存当期的高度
            int height = heights[i];

            // 定义左右指针
            int left = i;
            int right = i;

            // 寻找左边界，左指针左移
            while (left >= 0) {
                if (heights[left] < height) {
                    break;
                }
                left--;
            }

            // 寻找右边界，右指针右移
            while (right < heights.length) {
                if (heights[right] < height) {
                    break;
                }
                right++;
            }

            // 计算当期宽度
            int width = right - left - 1;

            // 计算面积
            int currentArea = height * width;

            largestArea = Math.max(currentArea, largestArea);
        }

        return largestArea;
    }

    // 方法三：双指针法改进
    public int largestRectangleArea3(int[] heights) {
        // 定义变量保存最大面积
        int largestArea = 0;

        int n = heights.length;

        // 定义两个数组，保存每个柱子对应的左右边界
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 遍历数组，计算左边界
        for (int i = 0; i < heights.length; i++) {
            // 保存当期的高度
            int height = heights[i];

            // 定义左指针，也就是当前柱子的左一个边界
            int left = i - 1;

            // 左指针左移，寻找左边界
            while (left >= 0) {
                if (heights[left] < height) {
                    break;
                }
                left = lefts[left]; // 关键代码，将左边界设置为当前left的左边界，也就是从当前高度从右往左第一个比它矮的柱子
            }
            lefts[i] = left;  // TODO 这一步有点迷
        }

        // 遍历数组，计算右边界
        for (int i = n - 1; i >= 0; i--) {
            // 保存当期的高度
            int height = heights[i];

            // 定义右指针
            int right = i + 1;

            // 右指针右移，寻找右边界
            while (right < n) {
                if (heights[right] < height) {
                    break;
                }
                right = rights[right];
            }
            rights[i] = right;
        }
        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currArea);
        }
        return largestArea;
    }

    // 方法三：单调栈
    public int largestRectangleArea4(int[] heights) {
        // 定义变量保存最大面积
        int largestArea = 0;

        int n = heights.length;

        // 定义两个数组，保存每个柱子对应的左右边界
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 定义一个栈
        Stack<Integer> stack = new Stack<>();

        // 遍历所有柱子，作为当前高度，先找左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        stack.clear();

        // 遍历所有柱子，作为当前高度，先找右边界
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            rights[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currArea);
        }

        return largestArea;
    }

    // 方法三：单调栈优化
    public int largestRectangleArea5(int[] heights) {
        // 定义变量保存最大面积
        int largestArea = 0;

        int n = heights.length;

        // 定义两个数组，保存每个柱子对应的左右边界
        int[] lefts = new int[n];
        int[] rights = new int[n];

        // 定义一个栈
        Stack<Integer> stack = new Stack<>();

        // 初始化 rights 为右哨兵 n
        for (int i = 0; i < n; i++) {
            rights[i] = n;
        }

        // 遍历所有柱子，作为当前高度，先找左边界
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                // 栈顶元素如果小于当前元素，那么他的右边界就是当期元素
                rights[stack.peek()] = i;
                stack.pop();
            }

            // 所有大于等于当前高度的元素全部弹出，找到了左边界
            lefts[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(i);
        }

        // 遍历所有柱子，计算面积
        for (int i = 0; i < n; i++) {
            int currArea = (rights[i] - lefts[i] - 1) * heights[i];
            largestArea = Math.max(largestArea, currArea);
        }

        return largestArea;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        int maxArea = new LargestRectangleInHistorgram().largestRectangleArea5(heights);
        System.out.println(maxArea);
    }
}
