package com.c.mashibing.suanfa.tixike;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 题目1，岛问题，使用并查集+HashMap+对象解法
 题目2，岛问题，使用并查集+一维数组解法
 */
public class Code16_1leetcode原题547题FreindCircles2 {

    /*
     * @author gengzhihao
     * @date 2023/8/10 8:55
     * @description 题目1
     * @param M
     * @return int
     **/
    public static int qs1_process1(char[][] M){
        if (M == null || M.length == 0){
            return 0;
        }

        UnionSet1 unionSet1 = new UnionSet1();
        Node[][] nodes = new Node[M.length][M[0].length];

        for (int i = 0; i < M.length; i++){
            if (M[i][0] == '1'){
                nodes[i][0] = new Node();
                unionSet1.add(nodes[i][0]);

                if (i > 0 && nodes[i-1][0] != null){
                    unionSet1.union(nodes[i][0],nodes[i-1][0]);
                }
            }
        }
        for (int j = 0; j < M[0].length; j++){
            if (M[0][j] == '1'){
                nodes[0][j] = new Node();
                unionSet1.add(nodes[0][j]);

                if (j > 0 && nodes[0][j-1] != null){
                    unionSet1.union(nodes[0][j],nodes[0][j-1]);
                }
            }
        }
        for (int i = 1; i < M.length; i++){
            for (int j = 1; j < M[i].length; j++){
                if (M[i][j] == '1'){

                    nodes[i][j] = new Node();

                    unionSet1.add(nodes[i][j]);

                    if (nodes[i-1][j] != null){
                        unionSet1.union(nodes[i][j],nodes[i-1][j]);
                    }
                    if (nodes[i][j-1] != null){
                        unionSet1.union(nodes[i][j],nodes[i][j-1]);
                    }
                }
            }
        }
        return unionSet1.getSetNum();
    }

    public static class UnionSet1{

        private Map<Node,Node> parentMap;
        private Map<Node,Integer> sizeMap;
        private int setNum;

        public UnionSet1() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            setNum = 0;
        }
        public void add(Node node){
            parentMap.put(node,node);
            sizeMap.put(node,1);
            setNum++;
        }


        public int getSetNum(){
            return setNum;
        }

        public Node find(Node cur){
            Node parent = parentMap.get(cur);
            Stack<Node> stack = new Stack<>();
            while (parent != cur){
                stack.push(cur);
                cur = parent;
                parent = parentMap.get(parent);
            }
            while (!stack.isEmpty()){
                parentMap.put(stack.pop(),parent);
            }
            return parent;
        }

        public void union(Node node1,Node node2){
            Node head1 = find(node1);
            Node head2 = find(node2);
            if (head1 == head2){
                return;
            }

            Node bigHead = sizeMap.get(head1) >= sizeMap.get(head2) ? head1 : head2;
            Node smallHead = bigHead == head1 ? head2 : head1;

            parentMap.put(smallHead,bigHead);
            sizeMap.put(bigHead, sizeMap.get(bigHead) + sizeMap.get(smallHead));
            sizeMap.remove(smallHead);
            setNum--;
        }
    }

    private static class Node{}

    //************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/8/10 10:06
     * @description 题目2
     * @param M
     * @return int
     **/
    public static int qs2_process1(char[][] M){
        if (M == null || M.length == 0){
            return 0;
        }

        int rows = M.length;
        int cows = M[0].length;

//        int[] arr = new int[rows * cows];
        UnionSet2 unionSet2 = new UnionSet2(rows * cows);

        for (int i = 0; i < M.length; i++){
            if (M[i][0] == '1'){
                unionSet2.add(i * cows + 0);
                if (i >0 && M[i-1][0] == '1'){
                    unionSet2.union(i * cows + 0, (i-1) * cows + 0);
                }
            }
        }
        for (int j = 0; j < M[0].length; j++){
            if (M[0][j] == '1'){
                unionSet2.add(0 * cows + j);
                if ( j > 0 && M[0][j-1] == '1'){
                    unionSet2.union(0 * cows + j,0 * cows + (j - 1));
                }
            }
        }

        for (int i = 1; i < M.length; i++){
            for (int j = 1; j < M[i].length; j++){
                if (M[i][j] == '1'){
                    unionSet2.add(i * cows + j);
                    if (M[i-1][j] == '1'){
                        unionSet2.union((i-1)*cows+j,i*cows+j);
                    }
                    if (M[i][j-1] == '1'){
                        unionSet2.union(i*cows+(j-1),i*cows+j);
                    }
                }
            }
        }
        return unionSet2.getSetNum();
    }



    public static class UnionSet2{

        int[] parentArr;
        int[] sizeArr;
        int[] help;
        int setNum;

        public UnionSet2() {
        }

        public UnionSet2(int length) {
            parentArr = new int[length];
            sizeArr = new int[length];
            help = new int[length];
            setNum = 0;
        }

        public void add(int index){
            parentArr[index] = index;
            sizeArr[index] = 1;
            setNum++;
        }

        public int find(int index){
            int hLentgh = 0;
            while (index != parentArr[index]){
                help[hLentgh++] = index;
                index = parentArr[index];
            }
            for (hLentgh--; hLentgh >= 0; hLentgh--){
                parentArr[hLentgh] = index;
            }
            return index;
        }

        public void union(int index1,int index2){
            int head1 = find(index1);
            int head2 = find(index2);

            if (head1 == head2){
                return;
            }

            int size1 = sizeArr[head1];
            int size2 = sizeArr[head2];

            int bigHead = size1 >= size2 ? head1 : head2;
            int smallHead = head1 == bigHead ? head2 : head1;

            parentArr[smallHead] = bigHead;
            sizeArr[bigHead] += sizeArr[smallHead];
            setNum--;
        }

        public int getSetNum(){
            return setNum;
        }
    }



    // 为了测试
    public static void main(String[] args) {
        int row = 0;
        int col = 0;
        char[][] board1 = null;
        char[][] board2 = null;
        char[][] board3 = null;
        long start = 0;
        long end = 0;

        row = 1000;
        col = 1000;
        board1 = generateRandomMatrix(row, col);
        board2 = copy(board1);
        board3 = copy(board1);

        System.out.println("感染方法、并查集(map实现)、并查集(数组实现)的运行结果和运行时间");
        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);

//        start = System.currentTimeMillis();
//        System.out.println("感染方法的运行结果: " + numIslands3(board1));
//        end = System.currentTimeMillis();
//        System.out.println("感染方法的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        int res1 = qs1_process1(board2);
//        System.out.println("并查集(map实现)的运行结果: " + res1);
        end = System.currentTimeMillis();
//        System.out.println("并查集(map实现)的运行时间: " + (end - start) + " ms");

        start = System.currentTimeMillis();
        int res2 = qs2_process1(board3);
//        System.out.println("并查集(数组实现)的运行结果: " + res2);
        end = System.currentTimeMillis();
//        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

        if (res1 != res2){
            System.out.println("oops");
        }

        System.out.println();

//        row = 10000;
//        col = 10000;
//        board1 = generateRandomMatrix(row, col);
//        board3 = copy(board1);
//        System.out.println("感染方法、并查集(数组实现)的运行结果和运行时间");
//        System.out.println("随机生成的二维矩阵规模 : " + row + " * " + col);
//
//        start = System.currentTimeMillis();
//        System.out.println("感染方法的运行结果: " + numIslands3(board1));
//        end = System.currentTimeMillis();
//        System.out.println("感染方法的运行时间: " + (end - start) + " ms");
//
//        start = System.currentTimeMillis();
//        System.out.println("并查集(数组实现)的运行结果: " + numIslands2(board3));
//        end = System.currentTimeMillis();
//        System.out.println("并查集(数组实现)的运行时间: " + (end - start) + " ms");

    }



    // 为了测试
    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return board;
    }

    // 为了测试
    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = board[i][j];
            }
        }
        return ans;
    }

}
