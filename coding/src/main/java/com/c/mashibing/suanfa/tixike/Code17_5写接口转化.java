package com.c.mashibing.suanfa.tixike;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/*
 题目1，写个Adapter将图的某种结构转化为图的自定义标准结构
 */
public class Code17_5写接口转化 {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    //
    /*
     * @author gengzhihao
     * @date 2023/8/15 9:43
     * @description 题目1
     * @param matrix
     * @return Graph
     **/
    public static Graph qs1_process1(int[][] matrix){
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++){
            Edge edge = new Edge();
            edge.weight = matrix[i][0];
            if (graph.nodes.containsKey(matrix[i][1])){
                graph.nodes.put(matrix[i][1],new Node(matrix[i][1]));
            }
            if (graph.nodes.containsKey(matrix[i][2])){
                graph.nodes.put(matrix[i][2],new Node(matrix[i][2]));
            }
            Node start = graph.nodes.get(matrix[i][1]);
            Node end = graph.nodes.get(matrix[i][2]);
            start.out++;
            start.nexts.add(end);
            start.edges.add(edge);
            end.in++;
            edge.start = start;
            edge.start = end;
            graph.edges.add(edge);
        }
        return graph;
    }

    private static class Graph{
        HashMap<Integer,Node> nodes;
        HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
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
