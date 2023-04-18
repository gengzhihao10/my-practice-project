package com.c.mashibing.suanfa.xinshouke;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Code6_1比较器 {

    public static class Student{
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return  id + " " + name + " " +age;
        }
    }

    //id比较器，id小的排在前面
    public static class IdComparator implements Comparator<Student>{

        //如果返回负数，第一个参数应该排在前面
        //如果返回正数，第二个参数应该排在前面
        //如果返回0，则无所谓
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.id < o2.id){
                return -1;
            }
            else if (o1.id > o2.id){
                return 1;
            }
            return 0;
        }
    }

    public static class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2){
                return 1;
            }
            if (o1 > o2){
                return -1;
            }
            return 0;
        }
    }


    public static void printArray(int[] arr){
        for (int i = 0; i<arr.length; i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.println();
    }

    public static void printStudent(Student[] arr){
        for (int i = 0; i<arr.length; i++){
            System.out.print(arr[i].id+" "+arr[i].name+" "+arr[i].age+"    ");
        }
        System.out.println();
    }

    public static void printStudentList(List<Student> list){
        for (int i = 0; i<list.size(); i++){
            System.out.print(list.get(i)+"    ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

//        int[] arr = {8,5,9,1,5,6,4,7,1,3,5,7,8,1,6};
//        printArray(arr);
//        Arrays.sort(arr);
//        printArray(arr);

        Student student1 = new Student("一",5,12);
        Student student2 = new Student("二",1,44);
        Student student3 = new Student("三",6,12);
        Student student4 = new Student("四",15,52);
        Student student5 = new Student("五",45,23);
        Student student6 = new Student("六",31,31);
        Student[] students = {student1,student2,student3,student4,student5,student6};

        printStudent(students);
        Arrays.sort(students,new IdComparator());
        printStudent(students);

        System.out.println("===========");

        ArrayList<Student> arrList = new ArrayList<>();
        arrList.add(student1);
        arrList.add(student2);
        arrList.add(student3);
        arrList.add(student4);
        arrList.add(student5);
        arrList.add(student6);

        printStudentList(arrList);
        arrList.sort(new IdComparator());
        printStudentList(arrList);

        System.out.println("=========");
        //优先级队列，实际上是堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(6);
        heap.add(3);
        heap.add(8);
        System.out.println(heap.peek());

        System.out.println("==========");
        while (!heap.isEmpty()){
            System.out.print(heap.poll() + " ");
        }
        System.out.println();

    }
}
