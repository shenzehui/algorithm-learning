package com.lenve.algorithm.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * N字形变换（Z字形变换）
 * Created by szh on 2023-09-25
 *
 * @author szh
 */

public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            // 在N的起始点或者终点时转向
            if (i == 0 || i == numRows - 1) {
                // 转向
                flag = -flag;
            }
            i += flag;
        }
        return "0";
    }

    public String convert1(String s, int numRows) {
        // 特殊情况处理
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuffer[] rows = new StringBuffer[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuffer();
        }

        int curRow = 0;
        // 先不向下
        boolean down = false;

        for (char c : s.toCharArray()) {
            rows[curRow].append(c);

            if (curRow == 0 || curRow == numRows - 1) {
                down = !down;
            }
            curRow += down ? 1 : -1;
        }

        StringBuffer result = new StringBuffer();
        for (StringBuffer row : rows) {
            result.append(row);
        }
        return result.toString();
    }
}
