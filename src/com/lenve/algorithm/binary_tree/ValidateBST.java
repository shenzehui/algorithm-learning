package com.lenve.algorithm.binary_tree;

import java.util.ArrayList;

/**
 * 判断是否是二叉搜索数
 * Created by lenve on 2022/11/27
 */

public class ValidateBST {
    // 方法一：先序遍历
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validator(root, null, null);
    }

    // 定义一个辅助校验器，用来传入上下文递归调用
    public boolean validator(TreeNode root, Integer lowerBound, Integer upperBound) {
        if (root == null) {
            return true;
        }
        // 1. 判断当期节点的值是否在上下界范围内，如果超出直接返回 false
        if (lowerBound != null && root.val <= lowerBound) {
            return false;
        }
        if (upperBound != null && root.val >= upperBound) {
            return false;
        }

        // 2.递归判断左右子树
        return validator(root.left, lowerBound, root.val) && validator(root.right, root.val, upperBound);
    }

    // 定义一个列表
    ArrayList<Integer> inOrderArray = new ArrayList<>();

    // 方法二：中序遍历（建议使用）
    public boolean isValidBST2(TreeNode root) {
        // 中序遍历，得到升序数组
        InOrder(root);

        // 2.遍历数组，判断是否升序
        for (int i = 0; i < inOrderArray.size(); i++) {
            if (i > 0 && inOrderArray.get(i) <= inOrderArray.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 实现一个中序遍历的方法，添加到数组
    private void InOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        InOrder(root.left);
        inOrderArray.add(root.val);
        InOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(8);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        boolean result = new ValidateBST().isValidBST2(node1);
        System.out.println(result);
    }
    /*
        5
       1 4
        3 6
     */
}
