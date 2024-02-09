package com.c.mashibing.suanfa.tixike;

/*
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
        //数组必须为正方形
        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length){
            return;
        }

        int start = 0;
        int end = matrix[0].length - 1;
        while (start <= end){
            rotateOne(matrix,start++,end--);
//            System.out.println("==========================");
//            printMatrix(matrix);
//            System.out.println("==========================");
        }

    }

    //只旋转正方形中的一层
    private static void rotateOne(int[][] matrix, int start, int end) {
        for (int i = start; i < end; i++){
            int temp = matrix[start][start + i];
            matrix[start][start + i] = matrix[end - i][start];
            matrix[end - i][start] = matrix[end][end - i];
            matrix[end][end - i] = matrix[start + i][end];
            matrix[start + i][end] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

//    public static void main(String[] args) {
//        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
//        printMatrix(matrix);
//        rotate(matrix);
//        System.out.println("=========");
//        printMatrix(matrix);
//
//    }

    //*******************************************************************************************

    /*
     * @author gengzhihao
     * @date 2024/2/8 14:51
     * @description 题目2
     * @param matrix
     **/
    public static void spiralOrderPrint(int[][] matrix){
       if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
           return;
       }

       //水平方向起止
       int standardStart = 0, standardEnd = matrix[0].length - 1;
       //竖直方向起止
       int verticalStart = 0,verticalEnd = matrix.length - 1;

       while (standardStart <= standardEnd && verticalStart <= verticalEnd){
           spiralOrderPrintOne(matrix,standardStart++,standardEnd--,verticalStart++,verticalEnd--);
       }
    }

    //转圈打印一层
    private static void spiralOrderPrintOne(int[][] matrix, int ss, int se, int vs, int ve) {
        //只剩下垂直方向一条线
        int curS = ss, curV = vs;
        if (ss == se){
            for (; curV <= ve; curV++){
                System.out.print(" " + matrix[curS][curV]);
            }
        }
        //只剩下水平方向一条线
        else if (vs == ve){
            for (; curS <= se; curS++){
                System.out.print(" " + matrix[curS][curV]);
            }
        }
        //正常打印一圈
        else {
            //水平方向 从左向右
            while (curV < ve){
                System.out.print(" " + matrix[curS][curV++]);
            }
            //垂直方向 从上到下
            while (curS < se){
                System.out.print(" " + matrix[curS++][curV]);
            }
            //水平方向 从右到左
            while (curV > vs){
                System.out.print(" " + matrix[curS][curV--]);
            }
            //垂直方向 从下到上
            while (curS > ss){
                System.out.print(" " + matrix[curS--][curV]);
            }
        }
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
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return;
        }

        //上方的点的起止坐标
        int us = 0, uv = 0;
        //下方的点的起止坐标
        int ds = 0, dv = 0;
        //true表示向右上打印
        boolean flag = true;
        for (int i = 0; i < matrix.length + matrix[0].length; i++){
            printZigZag(matrix,us,uv,ds,dv,flag);
            //fixme 问题就在于怎么控制us ue ds de等坐标的正确变化呢？
            us = uv == matrix[0].length - 1 ? us + 1 : us;
            uv = uv == matrix[0].length - 1 ? uv : uv + 1;
            dv = ds == matrix.length - 1 ? dv + 1 : dv;
            ds = ds == matrix.length - 1 ? ds : ds + 1;
            //换个方向打印
            flag = !flag;
        }
    }

    //打印一对数组
    private static void printZigZag(int[][] matrix, int us, int uv, int ds, int dv, boolean flag) {
        if (flag){
            int curs = ds;
            int curv = dv;
            //向右上打印
            while (curs >= us){
                System.out.print(" " + matrix[curs--][curv++]);
            }
        }else {
            int curs = us;
            int curv = uv;
            //向左下打印
            while (curs <= ds){
                System.out.print(" " + matrix[curs++][curv--]);
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
