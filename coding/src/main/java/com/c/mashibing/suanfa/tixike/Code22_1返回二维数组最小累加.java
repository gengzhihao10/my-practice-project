package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 返回最小距离累加和
 动态规划
 题目2，使用空间压缩技巧，省空间
 */
public class Code22_1返回二维数组最小累加 {

    /*
     * @author gengzhihao
     * @date 2023/9/11 9:29
     * @description 暴力递归
     * @param m
     * @return int
     **/
    public static int minPathSum0(int[][] m){
        if (m == null || m.length == 0){
            return -1;
        }

        return process0(m,0,0);
    }

    //返回当前点(x,y)到终点(X,Y)的最小路径和
    private static int process0(int[][] m, int x, int y) {
        if (x == m.length-1 && y == m[0].length-1){
            return m[m.length-1][m[0].length-1];
        }

        return m[x][y] + Math.min(process0(m,x,y+1),process0(m,x+1,y));
    }

    //****************************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/9/12 9:41
     * @description 题目1
     * @param m
     * @return int
     **/
    public static int minPathSum1(int[][] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }

        //申请dp表
        int M = arr.length-1;
        int N = arr[0].length-1;
        int[][] dp = new int[M+1][N+1];

        //dp表base case初始化
        dp[M][N] = arr[M][N];

        //dp表计算
        //最下一行
        for (int n = N-1; n >= 0; n--){
            dp[M][n] = arr[M][n] + dp[M][n+1];
        }
        //最右一列
        for (int m = M-1; m >= 0; m--){
            dp[m][N] = arr[m][N] + dp[m+1][N];
        }
        for (int m = M-1; m>= 0; m--){
            for (int n = N-1; n>= 0; n--){
                dp[m][n] = arr[m][n] + Math.min(dp[m][n+1],dp[m+1][n]);
            }
        }
        return dp[0][0];
    }



    //****************************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/9/11 12:06
     * @description 题目2
     * @param m
     * @return int
     **/
    public static int minPathSum2(int[][] arr){
        if (arr == null || arr.length == 0){
            return -1;
        }
        int M = arr.length-1;
        int N = arr[0].length-1;
        int[] dp = new int[N+1];

        dp[N] = arr[M][N];

        //计算最下一行
        for (int n = N-1; n >= 0; n--){
            dp[n] = arr[M][n] + dp[n+1];
        }
        //计算其他行
        for (int m = M-1; m >= 0; m--){
            dp[N] =  arr[m][N] + dp[N];
            for (int n = N-1; n>= 0;n--){
                dp[n] = arr[m][n] + Math.min(dp[n+1],dp[n]);
            }
        }

        return dp[0];
    }


    //****************************************************************************************************************


    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(test(m));
        System.out.println(minPathSum2(m));

    }


    public static int test(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }
}
