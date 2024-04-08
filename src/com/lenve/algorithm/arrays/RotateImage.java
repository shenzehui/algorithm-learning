package com.lenve.algorithm.arrays;

/**
 * 旋转图像
 * Created by lenve on 2022/11/16
 */

public class RotateImage {

    /**
     * 方法一：数学方法（矩阵转置，再翻转每一行）
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 1. 转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. 翻转每一行
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }

    }

    /**
     * 方法二：分治思想，分为四个矩阵分别考虑
     */
    public void rotate1(int[][] matrix) {
        int n = matrix.length;

        // 遍历四分之一矩阵，左上角
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                // 对于 matrix[i][j]，需要找到不同的四个矩阵中对应的另外三个位置和元素
                // 定义一个临时数组，保存对应的四个元素
                int[] temp = new int[4];
                int row = i;
                int col = j;
                // 行列转换的规律：col -> newRol rwo + newCol = n -1
                for (int k = 0; k < 4; k++) {
                    temp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }

                // 再次遍历要处理的四个位置，将旋转之后的数据填入
                for (int k = 0; k < 4; k++) {
                    // 用上一个值替换当前的位置
                    matrix[row][col] = temp[(k + 3) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }

    // 方法三：改进
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        // 遍历四分之一矩阵
        for (int i = 0; i < n / 2 + n % 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i]; // 将上一个位置的元素填入
                matrix[n - j - 1][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] image1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] image2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate1(image1);
        rotateImage.printImage(image1);

        rotateImage.rotate1(image2);
        rotateImage.printImage(image2);
    }

    private void printImage(int[][] image) {
        System.out.println("image:");
        for (int[] line : image) {
            for (int point : line) {
                System.out.print(point + "\t");
            }
            System.out.println();
        }
    }
}
