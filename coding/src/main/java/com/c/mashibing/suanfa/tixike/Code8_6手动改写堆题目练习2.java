package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
todo
 题目1，实现一个加强堆
 成员变量，
 private ArrayList<T> heap;
 private HashMap<T, Integer> indexMap;
 private int heapSize;
 private Comparator<? super T> comp;
 要求实现的方法，
 public HeapGreater(Comparator<? super T> c)
 public boolean isEmpty()
 public int size()
 public boolean contains(T obj)
 public T peek()
 public void push(T obj)
 public T pop()
 public void remove(T obj)
 public void resign(T obj)
 public List<T> getAllElements()
 private void heapInsert(int index)
 private void heapify(int index)
 private void swap(int i, int j)

 */
public class Code8_6手动改写堆题目练习2 {

    /*
     * @author gengzhihao
     * @date 2023/6/23 12:19
     * @description 题目1
     * @param null
     **/
    public static class EnhancedHeap<T> {

        public EnhancedHeap(Comparator<T> comparator) {
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
        private Map<T, Integer> indexMap = new HashMap<>();

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        //因为没有反向索引表，所以复杂度做不到Logn，只能做到n
        public boolean contains(T element) {
            if (indexMap.isEmpty()){
                return false;
            }
            return indexMap.containsKey(element);
        }

        public T peek() {
            return list.get(0);
        }

        public T poll() {
            T result = list.get(0);
            swap(list, 0, --heapSize);
            indexMap.remove(result);
            list.remove(heapSize);
            heapify(list, 0, heapSize);
            return result;
        }

        public void offer(T element) {
            list.add(element);
            indexMap.put(element,heapSize);
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

        public void remove(T obj){
            if (!indexMap.containsKey(obj)){
                return;
            }
            int index = indexMap.get(obj);
            swap(list,index,--heapSize);
            list.remove(heapSize);
            indexMap.remove(obj);
            resign(list.get(index));
        }


        public void resign(T obj){
            heapify(list,indexMap.get(obj),heapSize);
            heapInsert(list,indexMap.get(obj),comparator);
        }

    }
}
