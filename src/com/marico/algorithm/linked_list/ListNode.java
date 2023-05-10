package com.marico.algorithm.linked_list;

/**
 * Created by Marico on 2022/11/19
 */

public class ListNode {

    int val; // 当前节点保存的数据值

    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
