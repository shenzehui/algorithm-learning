package com.lenve.algorithm.linked_list;

import java.util.Stack;

/**
 * 删除倒数第 N 个节点
 * Created by lenve on 2022/11/20
 */

public class RemoveNthNodeFromEnd {
    // 方法一：计算链表长度
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 1. 扫描链表，得到长度 l
        int l = getLength(head);

        // 2. 从前到后继续遍历，找到正数第 l-n+1 个元素
        // 定义一个哨兵节点，next 指向头节点
        ListNode sentinel = new ListNode(-1, head);

        ListNode curr = sentinel;
        for (int i = 0; i < l - n; i++) {
            curr = curr.next;
        }
        // 找到第 l-n 个节点
        // 跳过第 l-n+1 个节点
        curr.next = curr.next.next;

        return sentinel.next;  // 返回头结点
    }

    // 定义一个计算链表长度的链表
    public static int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    // 方法二：使用栈
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 定义一个哨兵节点，next 指向头节点
        ListNode sentinel = new ListNode(-1, head);

        ListNode curr = sentinel;

        // 定义一个栈
        Stack<ListNode> stack = new Stack<>();

        // 1. 将所有节点入栈
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        // 2. 弹栈 n 次
        for (int i = 0; i < n; i++) {
            stack.pop();
        }

        stack.peek().next = stack.peek().next.next;

        return sentinel.next;
    }

    // 方法三：双指针（一次遍历）
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        // 定义一个哨兵节点，next 指向头节点
        ListNode sentinel = new ListNode(-1, head);

        // 定义前后双指正
        ListNode first = sentinel, second = sentinel;

        // 1. first 先走 n+1 步
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }

        // 2. first、second 同时前进，当 first 变为 null，second 就是倒数第 n+1 个节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // 3. 删除倒数第 n 个节点
        second.next = second.next.next;
        return sentinel.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = null;

        TestLinkedList.printList(listNode1);
        System.out.println();
        ListNode listNode = new RemoveNthNodeFromEnd().removeNthFromEnd3(listNode1, 2);
        TestLinkedList.printList(listNode);
    }
}
