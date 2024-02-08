package com.c.mashibing.suanfa.tixike;

/*
todo
 题目1，
 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
 a  b  c		    g  d  a
 d  e  f			h  e  b
 g  h  i			i  f  c
 题目2，
 给定一个长方形矩阵matrix，实现转圈打印
 a  b  c  d
 e  f   g  h
 i   j   k   L
 打印顺序：a b c d h L k j I e f g
 题目3，
 给定一个正方形或者长方形矩阵matrix，实现zigzag打印
 0 1 2
 3 4 5
 6 7 8
 打印: 0 1 3 6 4 2 5 7 8

 */
public class Code41_7给定一个正方形矩阵matrix原地调整成顺时针90度转动的样子 {


    /*
     * @author gengzhihao
     * @date 2024/2/8 14:56
     * @description 题目1
     * @param matrix
     **/
    public static void rotate(int[][] matrix){

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }

    //*******************************************************************************************

    /*
     * @author gengzhihao
     * @date 2024/2/8 14:51
     * @description 题目2
     * @param matrix
     **/
    public static void spiralOrderPrint(int[][] matrix){

    }

//    public static void main(String[] args) {
//        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
//                { 13, 14, 15, 16 } };
//        spiralOrderPrint(matrix);
//
//    }

    //*************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2024/2/8 15:01
     * @description 题目3
     * @param matrix
     **/
    public static void printMatrixZigZag(int[][] matrix){

    }


//    public static void main(String[] args) {
//        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
//        printMatrixZigZag(matrix);
//
//    }
}
