package com.c.mashibing.suanfa.tixike;

/*
todo
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


        return null;
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


        return null;
    }
}
