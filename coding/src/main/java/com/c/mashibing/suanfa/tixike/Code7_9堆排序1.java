package com.c.mashibing.suanfa.tixike;

import com.common.utils.LogarithmicUtil;
import org.omg.CORBA.Object;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 前提，以数组为实际内存形式的完全二叉树，任意一个下标为i位置，其左右孩子下标为2*i+1, 2*i+2，父节点为i-1/2
 题目1，有一个现成的数组，对其进行堆排序。最主要的是要实现对大根堆向上调整heapinsert和向下调整heapify两个操作
 题目2，实现一个堆
 题目3，有一个几乎有序的数组，几乎有序指的是从无序到有序，最多移动k个数，且k远小于数组长度N，对齐进行排序
 使用堆排序，可以做到复杂度为o(N * log(k+1))
 */
public class Code7_9堆排序1 {

    /*
     * @author gengzhihao
     * @date 2023/6/19 10:30
     * @description 题目1
     * 对于任意一个大根堆的数组中下标为i的数，左孩子下标为2*i，右孩子下标为2*i+1，父节点为i/2
     * @param arr
     **/
    public static void qs1_process1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //1,将数组构造为大根堆
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        //2,调整成有序数组
        int heapSize = arr.length;
        for (int i = arr.length; i > 0; i--) {
            swap(arr[0], arr[i - 1]);
            heapify(arr, 0, --heapSize);
        }
    }

    private static void heapInsert(int[] arr, int cur) {
        while (cur != 0) {
            int parent = (cur - 1) / 2;
            if (arr[parent] < arr[cur]) {
                swap(arr[parent], arr[cur]);
                cur = parent;
            } else {
                break;
            }
        }
    }

    public static void heapify(int[] arr, int cur, int heapSize) {
        while (cur < heapSize) {
            //左孩子大于最大下标，右孩子也会大于最大下标，此时不存在子节点
            if (2 * cur + 1 >= heapSize) {
                break;
            }
            //到了这里，左孩子一定在数组中没越界，
            int biggerChild = selectBiggerChild(arr, cur, heapSize);
            if (arr[cur] < arr[biggerChild]) {
                swap(arr[cur], arr[biggerChild]);
                cur = biggerChild;
            } else {
                break;
            }
        }
    }

    //返回较大的孩子节点
    private static int selectBiggerChild(int[] arr, int cur, int heapSize) {
        // 如果右孩子越界，那么只有左孩子应该成为较大的孩子；
        if (2 * cur + 2 >= heapSize) {
            return 2 * cur + 1;
        }
        //如果右孩子没越界，那么在左右孩子中选一个较大的子节点对应的下标返回
        if (arr[2 * cur + 1] > arr[2 * cur + 2]) {
            return 2 * cur + 1;
        }
        return 2 * cur + 2;
    }

    private static void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }


    //题目1
//    public static void main(String[] args) {
//        int testTime = 100000;
//        int maxLength = 100;
//        int maxValue = 1000;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = randomArray(maxLength, maxValue);
////            int[] arr1 = {6,2,7};
//            int[] arr2 = copyArray(arr1);
//            int[] temp = copyArray(arr1);
////            LogarithmicUtil.printArray(temp);
//            qs1_process1(arr1);
//            bubbleSort(arr2);
//            if (!equalOrNot(arr1, arr2)) {
//                System.out.println("错误啦");
//                System.out.print("原数组：");
//                LogarithmicUtil.printArray(temp);
//                System.out.print("错误排序后：");
//                LogarithmicUtil.printArray(arr1);
//                System.out.println();
//            }
//        }
//    }

    private static boolean equalOrNot(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
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

    private static int[] copyArray(int[] arr1) {
        int[] result = new int[arr1.length];
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        return result;
    }

    private static int[] randomArray(int maxLength, int maxValue) {
        int length = (int) (Math.random() * maxLength);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr[j], arr[j + 1]);
                }
            }

        }
    }

    //*****************************************************************************************
    //题目2
    public static class MyPriorityQueue<T> {

        public MyPriorityQueue(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        /*
        比较器，返回负数的时候，第一个参数排在前面
        返回正数的时候，第二个参数排在前面
        返回0的时候，谁在前面无所谓
         */
        private Comparator<T> comparator;
        private List<T> list = new ArrayList<>();
        private int heapSize = 0;

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        //因为没有反向索引表，所以复杂度做不到Logn，只能做到n
        public boolean contains(T element) {
            for (int i = 0; i < heapSize; i++) {
                if (list.get(i) == element) {
                    return true;
                }
            }
            return false;
        }

        public T peek() {
            return list.get(0);
        }

        public T poll() {
            T result = list.get(0);
            swap(list, 0, --heapSize);
            list.remove(heapSize);
            heapify(list, 0, heapSize);
            return result;
        }

        public void offer(T element) {
            list.add(element);
            heapInsert(list, heapSize++, comparator);
        }

        private void swap(List<T> list, int i, int j) {
            T temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }


        public void heapify(List<T> list, int cur, int heapSize) {
            while (cur < heapSize) {
                //左孩子大于最大下标，右孩子也会大于最大下标，此时不存在子节点
                if (2 * cur + 1 >= heapSize) {
                    break;
                }
                //到了这里，左孩子一定在数组中没越界，
                int child = selectChild(list, cur, heapSize, comparator);
                if (comparator.compare(list.get(cur), list.get(child)) > 0) {
                    swap(list, cur, child);
                    cur = child;
                } else {
                    break;
                }
            }
        }

        private int selectChild(List<T> list, int cur, int heapSize, Comparator<T> comparator) {
            // 如果右孩子越界，那么只有左孩子应该成为较大的孩子；
            if (2 * cur + 2 >= heapSize) {
                return 2 * cur + 1;
            }
            //小于0表示要让左边参数放在前面
            if (comparator.compare(list.get(2 * cur + 1), list.get(2 * cur + 2)) < 0) {
                return 2 * cur + 1;
            }
            return 2 * cur + 2;
        }

        private void heapInsert(List<T> list, int cur, Comparator<T> comparator) {
            while (cur != 0) {
                int parent = (cur - 1) / 2;
                if (comparator.compare(list.get(parent), list.get(cur)) > 0) {
                    swap(list, parent, cur);
                    cur = parent;
                } else {
                    break;
                }
            }
        }

    }

    public static class Student {
        int age;
        String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "  " + age;
        }
    }

//    //题目2的测试
//    public static void main(String[] args) {
//        MyPriorityQueue<Student> heap = new MyPriorityQueue<>(new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.age - o2.age;
//            }
//        });
//        Student s1 = new Student(11, "张三");
//        Student s2 = new Student(14, "李四");
//        Student s3 = new Student(39, "贾斯汀.比伯");
//        Student s4 = new Student(7, "汤姆");
//        Student s5 = new Student(45, "李建国");
//        Student s6 = new Student(23, "罗辑");
//        Student s7 = new Student(19, "程心");
//        Student s8 = new Student(34, "章北海");
//
//        heap.offer(s1);
//        heap.offer(s2);
//        heap.offer(s3);
//        heap.offer(s4);
//        heap.offer(s5);
//        System.out.println(heap.peek());
//        System.out.println(heap.poll());
//        ;
//        System.out.println(heap.poll());
//        ;
//        System.out.println(heap.poll());
//        ;
//        System.out.println(heap.poll());
//        ;
//
//    }


    //***************************************************************************
    /*
     * @author gengzhihao
     * @date 2023/6/22 8:38
     * @description 题目3
     * @param arr
     * @param k
     **/
    public static void qs3_process1(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }

        if (k == 0 ){
            return;
        }

        int start = 0;
        for (int i = 0; i < k+1; i++){
            heapInsert(arr,i,0);
        }

        for (int i = k+1; i < arr.length; i++){
            start++;
            heapInsert(arr,i,start);
        }

    }

    private static void heapInsert(int[] arr, int cur, int start) {
        while (cur >= start) {
            int parent = (cur - 1) / 2;
            if (arr[parent] < arr[cur]) {
                swap(arr[parent], arr[cur]);
                cur = parent;
            } else {
                break;
            }
        }
    }


        public static void main(String[] args) {
        int testTime = 100000;
        int maxLength = 100;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = randomArray(maxLength, maxValue);
//            int[] arr1 = {6,2,7};
            int[] arr2 = copyArray(arr1);
            int[] temp = copyArray(arr1);
//            LogarithmicUtil.printArray(temp);
            int k = (int)(Math.random()*arr1.length/2)+1;
//            System.out.println("k为："+k);
            qs3_process1(arr1,k);
            bubbleSort(arr2);
            if (!equalOrNot(arr1, arr2)) {
                System.out.println("错误啦");
                System.out.print("原数组：");
                LogarithmicUtil.printArray(temp);
                System.out.print("错误排序后：");
                LogarithmicUtil.printArray(arr1);
                System.out.println();
            }
        }
    }
}