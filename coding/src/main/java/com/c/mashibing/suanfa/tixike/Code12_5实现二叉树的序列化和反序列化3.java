package com.c.mashibing.suanfa.tixike;

import org.hibernate.criterion.LikeExpression;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，实现二叉树基于按层遍历的序列化和反序列化
 */
public class Code12_5实现二叉树的序列化和反序列化3 {

    //二叉树节点
    static class Node {
        int value;
        Node left;
        Node right;

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
     * @date 2023/7/20 9:43
     * @description 题目1，按层遍历，序列化
     * @param node
     * @return String
     **/
    public static String qs1_serialize(Node root) {
        if (root == null) {
            return "";
        }

        Node cur;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur != null){
                queue.offer(cur.left);
            }
            if (cur != null){
                queue.offer(cur.right);
            }
            //处理序列化逻辑
            builder.append(cur == null ? "#" : String.valueOf(cur.value));
            builder.append(",");
        }
        return builder.toString();
    }

    /*
     * @author gengzhihao
     * @date 2023/7/20 10:03
     * @description 题目1，反序列化
     * @param str
     * @return Node
     **/
    public static Node qs1_deserialize(String input){
        if (StringUtils.isEmpty(input)){
            return null;
        }

        //装节点的队列
        String[] strs = input.split(",");
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();

        for (String str : strs){
            if ("#".equals(str)){
                queue1.offer(null);
            }else {
                queue1.offer(new Node(Integer.parseInt(str)));
            }
        }

        Node cur;
        Node head = queue1.poll();
        queue2.offer(head);
        while (!queue2.isEmpty()){
            cur = queue2.poll();
            cur.left = queue1.poll();
            cur.right = queue1.poll();
            if (cur.left != null){
                queue2.offer(cur.left);
            }
            if (cur.right != null){
                queue2.offer(cur.right);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;

        String res = qs1_serialize(n1);
        System.out.println(res);


        Node root = qs1_deserialize(res);
        System.out.println("test");
    }

}
