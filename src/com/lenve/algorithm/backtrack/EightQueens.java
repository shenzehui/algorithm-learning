package com.lenve.algorithm.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 八皇后问题
 * Created by lenve on 2022/12/1
 */

public class EightQueens {
    // 方法一：暴力穷举
    public List<int[]> eightQueens() {
        // 定义保存结果的 list
        ArrayList<int[]> result = new ArrayList<>();

        // 用一个 int[8] 数组保存一组解
        int[] solution = new int[8];

        // 遍历八皇后每种可以摆放的场景，判断是否符合题目限制
        for (solution[0] = 0; solution[0] < 8; solution[0]++) {
            for (solution[1] = 0; solution[1] < 8; solution[1]++) {
                for (solution[2] = 0; solution[2] < 8; solution[2]++) {
                    for (solution[3] = 0; solution[3] < 8; solution[3]++) {
                        for (solution[4] = 0; solution[4] < 8; solution[4]++) {
                            for (solution[5] = 0; solution[5] < 8; solution[5]++) {
                                for (solution[6] = 0; solution[6] < 8; solution[6]++) {
                                    for (solution[7] = 0; solution[7] < 8; solution[7]++) {
                                        if (check(solution)) {
                                            result.add(Arrays.copyOf(solution, 8)); // 添加数组时需要复制一份，避免出现相同地址引用导致数组不断变化
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    // 定义一个判断当前摆放方式是否有效的方法
    private boolean check(int[] a) {
        // 任意两个皇后进行比较
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 8; j++) {
                // 判断不能在同一列，并且行列索引差不能相等
                if (a[i] == a[j] || Math.abs(a[i] - a[j]) == j - i) { //列相等且在同一斜线
                    return false;
                }
            }
        }
        return true;
    }


    // 方法二：回溯法
    HashSet<Integer> cols = new HashSet<>();
    HashSet<Integer> diags1 = new HashSet<>();
    HashSet<Integer> diags2 = new HashSet<>();

    public List<int[]> eightQueens1() {

        // 定义一个保存结果的 List
        ArrayList<int[]> result = new ArrayList<>();

        // 用一个 int[8] 数组保存一组解
        int[] solution = new int[8];

        // 先对 solution 做初始填充，表示皇后还没有填充
        Arrays.fill(solution, -1);

        // 定义回溯方法，递归调用
        backtrack(result, solution, 0);

        return result;
    }

    // 实现回溯方法
    private void backtrack(ArrayList<int[]> result, int[] solution, int row) {
        // 首先处理递归调用结果场景
        if (row >= 8) {
            // 已经直接得到了所有行的填充结果，构建了一组解
            result.add(Arrays.copyOf(solution, 8));
        } else {
            // 对于当前行，考察可能的皇后位置，遍历每一列
            for (int column = 0; column < 8; column++) {
                // 1. 如果已经和之前的皇后冲突，直接跳过，寻找下一个位置
                // 1.1 判断同一列
                if (cols.contains(column)) {
                    continue;
                }
                // 1.2 判断两条斜线
                int diag1 = row - column;
                int diag2 = row + column;
                if (diags1.contains(diag1)) {
                    continue;
                }
                if (diags2.contains(diag2)) {
                    continue;
                }

                // 2. 如果不冲突，当前位置就放置皇后
                solution[row] = column;
                cols.add(column);
                diags1.add(diag1);
                diags2.add(diag2);

                // 3. 递归调用，深度搜索下一行
                backtrack(result, solution, row + 1);

                // 4. 回溯，将状态回滚，继续遍历当前行皇后可能的位置
                solution[row] = -1;
                cols.remove(column);
                diags1.remove(diag1);
                diags2.remove(diag2);
            }
        }
    }


    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        List<int[]> ints = eightQueens.eightQueens1();
        System.out.println("一共有多少种" + ints.size() + "不同的摆法");

        for (int[] result : ints) {
            printQueens(result);
            System.out.println("===========");
        }

    }

    // 打印解的方法
    public static void printQueens(int[] queens) {
        int n = queens.length;
        // 打印每一行
        for (int i = 0; i < n; i++) {
            String[] line = new String[n];
            Arrays.fill(line, "□");
            line[queens[i]] = "Q";
            for (String str : line) {
                System.out.print(str + "\t");
            }
            System.out.println();
        }
    }
}
