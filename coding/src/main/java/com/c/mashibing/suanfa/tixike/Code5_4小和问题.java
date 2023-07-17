package com.c.mashibing.suanfa.tixike;


/*
 =====================================
 题目1，小和问题，
 数组所有元素左侧比自己小的数累加得到和sum(i)，将sum(0)-sum(maxIndex)累加返回
 如，数组   [6 4 2 1 6 7 ]
    累加得到[0 0 0 0 6 18]，最终返回6+18
 ==================================
 题目2，查出逆序对。
 有一个数组从左到右降序的一对数为逆序对，
 如[3 1 0 4 3 1]中3-1，3-0，1-0，4-3，4-1等为逆序对。
 查找出一个数组中有多少个逆序对
 ==========================================
 */
public class Code5_4小和问题 {


    /*
     * @author gengzhihao
     * @date 2023/5/25 9:09
     * @description 题目1。本质上是计算arr[i]*N(右侧大于自己的数为n个)
     * @param arr
     **/
    public static int qs1_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }

        int L = 0, M = 0, R = 0;
        int N = arr.length;
        int step = 1;
        int ans = 0;

        while (step < N){
            //每次step翻倍后重置L的位置为0
            L = 0;

            while (L < N){
                //右组的左边界如果超过了最大索引N-1，即L+step > N-1 <=> L > N-1-step
                if (L > N-1-step){
                    break;
                }
                M = L + step - 1;
                //通过上一个if到了这里，右组肯定是有的，但是右边界有可能为最大索引
                //即需要在N-1和M+step中挑出最小值
                //为防止M+step溢出，替换为M + Math.min(N-1-M,step)
                R = M+Math.min(N-1-M,step);
                ans += merger1(arr,L,M,R);
//                LogarithmicUtil.printArray(arr);
                //下一次内层循环开始前，更新下一个L的位置
                L = R + 1;
            }

            //防止step溢出
            if (step > N>>1){
                break;
            }
            step <<= 1;

        }
        return ans;
    }

    //合并左组和右组
    private static int merger1(int[] arr, int L, int M, int R) {
        int sum = 0;
        int p1 = L;
        int p2 = M+1;
        int[] help = new int[R-L+1];
        int i = 0;

        while (p1 <= M && p2 <= R){
            sum += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M){
            help[i++] = arr[p1++];
        }

        while (p2 <= R){
            help[i++] = arr[p2++];
        }

        for (i = 0; i<help.length; i++){
            arr[L+i] = help[i];
        }
        return sum;
    }
//=================================================================================

    /*
     * @author gengzhihao
     * @date 2023/5/25 10:48
     * @description 题目2。其实就是求数i(左组中的某一个数)右侧有多少比i小的(右组中的)。
     * 因为排序好的数是升序数组，所以内循环中的检测需要降序进行
     * @param arr
     * @return int
     **/
    public static int qs2_process1(int[] arr){
        if (arr == null || arr.length < 2){
            return 0;
        }

        int L = 0, M = 0, R = 0;
        int N = arr.length;
        int step = 1;
        int ans = 0;

        while (step < N){
            //每次step翻倍后重置L的位置为0
            L = 0;

            while (L < N){
                //右组的左边界如果超过了最大索引N-1，即L+step > N-1 <=> L > N-1-step
                if (L > N-1-step){
                    break;
                }
                M = L + step - 1;
                //通过上一个if到了这里，右组肯定是有的，但是右边界有可能为最大索引
                //即需要在N-1和M+step中挑出最小值
                //为防止M+step溢出，替换为M + Math.min(N-1-M,step)
                R = M+Math.min(N-1-M,step);
                ans += merger2(arr,L,M,R);
//                LogarithmicUtil.printArray(arr);
                //下一次内层循环开始前，更新下一个L的位置
                L = R + 1;
            }

            //防止step溢出
            if (step > N>>1){
                break;
            }
            step <<= 1;

        }
        return ans;
    }

    private static int merger2(int[] arr, int L, int M, int R) {
        int sum = 0;
        int p1 = M;
        int p2 = R;
        int[] help = new int[R-L+1];
        int i = R-L;

        while (p1 >= L && p2 >= M+1){
            //计算左组的arr[p1]在右组中有多少比它小的数
            sum += arr[p1] > arr[p2] ? p2 - M : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }

        while (p1 >= L){
            help[i--] = arr[p1--];
        }

        while (p2 >= M+1){
            help[i--] = arr[p2--];
        }

        for (i = 0; i<help.length; i++){
            arr[L+i] = help[i];
        }
        return sum;
    }

//=================================================================================
    public static void main(String[] args) {
        int testTime = 100000;
        int maxLength = 1000;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++){
            int[] arr1 = randomArray(maxLength,maxValue);
//            int[] arr1 = {3,2,1,0};
            int[] arr2 = copyArray(arr1);
            int[] temp = copyArray(arr1);
//            LogarithmicUtil.printArray(temp);
            int ans1 = qs2_process1(arr1);
            int ans2 = getReverseCoupleNum(arr2);
            if (ans1 != ans2){
                System.out.println("错误啦");
                System.out.print("原数组逆序对：");
                System.out.println(ans2);
                System.out.print("错误统计为：");
                System.out.println(ans1);
                System.out.println();
            }
        }
    }

    private static boolean equalOrNot(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null){
            return true;
        }
        if (arr1 == null || arr2 == null){
            return false;
        }
        if (arr1.length != arr2.length){
            return false;
        }
        for (int i = 0; i<arr1.length; i++){
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    private static int[] copyArray(int[] arr1) {
        int[] result = new int[arr1.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        return result;
    }

    private static int[] randomArray(int maxLength, int maxValue) {
        int length = (int)(Math.random() * maxLength);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++){
            arr[i] = (int)(Math.random() * maxValue);
        }
//        LogarithmicUtil.printArray(arr);
        return arr;
    }

    //通过排序得到小和
    private static int getSmallSum(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int ans = 0;

        for (int i = 0; i < arr.length; i++){
            int minIndex = 0;
            for (int j = 0; j < i; j++){
                if (arr[j] < arr[i]){
                    ans += arr[j];
                }
            }
        }
        return ans;
    }

    //得到逆序对的个数
    private static int getReverseCoupleNum(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int ans = 0;

        for (int i = 0; i < arr.length; i++){
            int minIndex = 0;
            for (int j = arr.length-1; j > i; j--){
                if (arr[i] > arr[j]){
                    ans++;
                }
            }
        }
        return ans;
    }

}
