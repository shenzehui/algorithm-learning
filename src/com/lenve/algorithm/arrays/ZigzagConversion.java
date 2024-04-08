package com.lenve.algorithm.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * N字形变换
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
}
