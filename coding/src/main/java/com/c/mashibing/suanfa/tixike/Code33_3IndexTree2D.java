package com.c.mashibing.suanfa.tixike;

/*
 题目1，IndexTree改成二维
 测试链接：https://leetcode.com/problems/range-sum-query-2d-mutable
 但这个题是付费题目
 提交时把类名、构造函数名从Code02_IndexTree2D改成NumMatrix
 */
public class Code33_3IndexTree2D {

    public class NumMatrix{
        int[][] nums;
        int[][] tree;
        int N;
        int M;

        public NumMatrix(int[][] matrix){
            N = matrix.length;
            M = matrix[0].length;
            nums = new int[N][M];
            tree = new int[N + 1][M + 1];
            //这里使用matrix数组作为输入，将index tree初始化，并计算sum结果
            for (int i = 0; i < N; i++){
                for (int j = 0; j < M; j++){
                    //一维index tree不需要在初始化时更新，是因为在主函数会调用add使index tree初始化/有值，在这道题里需要在构造函数里初始化
                    update(i,j,matrix[i][j]);
                }
            }
        }

        //统计从[1,1]到[row,col]的和
        public int sum(int row, int col){
            int sum = 0;
            //需要给i和j加1是因为index tree数组的[0][0]位置是空着没有用，真实索引从1开始
            for (int i = row + 1; i > 0; i -= -i & i){
                for (int j = col; j > 0; j -= -j & j){
                    sum += tree[i][j];
                }
            }
            return sum;
        }

        //将nums[row][col]位置更新为val
        private void update(int row, int col, int val){
            int add = val - nums[row][col];
            nums[row][col] = val;

            for (int i = row; i <= N; i += -i & i){
                for (int j = col; j <= M; j += -j & j){
                    tree[i][j] += add;
                }
            }

        }


        public int sumRegion(int row1, int col1, int row2, int col2){
            if (N == 0 || M == 0) {
                return 0;
            }
            return sum(row2,col2) + sum(row1 - 1,col1 - 1) - sum(row2,col1 - 1) - sum(row1 - 1,col2);
        }
    }
}
