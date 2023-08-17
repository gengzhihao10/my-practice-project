package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
 题目1，实现图的拓扑排序，这个题是不需要Adapter转化的
 拓扑序要求图是有向图且无环
 */
public class Code17_10图的拓扑排序算法 {


    /*
     * @author gengzhihao
     * @date 2023/8/15 10:23
     * @description 题目1
     * @param graph
     * @return List<Node>
     **/
    public static List<Node> qs1_process1(Graph graph) {
        if (graph == null) {
            return null;
        }

        //offer：发现了一个新的0入度的node；poll：将这个node脱离图，其nexts.in--
        Queue<Node> zeroInNodes = new LinkedList<>();
        Map<Node, Integer> inMap = new HashMap<>();

        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInNodes.offer(node);
            }
        }

        List<Node> res = new LinkedList<>();
        Node cur;
        while (!zeroInNodes.isEmpty()) {
            cur = zeroInNodes.poll();
            res.add(cur);
            for (Node node : cur.nexts) {
                inMap.put(node,inMap.get(node)-1);
                if (inMap.get(node) == 0) {
                    zeroInNodes.offer(node);
                }
            }
        }

        return res;
    }


    private static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.in - o2.in;
        }
    }

    private static class Graph {
        HashMap<Integer, Node> nodes;
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

    private static class Edge {
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

    private static class Node {
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
