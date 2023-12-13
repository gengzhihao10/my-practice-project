package com.c.mashibing.suanfa.tixike;

/*
 题目1，给定一个数组arr，用户希望你实现如下三个方法
 1）void add(int L, int R, int V) :  让数组arr[L…R]上每个数都加上V
 2）void update(int L, int R, int V) :  让数组arr[L…R]上每个数都变成V
 3）int sum(int L, int R) :让返回arr[L…R]这个范围整体的累加和
 怎么让这三个方法，时间复杂度都是O(logN)

 */
public class Code32_3线段树code实现数组arr时间复杂度都是LogN {


    public static class SegmentTree {
        // arr[]为原序列的信息从0开始，但在arr里是从1开始的
        // sum[]模拟线段树维护区间和
        // lazy[]为累加和懒惰标记
        // change[]为更新的值
        // update[]为更新慵懒标记
        private int MAXN;
        private int[] arr;
        private int[] sum;
        private int[] add;
        private int[] update;
        private boolean[] updateOrNot;


        public SegmentTree(int[] origin){
            MAXN = origin.length + 1;
            arr = new int[MAXN];
            for (int i = 0; i < origin.length; i++){
                arr[i + 1] = origin[i];
            }
            sum = new int[MAXN << 2];
            add = new int[MAXN << 2];
            update = new int[MAXN << 2];
            updateOrNot = new boolean[MAXN << 2];
        }

        public void build(int l, int r, int root){
            if (l == r){
                sum[root] = arr[l];
                return;
            }
            int mid = (l + r) / 2;
            build(l,mid,root << 1);
            build(mid + 1,r,root << 1 | 1);
            sum[root] = sum[root << 1] + sum[root << 1 | 1];
        }


        //将lazy信息向下推
        private void pushDown(int root, int ln, int rn) {

            //如果有root的update信息，update的lazy信息向下推
            if (updateOrNot[root]){
                sum[root << 1] = update[root] * ln;
                add[root << 1] = 0;
                updateOrNot[root << 1] = true;
                update[root << 1] = update[root];

                sum[root << 1 | 1] = update[root] * rn;
                add[root << 1 | 1] = 0;
                updateOrNot[root << 1 | 1] = true;
                update[root << 1 | 1] = update[root];

                updateOrNot[root] = false;
            }
            //如果有root的add信息，add的lazy信息向下推
            if (add[root] != 0){
                sum[root << 1] += add[root] * ln;
                add[root << 1] += add[root];

                sum[root << 1 | 1] +=  add[root] * rn;
                add[root << 1 | 1] +=  add[root];

                add[root] = 0;

            }
        }


        public void add(int L, int R, int C, int l, int r, int root){
            if (L <= l && R >= r){
                sum[root] = sum[root] + C * (r - l + 1);
                add[root] = add[root] + C;
                return;
            }

            int mid = (l + r) / 2;

            pushDown(root,mid - l + 1,r - mid);

            if (L <= mid){
                add(L,R,C,l,mid,root << 1);
            }
            if (mid + 1 <= R){
                add(L,R,C,mid +1,r,root << 1 | 1);
            }
            sum[root] = sum[root << 1] + sum[root << 1 | 1];
        }


        public void update(int L, int R, int C, int l, int r, int root){
            if (L <= l && R >= r){
                sum[root] = C * (r - l + 1);
                updateOrNot[root] = true;
                update[root] = C;
                add[root] = 0;
                return;
            }

            int mid = (l + r) / 2;
            pushDown(root,mid - l + 1,r - mid);

            if (L <= mid){
                update(L,R,C,l,mid,root << 1);
            }
            if (mid + 1 <= R){
                update(L,R,C,mid +1,r,root << 1 | 1);
            }
            sum[root] = sum[root << 1] + sum[root << 1 | 1];
        }

        public long query(int L, int R, int l, int r, int root){
            if (L <= l && R >= r){
                return sum[root];
            }

            int mid = (l + r) / 2;
            pushDown(root,mid - l + 1,r - mid);

            long ans = 0L;
            if (L <= mid){
                ans += query(L,R,l,mid,root << 1);
            }
            if (mid + 1 <= R){
                ans += query(L,R,mid +1,r,root << 1 | 1);
            }
            return ans;
        }
    }



    public static class Right {
        public int[] arr;

        public Right(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static int[] genarateRandomArray(int len, int max) {
        int size = (int) (Math.random() * len) + 1;
        int[] origin = new int[size];
        for (int i = 0; i < size; i++) {
            origin[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
        }
        return origin;
    }

    public static boolean test() {
        int len = 100;
        int max = 1000;
        int testTimes = 5000;
        int addOrUpdateTimes = 1000;
        int queryTimes = 500;
        for (int i = 0; i < testTimes; i++) {
            int[] origin = genarateRandomArray(len, max);
            SegmentTree seg = new SegmentTree(origin);
            int S = 1;
            int N = origin.length;
            int root = 1;
            seg.build(S, N, root);
            Right rig = new Right(origin);
            for (int j = 0; j < addOrUpdateTimes; j++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                int C = (int) (Math.random() * max) - (int) (Math.random() * max);
                if (Math.random() < 0.5) {
                    seg.add(L, R, C, S, N, root);
                    rig.add(L, R, C);
                } else {
                    seg.update(L, R, C, S, N, root);
                    rig.update(L, R, C);
                }
            }
            for (int k = 0; k < queryTimes; k++) {
                int num1 = (int) (Math.random() * N) + 1;
                int num2 = (int) (Math.random() * N) + 1;
                int L = Math.min(num1, num2);
                int R = Math.max(num1, num2);
                long ans1 = seg.query(L, R, S, N, root);
                long ans2 = rig.query(L, R);
                if (ans1 != ans2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] origin = { 2, 1, 1, 2, 3, 4, 5 };
        SegmentTree seg = new SegmentTree(origin);
        int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
        int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
        int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
        int L = 2; // 操作区间的开始位置 -> 可变
        int R = 5; // 操作区间的结束位置 -> 可变
        int C = 4; // 要加的数字或者要更新的数字 -> 可变
        // 区间生成，必须在[S,N]整个范围上build
        seg.build(S, N, root);
        // 区间修改，可以改变L、R和C的值，其他值不可改变
        seg.add(L, R, C, S, N, root);
        // 区间更新，可以改变L、R和C的值，其他值不可改变
        seg.update(L, R, C, S, N, root);
        // 区间查询，可以改变L和R的值，其他值不可改变
        long sum = seg.query(L, R, S, N, root);
        System.out.println(sum);

        System.out.println("对数器测试开始...");
        System.out.println("测试结果 : " + (test() ? "通过" : "未通过"));

    }
}
