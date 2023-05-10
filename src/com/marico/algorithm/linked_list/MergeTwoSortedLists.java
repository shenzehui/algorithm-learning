package com.marico.algorithm.linked_list;

/**
 * 合并有序列表
 * Created by Marico on 2022/11/20
 */

public class MergeTwoSortedLists {
    // 方法一：迭代法
    public ListNode mergeTowLists1(ListNode l1, ListNode l2) {
        // 首先，定义一个哨兵节点，它的 next 指向最终结果的头节点
        ListNode sentinel = new ListNode(-1);

        // 保存当前结果链表里的最后一个节点，作为判断比较的"前一个节点";
        ListNode prev = sentinel;

        // 迭代遍历两个链表，直到至少有一个为 null
        while (l1 != null && l2 != null) {
            // 比较当期两个链表的头节点，较小的那个就接在结果链表末尾
            if (l1.val <= l2.val) {
                prev.next = l1;  // 将 l1 头节点连接到 prev 后面
                prev = l1;  // 指针向前移动，以下一个节点作为链表头节点
                l1 = l1.next;
            } else {
                prev.next = l2;  // 将 l2 头节点连接到 prev 后面
                prev = l2;  // 指针向前移动，以下一个节点作为链表头节点
                l2 = l2.next;
            }
        }

        // 循环结束，最多还会一个链表还没有遍历完成，因为已经排序，可以直接把剩余节点接在结果链表上
        prev.next = l1 == null ? l2 : l1;
        return sentinel.next;
    }

    // 方法二：递归
    public ListNode mergeTowLists2(ListNode l1, ListNode l2) {
        // 基准情况
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            // l1 头节点较小，直接提取出来，剩下的 l1 和 l2 继续合并，接在 l1 后面
            l1.next = mergeTowLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTowLists2(l2.next, l1);
            return l2;
        }
    }

    public static void main(String[] args) {
        // 构建一个链表，把所有节点创建出来，然后连接
        ListNode listNode11 = new ListNode(1);
        ListNode listNode12 = new ListNode(2);
        ListNode listNode13 = new ListNode(4);
        ListNode listNode21 = new ListNode(1);
        ListNode listNode22 = new ListNode(3);
        ListNode listNode23 = new ListNode(4);

        listNode11.next = listNode12;
        listNode12.next = listNode13;
        listNode13.next = null;
        listNode21.next = listNode22;
        listNode22.next = listNode23;
        listNode23.next = null;

        TestLinkedList.printList(listNode11);
        System.out.println();
        TestLinkedList.printList(listNode21);
        System.out.println();
        ListNode listNode = new MergeTwoSortedLists().mergeTowLists2(listNode11, listNode21);
        TestLinkedList.printList(listNode);
    }
}
