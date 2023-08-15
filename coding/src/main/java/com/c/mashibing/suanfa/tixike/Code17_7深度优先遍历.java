package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
todo
 题目1，实现图的深度优先遍历
 */
public class Code17_7深度优先遍历 {

    /*
     * @author gengzhihao
     * @date 2023/8/15 9:31
     * @description 题目1
     * @param node
     **/
    public static void dfs(Node node){
        if (node == null){
            return;
        }

        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        System.out.println("  " + node.value);
        set.add(node);

        Node cur;
        List<Node> nexts;
        while (!stack.isEmpty()){
            cur = stack.pop();
            nexts = cur.nexts;
            for (Node next : nexts){
                if (!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println("  " + next.value);
                    break;
                }
            }
        }
    }


    private static class Graph{
        HashMap<Integer,Node> nodes;
        HashSet<Edge> edges;

        public Graph() {
        }

        public Graph(HashMap<Integer, Node> nodes, HashSet<Edge> edges) {
            this.nodes = nodes;
            this.edges = edges;
        }
    }

    private static class Edge{
        Node start;
        Node end;
        int weight;

        public Edge() {
        }

        public Edge(Node start, Node end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    private static class Node{
        int value;
        int in;
        int out;
        ArrayList<Node> nexts;
        ArrayList<Edge> edges;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
}
