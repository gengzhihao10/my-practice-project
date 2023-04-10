package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Code4_2单链表的反转 {

    //单链表节点
    static class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    //双向链表节点
    static class DoubleNode{
        int data;
        DoubleNode next;
        DoubleNode last;

        public DoubleNode(int data, DoubleNode next, DoubleNode last) {
            this.data = data;
            this.next = next;
            this.last = last;
        }
    }


    public static void main(String[] args) {
//        Node head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//
//        head = reverseLinkedList(head);
//        printLinkedList(head);

        DoubleNode head = new DoubleNode(1,null,null);
        DoubleNode second = new DoubleNode(2,null,null);
        DoubleNode third = new DoubleNode(3,null,null);
        head.next = second;
        second.last = head;
        second.next = third;
        third.last = second;

        head = reverseDoubleLinkedList(head);
        printDoubleLinkedList(head);
    }

    /*
     * @author gengzhihao
     * @date 2023/4/7 11:49
     * @description 打印单向链表
     * @param cur 传入头节点
     **/
    private static void printLinkedList(Node cur) {
        while (cur != null){
            System.out.print(cur.data + "    ");
            cur = cur.next;
        }
        System.out.println();
    }

    private static void printDoubleLinkedList(DoubleNode cur) {
        while (cur != null){
            System.out.print(cur.data + "    ");
            cur = cur.next;
        }
        System.out.println();
    }

    /*
     * @author gengzhihao
     * @date 2023/4/7 11:50
     * @description 反转单向链表
     * @param head 头节点
     * @return Node 反转后的头节点
     **/
    private static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        Node cur = head;

        while (cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /*
     * @author gengzhihao
     * @date 2023/4/10 9:51
     * @description 反转双向链表
     * @param head 头节点
     * @return DoubleNode 反转后的头节点
     **/
    private static DoubleNode reverseDoubleLinkedList(DoubleNode head){

        DoubleNode pre = null;
        DoubleNode next = null;
        DoubleNode cur = head;

        while(cur != null){
            //下一个节点next记录下一步位置
            next = cur.next;
            //进行当前节点cur的反转工作
            cur.next = pre;
            cur.last = next;
            //当前节点cur和前一个节点pre移动到下一步位置
            pre = cur;
            cur = next;
        }
        return pre;

    }


}
