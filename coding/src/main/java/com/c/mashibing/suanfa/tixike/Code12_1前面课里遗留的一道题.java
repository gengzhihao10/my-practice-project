package com.c.mashibing.suanfa.tixike;

/*
 题目1，给定头节点和链表中任意一个节点，删除指定节点
 题目2，给定链表中任意一个节点，删除指定节点
 */
public class Code12_1前面课里遗留的一道题 {

    //单链表
    static class Node{
        int value;
        Node next;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/18 9:00
     * @description 题目1
     * @param head
     * @param n
     * @return Node
     **/
    public static Node qs1_process1(Node head, Node n){
        if (head == null || n == null){
            return null;
        }

        Node cur = head;
        Node next = cur.next;
        if (cur == n){
            cur.next = null;
            return next;
        }
        while (cur != null){
            if (next == n){
                Node temp = next;
                cur.next = next.next;
                temp.next = null;
                break;
            }
            cur = cur.next;
            next = next.next;
        }
        return head;
    }

    /*
     * @author gengzhihao
     * @date 2023/7/18 9:07
     * @description 题目2
     * 这种做法很危险，存在2点问题
     * 1，如果要删除的节点是尾节点，那么没法删除
     * 2，如果是生产数据，这么删除是应该禁止的。
     * 因为如果数据存在外部系统的依赖，那么很有可能导致数据的错乱。
     * 即使不存在外部依赖，生产数据也不应该大范围的这样拷贝来拷贝去，只为删除一个数据，是极其危险的行为
     * @param n
     * @return Node
     **/
    public static Node qs2_proecss1(Node n){
        if (n == null){
            return null;
        }
        Node pre;
        Node cur = n;
        Node next = cur.next;
        while (next != null){
            cur.value = next.value;
            if (next.next == null){
                cur.next = null;
                break;
            }

            cur = cur.next;
            next = next.next;
        }
        return n;
    }

    private static void printLinkedList(Node head){
        if (head == null){
            return;
        }

        while (head != null){
            System.out.print("  " + head.value);
            head = head.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

//        printLinkedList(n1);
//        n1 = qs1_process1(n1,n4);
//        printLinkedList(n1);

        printLinkedList(n1);
        qs2_proecss1(n2);
        printLinkedList(n1);
    }
}
