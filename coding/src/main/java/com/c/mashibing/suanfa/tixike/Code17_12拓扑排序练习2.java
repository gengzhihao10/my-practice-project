package com.c.mashibing.suanfa.tixike;

import java.util.*;

/*
 题目1，同17_11第一题，统计深度，深度高的点，拓扑序靠前
 */
public class Code17_12拓扑排序练习2 {

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

    private static class RecordComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o2.height - o1.height;
        }
    }

    //缓存的数据存在map中
    private static Record cacheNum(Map<DirectedGraphNode, Record> map, DirectedGraphNode node) {
        if (map.containsKey(node)){
            return map.get(node);
        }

        int num = 0;
        for (DirectedGraphNode next : node.neighbors){
            num = Math.max(num,cacheNum(map,next).height);
        }

        Record record = new Record(node,num + 1);
        map.put(node,record);
        return record;
    }

    private static class Record{
        DirectedGraphNode node;
        int height;

        public Record() {
        }

        public Record(DirectedGraphNode node) {
            this.node = node;
            height = 0;
        }

        public Record(DirectedGraphNode node, int height) {
            this.node = node;
            this.height = height;
        }
    }
}
