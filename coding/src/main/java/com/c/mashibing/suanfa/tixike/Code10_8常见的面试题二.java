package com.c.mashibing.suanfa.tixike;

import java.util.HashMap;
import java.util.Map;

/*
 题目1，一个单向链表，除了next指针还有一个random指针随机指向链表中的某个节点或者null，
 要求深复制出一个新链表，结构和原有链表一样，包括next和random都要一样，
 返回新链表的头节点
 可以使用额外空间
 题目2，类似题目1，但是要求额外空间复杂度为O(1)
 */
public class Code10_8常见的面试题二 {

    //链表节点
    static class Node{
        int value;
        Node next;
        Node random;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next,Node random) {
            this.value = value;
            this.next = next;
            this.random = random;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/12 9:25
     * @description 题目1
     * @param head
     * @return Node
     **/
    public static Node qs1_process1(Node head){
        if (head == null ){
            return null;
        }

        //1.在next方向上构建一个新链表
        Map<Node, Node> map = new HashMap<>();
        Node pre = head;
        Node cur = pre.next;
        Node newHead = new Node(head.value);
        map.put(head,newHead);
        while (cur != null){
            Node newCur = new Node(cur.value);
            map.put(cur,newCur);
            map.get(pre).next = newCur;
            pre = cur;
            cur = cur.next;
        }

        //2.在random方向上，构建原有结构
        cur = head;
        while (cur != null){
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.next = node2;
        node1.random = node1;
        node2.next = node3;
        node2.random = node1;
        node3.next = node4;
        node3.random = node4;
        printNode(node1);
        System.out.println("====================");

        Node newNode1 = qs2_process1(node1);
        printNode(newNode1);
        System.out.println(node1 == newNode1);
    }

    //打印链表
    private static void printNode(Node head){
        Node cur = head;
        while (cur != null){
            System.out.println("value:" + cur.value+" next:"+cur.next + " random:" + cur.random);
            cur = cur.next;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/12 10:02
     * @description 题目2
     * @param head
     * @return Node
     **/
    public static Node qs2_process1(Node head){
        if (head == null){
            return null;
        }

        //1.将新节点，插入到链表中
        Node cur = head;
        Node next = null;
        while (cur != null){
            next = cur.next;

            cur.next = new Node(cur.value);
            cur.next.next = next;

            cur = next;
        }

        //2.复制random的指针
        cur = head;
        next = head.next;
        while (cur != null){
            if (cur.random != null){
                next.random = cur.random.next;
            }
            if (cur.next.next == null){
                break;
            }
            cur = cur.next.next;
            next = next.next.next;
        }

        //3.将链表拆分出来
        Node newHead = head.next;
        Node newCur = newHead;
        cur = head;
        while (cur != null){
            cur.next = cur.next.next;
            if (newCur.next != null){
                newCur.next = newCur.next.next;
            }else {
                newCur.next = null;
            }

            cur = cur.next;
            newCur = newCur.next;
        }

        return newHead;
    }
}
