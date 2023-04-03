package com.c.mashibing.suanfa.xinshouke;


/**
 * @author gengzhihao
 * @date 2023/2/6 21:02
 * @description 对int数组进行排序，从小到大排序
**/

public class Code1_5数组排序 {


    /**
     * @param
     * @return
     * @author gengzhihao
     * @date 2023/2/6 15:03
     * @description 选择排序
     **/
    public static void selectSort(int[] array) {
        //边界条件
        if (array == null || array.length < 2) {
            return;
        }
        //选择排序
        int size = array.length;
        for (int i = 0; i < size; i++) {
            int minValueIndex = i;
            for (int j = i; j < size; j++) {
                minValueIndex = array[minValueIndex] > array[j] ? j : minValueIndex;
            }

            swapValue(array, i, minValueIndex);
        }

    }


    /**
     * @param
     * @return
     * @author gengzhihao
     * @date 2023/2/6 16:51
     * @description 冒泡排序
     **/
    public static void bublleSort(int[] array) {
        //边界条件
        if (array == null || array.length < 2) {
            return;
        }
        //选择排序
        int size = array.length;
        for (int i = size - 1; i >= 0; i--) {
            int bigInt = i;
            for (int j = 1; j <= i; j++) {
                if (array[j - 1] > array[j]) {
                    swapValue(array, j - 1, j);
                }
            }
        }

    }


    /**
     * @return
     * @author gengzhihao
     * @date 2023/2/6 20:58
     * @description 插入排序
     **/
    public static void insertSort(int[] array) {
        //边界条件
        if (array == null || array.length < 2) {
            return;
        }
        //选择排序
        int size = array.length;
        for (int i = 1; i < size; i++) {
            int curValueIndex = i;
            while (array[curValueIndex - 1] > array[curValueIndex]) {
                swapValue(array, curValueIndex - 1, curValueIndex);
                curValueIndex--;
            }
        }

    }

    private static void swapValue(int[] array, int i, int j) {
        int middle = array[i];
        array[i] = array[j];
        array[j] = middle;
        return;
    }

    public static void printArray(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 3, 7, 4, 8};
        printArray(array);
        insertSort(array);
        printArray(array);

    }

}
