package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
 题目1，拓扑排序，给定指定的图的结构，统计点次，点次高的点，拓扑序靠前
 */
public class Code17_11拓扑排序的练习1 {


    public static class DirectedGraphNode {
        public int label;
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/17 9:15
     * @description 题目1
     * @param graph
     * @return ArrayList<DirectedGraphNode>
     **/
    public static ArrayList<DirectedGraphNode> qs1_process1(ArrayList<DirectedGraphNode> graph) {
        if (graph == null || graph.isEmpty()){
            return null;
        }

        Map<DirectedGraphNode,Record> map = new HashMap<>();
        for (DirectedGraphNode node : graph){
            cacheNum(map,node);
        }

        List<Record> records = new ArrayList<>(map.size());
        for (Record record : map.values()){
            records.add(record);
        }
        records.sort(new RecordComparator());

        ArrayList<DirectedGraphNode> res = new ArrayList<>(records.size());
        for (Record record : records){
            res.add(record.node);
        }
        return res;
    }

    private static class RecordComparator implements Comparator<Record>{

        @Override
        public int compare(Record o1, Record o2) {
            return o2.num - o1.num;
        }
    }

    //缓存的数据存在map中
    private static Record cacheNum(Map<DirectedGraphNode, Record> map, DirectedGraphNode node) {
        if (map.containsKey(node)){
            return map.get(node);
        }

        int num = 0;
        for (DirectedGraphNode next : node.neighbors){
            num += cacheNum(map,next).num;
        }

        Record record = new Record(node,num + 1);
        map.put(node,record);
        return record;
    }

    private static class Record{
        DirectedGraphNode node;
        int num;

        public Record() {
        }

        public Record(DirectedGraphNode node) {
            this.node = node;
            num = 0;
        }

        public Record(DirectedGraphNode node, int num) {
            this.node = node;
            this.num = num;
        }
    }
}
