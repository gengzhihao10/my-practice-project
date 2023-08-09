package com.c.mashibing.suanfa.tixike;

import java.io.*;

/*
 题目1，使用数组实现一个并查集
 */
public class Code15_10并查集2 {


    public static int MAXN = 1000001;

    //fater[i] i : i的父亲为fater[i]
    public static int[] father = new int[MAXN];
    //仅有为head的size[i]是有效的
    public static int[] size = new int[MAXN];
    //help[]是帮助数组
    public static int[] help = new int[MAXN];


    // 初始化并查集
    public static void init(int n) {
        for (int i = 1; i <= n; i++){
            father[i] = i;
            size[i] = 1;
        }
    }

    // 查询x和y是不是一个集合
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    // 从i开始寻找集合代表点
    public static int find(int cur) {
        int parent = father[cur];
        int index = 0;

        while (parent != cur){
            help[index++] = cur;
            cur = parent;
            parent = father[parent];
        }

        for (;index > 0; index--){
            father[help[index]] = parent;
        }
        return parent;
    }

    // x所在的集合，和y所在的集合，合并成一个集合
    public static void union(int x, int y) {
        int head1 = find(x);
        int head2 = find(y);

        if (head1 == head2){
            return;
        }

        int size1 = size[head1];
        int size2 = size[head2];

        int big = size1 >= size2 ? size1 : size2;
        int small = big == size1 ? size2 : size1;

        size[big] = size[big] + size[small];
        father[small] = big;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            init(n);
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                    out.flush();
                } else {
                    union(x, y);
                }
            }
        }
    }
}
