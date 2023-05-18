package com.c.mashibing.suanfa.tixike;


/**
 * @author gengzhihao
 * @date 2023/5/15 11:43
 * @description
 *  题目1，单链表的反转，要求，额外空间复杂度为O(1)，也就是实现不能借助容器
 *  题目2，双联表的反转。要求类似题目1
 *  题目3，删除单链表中指定节点
 *
**/

public class Code4_1单向链表和双向链表 {

    //单链表节点
    public static class LinkedNode{
        int value;
        LinkedNode next;

        public LinkedNode(int value) {
            this.value = value;
        }
    }

    //双链表节点
    public static class DoubleLinkedNode{
        int value;
        DoubleLinkedNode pre;
        DoubleLinkedNode next;

        public DoubleLinkedNode(int value) {
            this.value = value;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/5/17 9:35
     * @description 题目1
     * @param head
     * @return LinkedNode
     **/
    public static LinkedNode qs1_process1(LinkedNode head){

        if (head == null || head.next == null){
            return head;
        }

        LinkedNode pre = null;
        LinkedNode next = null;

        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    /*
     * @author gengzhihao
     * @date 2023/5/17 9:59
     * @description 题目2
     * @param head
     * @return DoubleLinkedNode
     **/
    public static DoubleLinkedNode qs2_process1(DoubleLinkedNode head){

        if (head == null || head.next == null){
            return head;
        }

        DoubleLinkedNode pre = null;
        DoubleLinkedNode next = null;

        while (head != null){
            next = head.next;

            head.next = pre;
            head.pre = next;

            pre = head;
            head = next;
        }
        return pre;
    }

    /*
     * @author gengzhihao
     * @date 2023/5/17 10:08
     * @description 题目3
     * @param head
     * @param num
     * @return LinkedNode
     **/
    public static LinkedNode qs3_process1(LinkedNode head, int num){

        //开头就有num的情况
        while (head != null){
            if (head.value != num){
                break;
            }
            head = head.next;
        }

        LinkedNode cur = head;
        LinkedNode pre = head;
        while (cur != null){
            if (cur.value != num){
                pre = cur;
            }
            else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;

    }

    //打印单链表
    private static void printLinkedList(LinkedNode head){
        while (head != null){
            System.out.print(head.value + " -> ");
            head = head.next;
        }
        System.out.print("null");
    }

    public static void main(String[] args) {

        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node4 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        node1.next = node2;
        node2.next = node4;
        node4.next = node3;

        LinkedNode head = qs3_process1(node1,2);

        printLinkedList(head);

    }
}
