package com.c.mashibing.suanfa.tixike;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 题目1，给定一颗二叉树的头节点root，任何2个节点之间都存在距离，返回整颗二叉树的最大距离
 */
public class Code13_4返回整颗二叉树的最大距离 {

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
     * @date 2023/7/29 10:15
     * @description 题目1
     * @param root
     * @return int
     **/
    public static int qs1_process1(Node root){
        if (root == null){
            return 0;
        }

        return process1(root).maxDistance;
    }

    //题目1 处理函数
    public static Info process1(Node root){
        if (root == null){
            return new Info(0,0);
        }
        Info leftInfo = process1(root.left);
        Info rightInfo = process1(root.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;

        int maxDistance = Math.max(leftInfo.height + rightInfo.height + 1,
                Math.max(leftInfo.maxDistance,rightInfo.maxDistance));
        return new Info(maxDistance,height);
    }

    private static class Info{
        int maxDistance;
        int height;

        public Info() {
        }

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }


    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxDistance1(head) != qs1_process1(head)) {
                System.out.println("Oops!");
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

    public static int maxDistance1(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = getPrelist(head);
        HashMap<Node, Node> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
            }
        }
        return max;
    }

    public static ArrayList<Node> getPrelist(Node head) {
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        return arr;
    }

    public static HashMap<Node, Node> getParentMap(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(head, null);
        fillParentMap(head, map);
        return map;
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

    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
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
        Node lowestAncestor = cur;
        cur = o1;
        int distance1 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance1++;
        }
        cur = o2;
        int distance2 = 1;
        while (cur != lowestAncestor) {
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1 + distance2 - 1;
    }
}
