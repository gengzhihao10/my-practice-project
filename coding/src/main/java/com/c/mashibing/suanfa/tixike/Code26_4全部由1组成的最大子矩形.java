package com.c.mashibing.suanfa.tixike;

import java.util.Stack;

/*
 题目1，
 给定一个二维数组matrix，其中的值不是0就是1，
 返回全部由1组成的最大子矩形，内部有多少个1
 测试链接：https://leetcode.com/problems/maximal-rectangle/
 */
public class Code26_4全部由1组成的最大子矩形 {


    /*
     * @author gengzhihao
     * @date 2023/11/8 10:54
     * @description 题目1。使用压缩数组技巧
     * @param map
     * @return int
     **/
    public static int maximalRectangle(char[][] map){
        if (map == null || map.length == 0 || map[0].length == 0){
            return 0;
        }

        //1. 压缩数组
        int[][] compressedArr = new int[map.length][map[0].length];
        for (int j = 0; j < compressedArr[0].length; j++){
            compressedArr[0][j] = convert(map[0][j]);
        }
        for (int i = 1; i < compressedArr.length; i++){
            for (int j = 0; j < compressedArr[i].length; j++){
                /*
                如果map[i][j]为0，表示当前位置不能参与计算了，所以compressedArr[i][j]也需要为0
                如果map[i][j]不为0，那么map[i][j]就可以参加计算，compressedArr[i][j] = compressedArr[i-1][j] + map[i][j]
                举例，假设compressedArr[2][3] = 2，表示map第3列的第2行及以上，有连续的2个格子为1，即map[2][3] [1][3]为1，[0][3]为0
                那么问题就转换为了直方图的最大长方形面积问题，compressedArr[2][3]表示该矩形高度为2.
                当compressedArr[2][3]弹出时，求的以compressedArr[2][3]为最小值的子数组的直方图的公共面积了
                 */
                compressedArr[i][j] = (convert(map[i][j]) == 0 ? 0: (compressedArr[i-1][j]) + convert(map[i][j]));
            }
        }

        //2. 遍历，使用单调栈
        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i < compressedArr.length; i++){

            for (int j = 0; j < compressedArr[i].length; j++){
                while (!stack.isEmpty() && (compressedArr[i][stack.peek()] >= compressedArr[i][j])){
                    int popIndex = stack.pop();
                    max = Math.max(max,
                            compressedArr[i][popIndex] * ((j - 1) - (stack.isEmpty() ? -1 : stack.peek())));
                }
                stack.push(j);
            }

            while (!stack.isEmpty()){
                int popIndex = stack.pop();
                max = Math.max(max,
                        compressedArr[i][popIndex] * ((compressedArr[i].length - 1) - (stack.isEmpty() ? -1 : stack.peek())));
            }

        }

        return max;
    }

    //对map数组里的char进行转换
    private static int convert(char c){
        return c == '1' ? 1 : 0;
    }


}
