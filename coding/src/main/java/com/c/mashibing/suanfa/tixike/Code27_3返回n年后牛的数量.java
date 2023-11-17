package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 第一年农场有1只成熟的母牛A，往后的每年：
 1）每一只成熟的母牛都会生一只母牛
 2）每一只新出生的母牛都在出生的第三年成熟
 3）每一只母牛永远不会死
 返回N年后牛的数量

 */
public class Code27_3返回n年后牛的数量 {


    /*
     * @author gengzhihao
     * @date 2023/11/16 11:30
     * @description 题目1
     * @param n
     * @return int
     **/
    public static int c(int n) {
        if (n <= 0){
            return -1;
        }

        if (n == 1 || n == 2 || n == 3){
            return n;
        }

        int[][] base = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        int[][] powerBase = power(base,n-3);

        return 3 * powerBase[0][0] + 2 * powerBase[1][0] + powerBase[2][0];
    }

    //返回input矩阵的n次方，要求复杂度log(n)
    private static int[][] power(int[][] input, int n) {

        //初始化单位矩阵，单位矩阵相当于1
        int[][] res = new int[input.length][input[0].length];
        for (int i = 0; i < input.length; i++){
            res[i][i] = 1;
        }
        int[][] t = input;
        for (; n != 0; n >>= 1){
            if ((n & 1) != 0){
                res = multiply(res,t);
            }
            t = multiply(t,t);
        }
        return res;
    }


    //返回两个矩阵相乘的结果
    private static int[][] multiply(int[][] base, int[][] input) {
        int m = base.length;
        int n = base[0].length;
        int j = input[0].length;

        int[][] res = new int[m][j];

        for (int m1 = 0; m1 < m; m1++){
            for (int j1 = 0; j1 < j; j1++){
                int sum = 0;
                for (int n1 = 0; n1 < n; n1++){
                    sum += base[m1][n1] * input[n1][j1];
                }
                res[m1][j1] = sum;
            }
        }
        return res;
    }

//******************************************************************************************************

    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static void main(String[] args) {
        int input = 10;

        System.out.println("c: " + c(input));
        System.out.println("c1: " + c1(input));


        int[][] base1 = {
                {1, 0, 1},
                {1, 0, 0},
                {0, 1, 0}
        };
        int[][] base2 = {
                {1, 0, 1},
                {1, 0, 0},
                {0, 1, 0}
        };
        int[][] res1 = product(base1,base2);
        int[][] res2 = multiply(base1,base2);
        System.out.println();
    }
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
