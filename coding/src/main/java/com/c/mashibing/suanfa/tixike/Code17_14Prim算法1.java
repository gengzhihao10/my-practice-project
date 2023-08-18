package com.c.mashibing.suanfa.tixike;

import netscape.security.UserTarget;

import java.util.*;

/*
 题目1，找到最小生成树，使用Prim算法
 */
public class Code17_14Prim算法1 {


    /*
     * @author gengzhihao
     * @date 2023/8/17 11:06
     * @description 题目1
     * @param graph
     * @return Set<Edge>
     **/
    public static Set<Edge> primMST(Graph graph){
        if (graph == null){
            return null;
        }


        //已解锁 的node
        Set<Node> nodeSet = new HashSet<>();
        //已解锁 的edge
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>();
        //结果 边的集合
        Set<Edge> res = new HashSet<>();

        for (Node node : graph.nodes.values()){
            if (!nodeSet.contains(node)){
                nodeSet.add(node);
                for (Edge edge : node.edges){
                    edgePriorityQueue.offer(edge);
                }
                while (!edgePriorityQueue.isEmpty()){
                    Edge edge = edgePriorityQueue.poll();
                    Node endNode = edge.end;
                    if (!nodeSet.contains(endNode)){
                        nodeSet.add(endNode);
                        res.add(edge);
                        for (Edge edge1 : endNode.edges){
                            edgePriorityQueue.add(edge1);
                        }
                    }
                }
            }
        }
        return res;
    }

    //权重小的排前面
    private static class EdgeComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
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
