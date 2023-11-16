package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 斐波那契数列矩阵乘法方式的实现
 1）斐波那契数列的线性求解（O(N)）的方式非常好理解
 2）同时利用线性代数，也可以改写出另一种表示
 | F(N) , F(N-1) | = | F(2), F(1) |  *  某个二阶矩阵的N-2次方
 3）求出这个二阶矩阵，进而最快求出这个二阶矩阵的N-2次方

 */
public class Code27_2求斐波那契数列矩阵乘法的方法 {


    /*
     * @author gengzhihao
     * @date 2023/11/15 14:47
     * @description 题目1
     * @param n
     * @return int
     **/
    public static int f(int n){
        if (n <= 0){
            return -1;
        }
        if (n == 1 || n == 2){
            return 1;
        }

        //系数
        int[][] base = {{1,1},{1,0}};
        int[][] power = getPower(base,n - 2);
        return power[0][0] + power[1][0];
    }

    //返回input矩阵的n次方
    private static int[][] getPower(int[][] input, int n) {

        //初始化res矩阵，作为矩阵的单位1存在
        int[][] res = new int[input.length][input[0].length];
        for (int i = 0; i < res.length; i++){
            res[i][i] = 1;
        }

        for (;n != 0; n /= 2){
            //n的末尾为1
            if ((n & 1) != 0){
                res = product(res,input);
            }
            input = product(input,input);
        }
        return input;
    }


    // 两个矩阵乘完之后的结果返回
    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length; // a的列数同时也是b的行数
        int[][] ans = new int[n][m];
        for(int i = 0 ; i < n; i++) {
            for(int j = 0 ; j < m;j++) {
                for(int c = 0; c < k; c++) {
                    ans[i][j] += a[i][c] * b[c][j];
                }
            }
        }
        return ans;
    }
}
