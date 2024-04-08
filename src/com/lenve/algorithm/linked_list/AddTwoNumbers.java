package com.lenve.algorithm.linked_list;

/**
 * 两数相加
 * Created by lenve on 2022/11/20
 */

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2;
        ListNode temp = new ListNode(-1);
        ListNode res = temp;
        int carry = 0;

        while (p != null || q != null) {
            int num1 = p == null ? 0 : p.val;
            int num2 = q == null ? 0 : q.val;
            int result = num1 + num2 + carry;
            temp.next = new ListNode(result % 10);
            carry = result / 10;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
            temp = temp.next;
        }
        if (carry != 0) {
            temp.next = new ListNode(carry);
        }
        return res.next;
    }

    // 链表 ListNode 的使用
    public ListNode addTwoNumber(ListNode l1, ListNode l2) {
        // 初始化，存放结果
        ListNode res = new ListNode(0);

        ListNode p = l1, q = l2, temp = res;

        int carray = 0;

        while (p != null || q != null) {
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;

            int result = x + y + carray;

            carray = result / 10;

            temp.next = new ListNode(result % 10);

            temp = temp.next;

            if (p != null) {
                p = p.next;
            }

            if (q != null) {
                q = q.next;
            }
        }

        // 最后处理下进位数
        if (carray != 0) {
            temp.next = new ListNode(carray);
        }

        return res.next;
    }

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(2);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(3);

        ListNode listNode21 = new ListNode(5);
        ListNode listNode22 = new ListNode(6);
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
        ListNode listNode = new AddTwoNumbers().addTwoNumber(listNode11, listNode21);
        TestLinkedList.printList(listNode);
    }
}
