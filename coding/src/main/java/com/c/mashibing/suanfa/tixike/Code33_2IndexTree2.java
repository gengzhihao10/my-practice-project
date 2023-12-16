package com.c.mashibing.suanfa.tixike;

/*
 题目1，实现index tree
 */
public class Code33_2IndexTree2 {

    public static class IndexTree{

        private int[] tree;
        private int N;


        public IndexTree(int size){
            N = size;
            tree = new int[N + 1];
        }


        public int sum(int index){
            int sum = 0;
            while (index > 0){
                sum += tree[index];
                index -= -index & index;
            }
            return sum;
        }

        //将index二进制最右侧的1剥离出来，加上index得到新的index，是被影响到的tree的所有索引
        public void add(int index, int val){
            while (index <= N){
                tree[index] += val;
                index += -index & index;
            }
        }
    }

    public static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }

    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }

}
