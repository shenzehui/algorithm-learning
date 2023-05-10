package com.marico.algorithm.binary_tree;

/**
 * 判断是否为平衡二叉树
 * Created by Marico on 2022/11/27
 */

public class BalanceBinaryTree {

    // 方法一：先序遍历 O(nlog2n)
    public boolean isBalanced(TreeNode root) {
        // 基准情况
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right); //递归调用
    }

    // 定义一个计算树高度的方法
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }


    // 方法二：后续遍历
    public boolean isBalanced2(TreeNode root) {
        return balancedHeight(root) > -1;
    }

    // 定义一个直接判断当期树是否平衡的方法，也返回高度
    public int balancedHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 递归计算左右子树高度
        int leftHeight = balancedHeight(root.left);
        int rightHeight = balancedHeight(root.right);

        // 如果子树不平衡，直接返回 -1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // 如果平衡，返回当前高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        boolean result = new BalanceBinaryTree().isBalanced2(node1);
        System.out.println(result);
    }
}
