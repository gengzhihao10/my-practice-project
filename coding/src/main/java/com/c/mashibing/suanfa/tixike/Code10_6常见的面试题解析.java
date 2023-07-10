package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，输入一个链表的头节点和一个参数V，将链表调整为：
 小于V的节点->等于V的节点->大于V的节点。可以使用额外空间
 题目2，题目1，但是要使用有限几个变量
 */
public class Code10_6常见的面试题解析 {

    //链表节点
    static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/8 14:14
     * @description 题目1
     * @param head
     **/
    public static Node qs1_process1(Node head,int v){
        if (head == null || head.next == null){
            return head;
        }

        Queue<Node> less = new LinkedList<>();
        Queue<Node> equal = new LinkedList<>();
        Queue<Node> more = new LinkedList<>();

        Node cur = head, next = null;
        while (cur != null){
            next = cur.next;
            if (cur.value < v){
                cur.next = null;
                less.offer(cur);
            }
            else if (cur.value == v){
                cur.next = null;
                equal.offer(cur);
            }
            else if (cur.value > v){
                cur.next = null;
                more.offer(cur);
            }
            cur = next;
        }
        //拿到head
        if (!less.isEmpty()){
            head = less.poll();
        }
        else if (!equal.isEmpty()){
            head = equal.poll();
        }
        else if (!more.isEmpty()){
            head = more.poll();
        }
        //重建链表
        cur = head;
        while (!less.isEmpty()){
            cur.next = less.poll();
            cur = cur.next;
        }
        while (!equal.isEmpty()){
            cur.next = equal.poll();
            cur = cur.next;
        }
        while (!more.isEmpty()){
            cur.next = more.poll();
            cur = cur.next;
        }
        return head;
    }


    //*****************************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/7/8 14:19
     * @description 题目2
     * @param head
     **/
    public static Node qs2_process1(Node head, int v){
        if (head == null || head.next == null){
            return head;
        }

        Node lessL = null, lessR = null, equalL = null,equalR = null, moreL = null,moreR = null;
        Node cur = head,next = null;
        while (cur != null){
            next = cur.next;

            if (cur.value < v){
                if (lessL == null){
                    lessL = cur;
                    lessR = cur;
                }else {
                    lessR.next = cur;
                    lessR = cur;
                }
                cur.next = null;
            }

            else if (cur.value == v) {
                if (equalL == null){
                    equalL = cur;
                    equalR = cur;
                }else {
                    equalR.next = cur;
                    equalR = cur;
                }
                cur.next = null;
            }

            else if (cur.value > v) {
                if (moreL == null){
                    moreL = cur;
                    moreR = cur;
                }else {
                    moreR.next = cur;
                    moreR = cur;
                }
                cur.next = null;
            }

            cur = next;
        }

        if (lessR != null){
            head = lessL;
            if (equalL != null){
                lessR.next = equalL;
                if (moreL != null){
                    equalR.next = moreL;
                }
            }else if (moreL != null){
                lessR.next = moreL;
            }
        }
        else {
            if (equalR != null){
                head = equalL;
                if (moreL != null){
                    equalR.next = moreL;
                }
            }else {
                head = moreL;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node head1 = new Node(3);
        head1.next = new Node(4);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(1);
        head1.next.next.next.next = new Node(6);
        head1.next.next.next.next.next = new Node(2);
        Node newHead = qs1_process1(head1,3);
        printLinkedList(newHead);
    }

    private static void printLinkedList(Node head){
        while (head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
}
