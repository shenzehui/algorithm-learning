package com.lenve.algorithm.binary_tree;

/**
 * 翻转二叉树
 * Created by lenve on 2022/11/27
 */

public class InvertBinaryTree {
    // 1. 先序遍历
    public TreeNode invertNodeTree(TreeNode root) {
        // 基准场景
        if (root == null) {
            return null;
        }
        // 1. 先处理根节点，交换左右子节点
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 2. 递归处理左右子树
        invertNodeTree(root.left);
        invertNodeTree(root.right);
        return root;
    }

    // 2. 后序遍历
    public TreeNode invertNodeTree2(TreeNode root) {
        // 基准场景
        if (root == null) {
            return null;
        }
        // 1. 递归处理左右子树
        TreeNode left = invertNodeTree(root.left);  // left 就是 root.left
        TreeNode right = invertNodeTree(root.right);

        // 2. 处理根节点，交换左右子节点
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        TreePrinter.printTreeLevelOrder(node1);

        System.out.println();
        TreeNode result = new InvertBinaryTree().invertNodeTree2(node1);
        TreePrinter.printTreeLevelOrder(result);
        /*
              4
           2    7
         1  3  6  9

         */
    }
}
