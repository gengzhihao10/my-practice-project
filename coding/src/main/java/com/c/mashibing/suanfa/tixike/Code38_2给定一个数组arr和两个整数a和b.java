package com.c.mashibing.suanfa.tixike;

import java.util.HashSet;

/*
 题目1，
 给定一个数组arr，和两个整数a和b（a<=b）
 求arr中有多少个子数组，累加和在[a,b]这个范围上
 返回达标的子数组数量

 */
public class Code38_2给定一个数组arr和两个整数a和b {

    /*
     * @author gengzhihao
     * @date 2024/1/15 11:46
     * @description 题目1
     * @param nums
     * @param lower
     * @param upper
     * @return int
     **/
    public static int countRangeSum2(int[] nums, int lower, int upper){
        if (nums == null || nums.length == 0 || lower > upper){
            return -1;
        }

        SizeBalancedTreeSet set = new SizeBalancedTreeSet();
        long sum = 0L;
        //1,题意 -> nums[i]结尾的子数组有多少个满足要求
        //2,nums[i]，假设nums[0]~nums[i]的和为sum -> 多少个前缀和满足 [sum-upper, sum-lower]
        //3,[sum-upper, sum-lower] -> lessThan(sum-lower+1) - lessThan(sum-upper)
        int ans = 0;
        set.add(0);
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            long a = set.lessKeySize(sum - lower + 1);
            long b = set.lessKeySize(sum - upper);
            ans += a-b;
            set.add(sum);
        }
        return ans;
    }


    public static class SBTNode{
        private long k;
        private SBTNode l;
        private SBTNode r;
        private int size;
        private int all;

        public SBTNode(long k){
            this.k = k;
            this.size = 1;
            this.all = 1;
        }
    }


    public static class SizeBalancedTreeSet{
        SBTNode root;
        HashSet<Long> set;

        public SizeBalancedTreeSet() {
            set = new HashSet<>();
        }

        private SBTNode rightRotate(SBTNode cur){
            int curEqualAll = cur.all - (cur.l == null ? 0 : cur.l.all) - (cur.r == null ? 0 : cur.r.all);
            SBTNode left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            left.all = cur.all;
            cur.all = (cur.l == null ? 0 : cur.l.all) + (cur.r == null ? 0 : cur.r.all) + curEqualAll;
            return left;
        }

        private SBTNode leftRotate(SBTNode cur){
            int curEqualAll = cur.all - (cur.l == null ? 0 : cur.l.all) - (cur.r == null ? 0 : cur.r.all);
            SBTNode right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            right.all = cur.all;
            cur.all = (cur.l == null ? 0 : cur.l.all) + (cur.r == null ? 0 : cur.r.all) + curEqualAll;
            return right;
        }

        private SBTNode maintain(SBTNode cur){
            if (cur == null){
                return null;
            }

            int left = cur.l != null ? cur.l.size : 0;
            int right = cur.r != null ? cur.r.size : 0;
            int leftLeft = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int rightRight = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            int leftRight = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightLeft = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            //ll型违规
            if (leftLeft > right){
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //rr型违规
            else if (rightRight > left){
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }
            //rl型违规
            else if (rightLeft > left){
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            //lr型违规
            else if (leftRight > right){
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }


        private SBTNode add(SBTNode cur, long key, boolean contains){
            if (cur == null){
                return new SBTNode(key);
            }else {
                cur.all++;
                if (!contains){
                    cur.size++;
                }
                if (key < cur.k){
                    cur.l = add(cur.l,key,contains);
                }else if (key > cur.k){
                    cur.r = add(cur.r,key,contains);
                }
            }
            return maintain(cur);
        }

        public void add(long sum){
            boolean contains = set.contains(sum);
            root = add(root,sum,contains);
            set.add(sum);
        }

        //只计算比Key小的数量，不包含相等
        public long lessKeySize(long key){
            SBTNode cur = root;
            int ans = 0;
            while (cur != null){
                if (key < cur.k){
                    cur = cur.l;
                }else if (key > cur.k){
                    ans += cur.all - (cur.r != null ? cur.r.all : 0);
                    cur = cur.r;
                }else {
                    return ans + (cur.l != null ? cur.l.all : 0);
                }
            }
            return ans;
        }
    }



    //*******************************************************************************


    public static int countRangeSum1(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            sums[i + 1] = sums[i] + nums[i];
        }
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private static int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower) {
                k++;
            }
            while (j < end && sums[j] - sums[i] <= upper) {
                j++;
            }
            while (t < end && sums[t] < sums[i]) {
                cache[r++] = sums[t++];
            }
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }

    //*********************************************************************************

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateArray(int len, int varible) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * varible);
        }
        return arr;
    }

    public static void main(String[] args) {
        int len = 200;
        int varible = 50;
        for (int i = 0; i < 10000; i++) {
            int[] test = generateArray(len, varible);
            int lower = (int) (Math.random() * varible) - (int) (Math.random() * varible);
            int upper = lower + (int) (Math.random() * varible);
            int ans1 = countRangeSum1(test, lower, upper);
            int ans2 = countRangeSum2(test, lower, upper);
            System.out.println("第" + i + "次循环");
            if (ans1 != ans2) {
                printArray(test);
                System.out.println(lower);
                System.out.println(upper);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }

    }
}
