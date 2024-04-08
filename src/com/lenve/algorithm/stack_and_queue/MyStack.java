package com.lenve.algorithm.stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列来实现栈
 * Created by lenve on 2022/11/23
 */

// 只用两个队列实现自定义栈
public class MyStack {
    // 定义两个队列
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        // LinkedList 双端链表
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    // 入栈方法  （queue2 最后永远都是空的）
    public void push(int x) {
        // 1. 把x保存到 queue2中
        queue2.offer(x); // offer 入队操作

        // 2. 将 queue1 中所有元素出队，然后放入 queue2
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());  // poll 出队操作
        }

        // 3. 交换两个队列
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    // 出栈方法
    public int pop() {
        // queue1 出队就是出栈
        return queue1.poll();
    }

    // 判断为空
    public boolean empty() {
        return queue1.isEmpty();
    }

    public int top() {
        return queue1.peek();
    }

    public static void main(String[] args) {

    }
}
