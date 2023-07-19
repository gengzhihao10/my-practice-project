package com.c.mashibing.suanfa.tixike;

import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，实现二叉树先序遍历的序列化和反序列化
 */
public class Code12_3实现二叉树的序列化和反序列化1 {

    //二叉树
    static class Node{
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/7/19 10:43
     * @description 题目1，序列化
     * @param root
     * @return String
     **/
    public static String qs1_serialize(Node root){
        if (root == null){
            return "";
        }

        Queue<Node> queue = new LinkedList<>();
        getQueueByPreTraver(root,queue);
        return queue2String(queue);
    }

    //队列转为字符串
    private static String queue2String(Queue<Node> queue) {
        StringBuilder builder = new StringBuilder();
        Node cur;
        while (!queue.isEmpty()){
            cur = queue.poll();
            if (cur == null){
                builder.append("#");
                builder.append(",");
            }else {
                builder.append(cur.value);
                builder.append(",");
            }
        }
        return builder.toString();
    }

    //基于先序遍历，构造队列
    private static void getQueueByPreTraver(Node root, Queue<Node> queue) {
        if (root == null){
            queue.offer(null);
            return;
        }
        queue.offer(root);
        getQueueByPreTraver(root.left,queue);
        getQueueByPreTraver(root.right,queue);
    }

    //****************************************************************************************************

    /*
     * @author gengzhihao
     * @date 2023/7/19 11:09
     * @description 题目1，反序列化
     * @param str
     * @return Node
     **/
    public static Node qs1_deserialize(String str){
        Queue<Node> queue = string2queue(str);
        if (queue.isEmpty()){
            return null;
        }
        Node root = getTreeByQueue(queue);
        return root;
    }

    //通过队列构造二叉树
    private static Node getTreeByQueue(Queue<Node> queue) {
        if (queue.isEmpty()){
            return null;
        }

        Node cur = queue.poll();
        if (cur == null){
            return null;
        }
        cur.left = getTreeByQueue(queue);
        cur.right = getTreeByQueue(queue);
        return cur;
    }

    //字符串转队列
    private static Queue<Node> string2queue(String str) {
        Queue<Node> queue = new LinkedList<>();
        if (StringUtils.isEmpty(str)){
            return queue;
        }
        String[] strs = str.split(",");
        for (String s : strs){
            if ("#".equals(s)){
                queue.offer(null);
            }else {
                queue.offer(new Node(Integer.parseInt(s)));
            }
        }
        return queue;
    }

    //*************************************************************************************************

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

        String result = qs1_serialize(n1);
        System.out.println(result);

        Node root = qs1_deserialize(result);
        System.out.println("test");
    }
}
