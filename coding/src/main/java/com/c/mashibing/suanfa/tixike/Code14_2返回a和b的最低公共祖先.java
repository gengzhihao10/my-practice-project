package com.c.mashibing.suanfa.tixike;

import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import java.util.*;

/*
 题目1，给定一颗二叉树的头节点head，和另外两个节点a和b，
 返回a和b的最低公共祖先，可以使用更多的额外空间
 题目2，类似题目1，使用递归套路解题
 */
public class Code14_2返回a和b的最低公共祖先 {

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/1 11:33
     * @description 题目1
     * @param root
     * @param a
     * @param b
     * @return Node
     **/
    public static Node qs1_process1(Node root,Node a, Node b){
        if (root == null){
            return null;
        }

        Map<Node,Node> map = new HashMap<>();
        pre(root,map);
        map.put(root,null);

        Set<Node> set = new HashSet<>();
        Node cur = a;
        while (cur != null){
            set.add(cur);
            cur = map.get(cur);
        }

        cur = b;
        while (cur != null){
            if (set.contains(cur)){
                return cur;
            }
            cur = map.get(cur);
        }

        return cur;
    }

    private static void pre(Node root, Map<Node, Node> map) {
        if (root == null){
            return;
        }
        if (!map.containsKey(root.left)){
            map.put(root.left,root);
        }
        if (!map.containsKey(root.right)){
            map.put(root.right,root);
        }

        pre(root.left,map);
        pre(root.right,map);
    }
//****************************************************************

    private static class Info{
        boolean findA;
        boolean findB;
        Node ancestor;

        public Info() {
        }

        public Info(boolean findA, boolean findB, Node ancestor) {
            this.findA = findA;
            this.findB = findB;
            this.ancestor = ancestor;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/1 14:46
     * @description 题目2
     * @param root
     * @param a
     * @param b
     * @return Node
     **/
    public static Node qs2_process1(Node root,Node a , Node b){
        return process1(root,a,b).ancestor;
    }

    public static Info process1(Node root,Node a,Node b){
        if (root == null){
            return new Info(false,false,null);
        }

        Info leftInfo = process1(root.left,a,b);
        Info rightInfo = process1(root.right,a,b);

        boolean findA = root == a || leftInfo.findA || rightInfo.findA;
        boolean findB = root == b || leftInfo.findB || rightInfo.findB;

        Node ancestor = null;
        if (leftInfo.ancestor != null){
            return new Info(findA,findB,leftInfo.ancestor);
        }
        if (rightInfo.ancestor != null){
            return new Info(findA,findB,rightInfo.ancestor);
        }

        if (findA && findB){
            return new Info(findA,findB,root);
        }

        return new Info(findA,findB,null);
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != qs1_process1(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }



    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
}


