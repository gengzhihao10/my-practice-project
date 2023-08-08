package com.c.mashibing.suanfa.tixike;

import java.io.*;

/*
todo
 题目1，使用数组实现一个并查集
 */
public class Code15_10并查集2 {


    public static int MAXN = 1000001;

    public static int[] father = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static int[] help = new int[MAXN];


    // 初始化并查集
    public static void init(int n) {

    }

    // 查询x和y是不是一个集合
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    // 从i开始寻找集合代表点
    public static int find(int i) {
        return 0;
    }

    // x所在的集合，和y所在的集合，合并成一个集合
    public static void union(int x, int y) {


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
