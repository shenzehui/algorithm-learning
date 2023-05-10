package com.marico.algorithm.stack_and_queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 用两个栈实现队列：出队时反转
 * Created by Marico on 2022/11/23
 */

public class MyQueue2 {

    // 定义两个栈
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        // 1. 判断 stack2 是否为空，如果为空，就要将 stack1 中所有的元素弹出，然后压入
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // 弹出 stack2 栈顶元素
        return stack2.pop();
    }

    public int peek() {
        // 1. 判断 stack2 是否为空，如果为空，就要将 stack1 中所有的元素弹出，然后压入
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // 返回 stack2 栈顶元素
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public static void main(String[] args) {
        // 队列测试
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println("myQueue.peek() = " + myQueue.peek());
        myQueue.pop();
        myQueue.empty();
    }
}
