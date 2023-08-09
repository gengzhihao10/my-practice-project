package com.c.mashibing.suanfa.tixike;

/*
 题目1，https://leetcode.cn/problems/number-of-provinces/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 题目2，岛问题
 给定一个二维数组matrix，里面的值不是1就是0，
 上、下、左、右相邻的1认为是一片岛，
 返回matrix中岛的数量
 */
public class Code16_1leetcode原题547题FreindCircles1 {


    /*
     * @author gengzhihao
     * @date 2023/8/9 9:59
     * @description 题目1
     * @param M
     * @return int
     **/
    public static int qs1_process1(int[][] M){
        if (M == null || M.length == 0){
            return 0;
        }

        UnionSet unionSet = new UnionSet();
        for (int i = 0; i < M.length; i++){
            for (int j = i + 1; j < M[i].length; j++){
                unionSet.union(i,j);
            }
        }
        return unionSet.setNum;
    }


    public static class UnionSet{
        public int MAXN = 1000001;

        //fater[i] i : i的父亲为fater[i]
        public int[] father = new int[MAXN];
        //仅有为head的size[i]是有效的
        public int[] size = new int[MAXN];
        //help[]是帮助数组
        public int[] help = new int[MAXN];
        //set数量
        public int setNum = 0;
        public int length = 0;


        public UnionSet() {
        }

        // 初始化并查集
        public void init(int n) {
            for (int i = 1; i <= n; i++){
                father[i] = i;
                size[i] = 1;
            }
            setNum = n;
            length = n;
        }

        public void add(int i){
            father[length] = i;
            setNum++;
            size[length++] = 1;
        }

        // 查询x和y是不是一个集合
        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }

        // 从i开始寻找集合代表点
        public int find(int cur) {
            int parent = father[cur];
            int index = 0;

            while (parent != cur){
                help[index++] = cur;
                cur = parent;
                parent = father[parent];
            }

            for (;index > 0; index--){
                father[help[index]] = parent;
            }
            return parent;
        }

        // x所在的集合，和y所在的集合，合并成一个集合
        public void union(int x, int y) {
            int head1 = find(x);
            int head2 = find(y);

            if (head1 == head2){
                return;
            }

            int size1 = size[head1];
            int size2 = size[head2];

            int big = size1 >= size2 ? size1 : size2;
            int small = big == size1 ? size2 : size1;

            size[big] = size[big] + size[small];
            father[small] = big;
            setNum--;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/9 10:20
     * @description 题目2
     * @param board
     * @return int
     **/
    public static int qs2_process1(char[][] board){

        if (board == null || board.length == 0){
            return 0;
        }

        int count = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == '1'){
                    count++;
                    process2(board,i,j);
                }
            }
        }
        return count;
    }

    //将和[i,j]位置连同的所有岛，字符变为'X'
    private static void process2(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != '1'){
            return;
        }

        board[i][j] = 'X';
        process2(board,i+1,j);
        process2(board,i-1,j);
        process2(board,i,j+1);
        process2(board,i,j-1);
    }
}
