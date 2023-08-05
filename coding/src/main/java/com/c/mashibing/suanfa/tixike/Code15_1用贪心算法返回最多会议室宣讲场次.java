package com.c.mashibing.suanfa.tixike;

import java.util.Arrays;
import java.util.Comparator;

/*
 题目1，一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 给你每一个项目开始的时间和结束的时间
 你来安排宣讲的日程，要求会议室进行的宣讲的次数最多。
 返回最多的宣讲次数
 */
public class Code15_1用贪心算法返回最多会议室宣讲场次 {

    private static class Meeting{
        int start;
        int end;

        public Meeting() {
        }

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /*
     * @author gengzhihao
     * @date 2023/8/4 9:47
     * @description 题目1
     * @param meetings
     * @return int
     **/
    public static int qs1_process1(Meeting[] meetings){
        if (meetings == null || meetings.length == 0){
            return 0;
        }

        int time = 0;
        int count = 0;

        Arrays.sort(meetings,new MeetingComparator());

        for (Meeting meeting : meetings){
            if (time < meeting.start){
                time = meeting.end;
                count++;
            }
        }
        return count;
    }

    //谁的end早,谁排前面
    private static class MeetingComparator implements Comparator<Meeting>{
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }
}
