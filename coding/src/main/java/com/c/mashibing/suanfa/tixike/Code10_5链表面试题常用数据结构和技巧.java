package com.c.mashibing.suanfa.tixike;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 题目1， 判断输入的链表是否为回文结构。可以使用额外空间
 题目2，判断输入的链表是否为回文结构。要求只用有限几个变量
 题目3，一个链表，假设其节点从左到右为L1 L2 L3 L4 R1 R2 R3 R4，
 将其转变为L1 R4 L2 R3 L3 R2 L4 R1
 */
public class Code10_5链表面试题常用数据结构和技巧 {

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
     * @date 2023/7/8 9:01
     * @description 题目1
     * @param head
     * @return boolean
     **/
    public static boolean qs1_process1(Node head){
        if (head == null || head.next == null){
            return true;
        }

        //将链表放入数组中
        Stack<Node> nodes = new Stack<>();
        Node cur = head;
        while (cur != null){
            nodes.push(cur);
            cur = cur.next;
        }

        //对比是否为回文结构
        cur = head;
        while (cur != null){
            if (nodes.peek().value != cur.value){
                return false;
            }
            nodes.pop();
            cur = cur.next;
        }
        return true;
    }

//    public static void main(String[] args) {
//        Node head1 = new Node(1);
//        head1.next = new Node(2);
//        head1.next.next = new Node(2);
//        head1.next.next.next = new Node(1);
////        Node newHead = reverseLinkedList(head1);
//        System.out.println(qs2_process1(head1));
//        printLinkedList(head1);
//
//        Node head2 = new Node(1);
//        head2.next = new Node(2);
//        head2.next.next = new Node(1);
//        System.out.println(qs2_process1(head2));
//
//        Node head3 = new Node(1);
//        head3.next = new Node(2);
//        head3.next.next = new Node(3);
//        System.out.println(qs2_process1(head3));
//    }

    //***********************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/7/8 9:13
     * @description 题目2
     * @param head
     * @return boolean
     **/
    public static boolean qs2_process1(Node head){
        if (head == null || head.next == null){
            return true;
        }

        //找到中间节点
        Node fast = head;
        Node slow = head;

        while (fast.next != null){
            fast = fast.next;
            fast = fast.next;
            if (fast == null){
                break;
            }
            slow = slow.next;
        }

        //逆转slow后面的链表
        Node L = head;
        Node R = reverseLinkedList(slow);
        Node tail = R;

        //对比链表是否为回文
        while (L != null && R != null){
            if (L.value != R.value){
                return false;
            }
            L = L.next;
            R = R.next;
        }

        //将链表恢复至最初
        reverseLinkedList(tail);
        return true;
    }

    private static Node reverseLinkedList(Node head){
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

//********************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/7/8 10:34
     * @description 题目3
     * @param head
     * @return Node
     **/
    public static Node qs3_process1(Node head){
        if (head == null || head.next == null){
            return null;
        }

        //找到中间节点
        Node fast = head;
        Node slow = head;

        while (fast.next != null){
            fast = fast.next;
            fast = fast.next;
            if (fast == null){
                break;
            }
            slow = slow.next;
        }

        //逆转slow后面的链表
        Node L = head;
        Node R = reverseLinkedList(slow);
        Node lNext = L.next;
        Node rNext = R.next;
        //将链表转变结构
        while (L != null && R != null){
            lNext = L.next;
            L.next = R;
            L = lNext;

            rNext = R.next;
            R.next = L;
            R = rNext;
        }

        return head;
    }

    private static void printLinkedList(Node head){
        while (head != null){
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
//        Node newHead = reverseLinkedList(head1);
        System.out.println(qs3_process1(head1));
        printLinkedList(head1);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        System.out.println(qs3_process1(head2));
        printLinkedList(head2);

        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(3);
        System.out.println(qs2_process1(head3));
    }
}
