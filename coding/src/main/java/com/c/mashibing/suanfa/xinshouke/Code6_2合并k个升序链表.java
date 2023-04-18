package com.c.mashibing.suanfa.xinshouke;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentMap;

public class Code6_2合并k个升序链表 {


    public static class ListNode{
        public int val;
        public ListNode next;
    }

    public static class ListNodeComparator implements Comparator<ListNode>{
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val-o2.val;
        }
    }

    public static ListNode mergeK(ListNode[] lists){
        //边界条件，数组指向null时
        if (lists == null){
            return null;
        }

        //借助优先级队列完成算法
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (int i = 0; i<lists.length; i++){
            if (lists[i] != null){
                heap.add(lists[i]);
            }
        }
        //处理边界条件，为空数组时
        if (heap.isEmpty()){
            return null;
        }

        //预处理
        //首先弹出最小的一个节点，作为头节点和pre节点
        ListNode head = heap.poll();
        ListNode pre = head;
        //pre的next不为null就添加进优先级队列
        if (pre.next != null){
            heap.add(pre.next);
        }

        while (!heap.isEmpty()){
            //poll出来的肯定是最小的
            ListNode cur = heap.poll();
            //上一个节点的next指针指向弹出来的节点
            pre.next = cur;
            //pre移动过去
            pre = cur;
            //如果弹出来的节点的next不为空，继续优先级队列里加
            if (cur.next != null){
                heap.add(cur.next);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {};
        System.out.println(arr == null);
    }


}
