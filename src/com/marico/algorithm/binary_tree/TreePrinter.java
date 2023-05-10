package com.marico.algorithm.binary_tree;

import java.util.LinkedList;

/**
 * 二叉树的遍历
 * Created by Marico on 2022/11/27
 */

public class TreePrinter {

    // 1. 先序遍历
    public static void printTreePreOrder(TreeNode root) {
        // 处理基准情形
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t"); // 先打印根
        printTreePreOrder(root.left); // 打印左子树
        printTreePreOrder(root.right); // 打印右子树
    }

    // 2. 中序遍历
    public static void printTreeInOrder(TreeNode root) {
        // 处理基准情形
        if (root == null) {
            return;
        }
        printTreeInOrder(root.left); // 打印左子树
        System.out.print(root.val + "\t"); // 再打印根
        printTreeInOrder(root.right); // 打印右子树
    }

    // 3. 后续遍历
    public static void printTreePostOrder(TreeNode root) {
        // 处理基准情形
        if (root == null) {
            return;
        }
        printTreePostOrder(root.left); // 打印左子树
        printTreePostOrder(root.right); // 打印右子树
        System.out.print(root.val + "\t"); // 再打印根
    }

    // 4. 层序遍历  类似图中的广度优先搜索
    public static void printTreeLevelOrder(TreeNode root) {
        // 定义一个队列
        LinkedList<TreeNode> queue = new LinkedList<>();

        // 先把更节点放入队列
        queue.offer(root);

        // 只要队列不为空，就一直出队
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.pop();
            System.out.print(curNode.val + "\t");
            // 将子节点加入队列
            if (curNode.left != null) {
                queue.offer(curNode.left);
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node4.right = node6;

        printTreePreOrder(node1);
        System.out.println();
        printTreeInOrder(node1);
        System.out.println();
        printTreePostOrder(node1);
        System.out.println();

        printTreeLevelOrder(node1);

        /*
         *   1
         * 2   3
         *    4  5
         *     6
         */
    }
}
