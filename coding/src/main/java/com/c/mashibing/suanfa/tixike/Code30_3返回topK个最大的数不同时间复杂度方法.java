package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;

/*
 题目1，给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数（这k个数从大到小排列）
 不同时间复杂度三个方法：
 1）O(N*logN)
 2）O(N + K*logN)
 3）O(n + k*logk)

 */
public class Code30_3返回topK个最大的数不同时间复杂度方法 {


    /*
     * @author gengzhihao
     * @date 2023/12/4 10:19
     * @description 题目1 O(N*logN)
     * @param arr
     * @param k
     * @return int[]
     **/
    public static int[] maxTopK1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return null;
        }

        k = Math.min(arr.length, k);
        Arrays.sort(arr);
        int[] res = new int[k];

        for (int i = 0, j = arr.length - 1; i < k; i++, j--) {
            res[i] = arr[j];
        }
        return res;
    }


    //**************************************************************************************************


    /*
     * @author gengzhihao
     * @date 2023/12/4 10:20
     * @description O(N + K*logN)
     * @param arr
     * @param k
     * @return int[]
     **/
    public static int[] maxTopK2(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return null;
        }

        k = Math.min(arr.length, k);

        //1 调整成大根堆
        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--){
            heapify(arr,i,heapSize);
        }

        //2 弹出k个数到数组末尾
        swap(arr,0,--heapSize);
        int times = 0;
        while (heapSize > 0 && times < k){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
            times++;
        }

        //3 复制结果
        int[] res = new int[k];
        for (int i = 0, j = arr.length - 1; i < k; i++, j--){
            res[i] = arr[j];
        }

        return res;
    }

    //将索引index对应的数据向下调整
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize){
            int bigger = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            if (arr[index] >= arr[bigger]){
                break;
            }
            swap(arr,index,bigger);
            index = bigger;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


    //*********************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/12/4 10:20
     * @description O(n + k*logk)
     * @param arr
     * @param k
     * @return int[]
     **/
    public static int[] maxTopK3(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return null;
        }

        k = Math.min(arr.length, k);

        int num = getNum(arr,arr.length-k);
//        System.out.println("num: " + num);
//        System.out.println("k: "+k);
//        System.out.print("arr: ");
//        printArray(arr);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] > num){
                res[index++] = arr[i];
            }
        }
        while (index < k){
            res[index++] = num;
        }


        Arrays.sort(res);

//        System.out.print("交换前res: " );
//        printArray(res);
        int N = res.length;
        for (int i = 0; i <= (N-1)/2; i++){
            swap(res,i,res.length-1-i);
        }
//        System.out.print("res: " );
//        printArray(res);
        return res;
    }

    //返回arr中第k个大的数,数组从小到大排序
    private static int getNum(int[] arr, int index) {
        int L = 0, R = arr.length - 1;
        //获得[L,R]的随机整数
        int num;
        int[] p;

        while (L < R){
            num = arr[(int)(Math.random() * (R - L + 1)) + L];
            p = partition(arr,num,L,R);
            //partition后，arr被swap成了小于区，等于区，大于区。如果等于区找不到，就去对应区域找
            if (index < p[0]){
                R = p[0] - 1;
            }
            else if (index > p[1]){
                L = p[1] + 1;
            }
            else {
                return num;
            }
        }
        return arr[L];
    }

    //借用快排的逻辑，把数组分为小于Num区域，等于num区域，大于num区域。返回等于num区域左右边界
    private static int[] partition(int[] arr, int num, int L, int R) {
        int less = L - 1, cur = L, more = R + 1;
        while (cur < more ){
            if (arr[cur] < num){
                swap(arr,cur++,++less);
            }
            else if (arr[cur] > num){
                swap(arr,cur,--more);
            }
            else {
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }


    //********************************************************************************************

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            // [-? , +?]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 生成随机数组测试
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 10;
        boolean pass = true;
        System.out.println("测试开始，没有打印出错信息说明测试通过");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] arr = generateRandomArray(maxSize, maxValue);
//            int[] arr = {4, -6, -3, 5 };
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            int[] arr3 = copyArray(arr);

            int[] ans1 = maxTopK1(arr1, k);
            int[] ans2 = maxTopK2(arr2, k);
            int[] ans3 = maxTopK3(arr3, k);
            if (!isEqual(ans1, ans2) || !isEqual(ans1, ans3)) {
                pass = false;
                System.out.println("出错了！");
                printArray(arr);
                printArray(ans1);
                printArray(ans2);
                printArray(ans3);
                break;
            }
        }
        System.out.println("测试结束了，测试了" + testTime + "组，是否所有测试用例都通过？" + (pass ? "是" : "否"));
    }
}
