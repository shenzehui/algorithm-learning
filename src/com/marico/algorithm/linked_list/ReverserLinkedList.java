package com.marico.algorithm.linked_list;

/**
 * 反转链表
 * Created by Marico on 2022/11/20
 */

public class ReverserLinkedList {
    // 方法一：迭代法
    public ListNode reverseList1(ListNode head) {
        // 定义两个指针，指向当前访问节点，以及上一个节点
        ListNode curr = head;
        ListNode prev = null;

        // 依次迭代链表中的节点，将 next 指针指向 prev
        while (curr != null) {
            // 临时保存当前节点的下一个节点
            ListNode tempNext = curr.next;
            // 反转链表，将 next 指针指向上一个节点
            curr.next = prev;
            // 更新指针，当前指针变为之前的 next，上一个指针变为 curr
            prev = curr;
            curr = tempNext;
        }
        // prev 指向的就是末尾的节点，也就是翻转之后的头结点
        return prev;
    }

    // 方法二：递归
    public ListNode reverseList2(ListNode head) {
        // 基准情况
        if (head == null || head.next == null) {
            return head;
        }
        // 递归调用，翻转剩余所有节点
        ListNode restHead = head.next;
        ListNode reversedRest = reverseList2(restHead);

        // 把当前节点接在翻转之后的链表末尾
        restHead.next = head;
        // 当前节点就是链表末尾，直接指向 null
        head.next = null;

        // 递归调用，最终返回的是头节点
        return reversedRest;
    }


    public static void main(String[] args) {
        // 构建一个链表，把所有节点创建出来，然后连接
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

        ReverserLinkedList reverserLinkedList = new ReverserLinkedList();
        TestLinkedList.printList(listNode1);
        System.out.println();
        ListNode listNode = reverserLinkedList.reverseList2(listNode1);
        TestLinkedList.printList(listNode);
    }
}
