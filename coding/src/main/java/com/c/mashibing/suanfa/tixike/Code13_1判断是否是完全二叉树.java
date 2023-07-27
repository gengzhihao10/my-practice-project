package com.c.mashibing.suanfa.tixike;

import java.util.LinkedList;
import java.util.Queue;

/*
 题目1，判断二叉树是否为完全二叉树
 */
public class Code13_1判断是否是完全二叉树 {

    //二叉树节点
    private static class Node{
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
     * @date 2023/7/27 9:39
     * @description 题目1
     * @param root
     * @return boolean
     **/
    public static boolean qs1_process1(Node root){
        if (root == null){
            return true;
        }

        Node cur;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeafNode = false;

        while (!queue.isEmpty()){
            cur = queue.poll();

            if (cur.left != null){
                queue.offer(cur.left);
            }
            if (cur.right != null){
                queue.offer(cur.right);
            }

            //1. 左孩子为空，右孩子不为空，不是完全二叉树
            if (cur.left == null && cur.right != null){
                return false;
            }
            //2. 如果出现了叶子节点，按层遍历的后面的节点都需要是叶子节点
            if (isLeafNode){
                if (cur.left != null || cur.right != null){
                    return false;
                }
            }
            if (cur.left == null || cur.right == null){
                isLeafNode = true;
            }

        }
        return true;
    }


    //*****************************************************************************
    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (qs1_process1(head) != qs1_process2(head)) {
                printTree(head);
                System.out.println("1: "+qs1_process1(head));
                System.out.println("2: "+qs1_process2(head));
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }

    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    //*****************************************************************************
    /*
     * @author gengzhihao
     * @date 2023/7/27 9:58
     * @description 题目1
     * @param root
     * @return boolean
     **/
    public static boolean qs1_process2(Node root){
        if (root == null){
            return true;
        }
        return process2(root).isCBT;
    }

    private static Res process2(Node root){
        if (root == null){
            return new Res(true,0);
        }

        Res leftRes = process2(root.left);
        Res rightRes = process2(root.right);
        int height = Math.max(leftRes.height,rightRes.height) + 1;

        boolean isCBT = true;
        if (!leftRes.isCBT || !rightRes.isCBT){
            isCBT = false;
        }
        if (root.left == null && root.right != null){
            isCBT = false;
        }
        if (Math.abs(leftRes.height - rightRes.height) > 1){
            isCBT = false;
        }

        return new Res(isCBT,height);
    }

    private static class Res{
        boolean isCBT;
        int height;

        public Res() {
        }

        public Res(boolean isCBT, int height) {
            this.isCBT = isCBT;
            this.height = height;
        }
    }
}
