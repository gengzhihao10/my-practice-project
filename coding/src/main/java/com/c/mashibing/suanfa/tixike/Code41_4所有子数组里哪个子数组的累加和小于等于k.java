package com.c.mashibing.suanfa.tixike;

/*
 题目1，
 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 给定一个整数值K
 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 返回其长度
 要求复杂度o(N)，而不是o(N*logN)

 */
public class Code41_4所有子数组里哪个子数组的累加和小于等于k {

    /*
     * @author gengzhihao
     * @date 2024/2/6 14:28
     * @description 题目1
     * @param arr
     * @param k
     * @return int
     **/
    public static int maxLengthAwesome(int[] arr, int k){
        if (arr == null || arr.length == 0){
            return 0;
        }

        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length-1] = arr[arr.length-1];
        minSumEnds[arr.length-1] = arr.length-1;
        for (int i = arr.length - 2; i >= 0; i--){
            //如果右侧累加和为负，则可以累加出更小的累加和，就累加上
            if (minSums[i + 1] < 0){
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i + 1];
            }else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }

        int ans = 0;
        //end表示没有扩进来的第一个数
        int end = 0;
        int sum = 0;
        for (int i = 0; i < arr.length - 1; i++){
            //能向右推就向右推
            while (end < arr.length && sum + minSums[end] <= k){
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            ans = Math.max(ans, end - i);
            //每次i++，都需要在sum中减去因为i++舍弃的arr[i-1]，前提是end没有等于i，即窗口内还有数
            if (end > i){
                sum -= arr[i];
            }
            //end没有扩，i逐渐增大，end和i数值一样。这种情况，因此end也需要加一，
            //但是不需要sum -= arr[i]，因为sum已经减到0（窗口内没有数了）
            else {
                end++;
            }
        }
        return ans;
    }


    //*****************************************************************************************

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
