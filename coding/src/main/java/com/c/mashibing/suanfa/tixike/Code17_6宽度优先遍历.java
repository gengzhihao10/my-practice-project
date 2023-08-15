package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
 题目1，实现图的宽度优先遍历
 */
public class Code17_6宽度优先遍历 {

    /*
     * @author gengzhihao
     * @date 2023/8/15 8:45
     * @description 题目1
     * @param start
     **/
    public static void bfs(Node start){
        if (start == null){
            return;
        }

        Set<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        set.add(start);
        Node cur;
        List<Node> nexts;
        while (!queue.isEmpty()){
            cur = queue.poll();
            nexts = cur.nexts;
            System.out.println("  " + cur.value);
            for (Node node : nexts){
                if (!set.contains(node)){
                    queue.offer(node);
                    set.add(node);
                }
            }
        }

    }

    //图
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

    //边
    private static class Edge{
        int weight;
        Node start;
        Node end;

        public Edge() {
        }

        public Edge(int weight, Node start, Node end) {
            this.weight = weight;
            this.start = start;
            this.end = end;
        }
    }

    //点
    private static class Node{
        //这个点的值
        int value;
        //入度，有几条边进来
        int in;
        //出度，有几条边出去
        int out;
        //从这个点出发，下面的点有几个
        ArrayList<Node> nexts;
        //从这个点出发，下面的边有几个
        ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
}
