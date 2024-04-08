package com.lenve.algorithm.stack_and_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用一个队列实现自定义栈
 * Created by lenve on 2022/11/23
 */

public class MyStack2 {

    // 定义两个队列
    Queue<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        // 1. 记录当前队列的长度
        int length = queue.size();

        // 2. 把 x 入队
        queue.offer(x);

        // 3. 把 queue 中原先的所有元素依次出队，然后再入队
        for (int i = 0; i < length; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
