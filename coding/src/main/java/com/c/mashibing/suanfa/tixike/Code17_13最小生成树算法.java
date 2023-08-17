package com.c.mashibing.suanfa.tixike;


import java.util.*;

/*
 题目1，通过和最小的路径，将所有的点联通起来。使用最小生成树算法之Kruskal
 */
public class Code17_13最小生成树算法 {



    /*
     * @author gengzhihao
     * @date 2023/8/15 11:28
     * @description 题目1
     * @param graph
     * @return Set<Edge>
     **/
    public static Set<Edge> kruskalMST(Graph graph) {
        if (graph == null){
            return null;
        }

        UnionSet unionSet = new UnionSet();
        for (Node node : graph.nodes.values()){
            unionSet.add(node);
        }
        PriorityQueue<Edge> edges = new PriorityQueue<>(new EdgeComparator());
        Set<Edge> res = new HashSet<>();
        for (Edge edge : graph.edges){
            edges.offer(edge);
        }

        Edge cur;
        Node start,end;
        while (!edges.isEmpty()){
            cur = edges.poll();
            if (!unionSet.isSameSet(cur.start,cur.end)){
                 res.add(cur);
                 unionSet.union(cur.start,cur.end);
            }
        }
        return res;
    }

    private static class UnionSet{

        Map<Node,Node> parent;
        Map<Node,Integer> size;
        List<Node> help;

        public UnionSet() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new LinkedList<>();
        }

        public boolean isSameSet(Node n1,Node n2){
            return findAncestor(n1) == findAncestor(n2);
        }

        public Node findAncestor(Node node){
            Node father = parent.get(node);
            int i = 0;
            while (father != node){
                help.set(i++,node);
                node = father;
                father = parent.get(father);
            }

            while (i != 0){
                parent.put(help.get(--i),father);
            }
            return father;
        }

        public void add(Node node ){
            parent.put(node,node);
            size.put(node,1);
        }

        public void union(Node n1, Node n2){
            Node head1 = findAncestor(n1);
            Node head2 = findAncestor(n2);
            int size1 = size.get(head1);
            int size2 = size.get(head2);
            Node big = size1 >= size2 ? head1 : head2;
            Node small = (big == head1) ? head2 : head1;
            parent.put(small,big);
            size.put(big,size1+size2);

        }
    }

    //权重小靠前
    private static class EdgeComparator implements Comparator<Edge> {

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
