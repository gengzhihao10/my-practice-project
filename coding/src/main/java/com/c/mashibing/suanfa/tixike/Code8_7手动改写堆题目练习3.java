package com.c.mashibing.suanfa.tixike;

import java.util.*;

import com.c.mashibing.suanfa.tixike.Code8_6手动改写堆题目练习2.EnhancedHeap;

/*
 题目1，一对arr[i]和op[i]就代表一个事件：
 用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
 op[i] == F就代表这个用户退货了一件商品
 现在你作为电商平台负责人，你想在每一个事件到来的时候，
 都给购买次数最多的前K名用户颁奖。
 所以每个事件发生后，你都需要一个得奖名单（得奖区）
 具体规则见PPT
 */
public class Code8_7手动改写堆题目练习3 {

    //顾客
    public static class Customer{
        int id;
        int count;
        int time;

        public Customer(int id, int count, int time) {
            this.id = id;
            this.count = count;
            this.time = time;
        }
    }

    //得奖区比较器
    public static class RewardComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.count != o2.count ? o1.count - o2.count : o1.time - o2.time;
        }
    }

    //候选区比较器
    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.count != o2.count ? o2.count - o1.count : o1.time - o2.time;
        }
    }


    /*
     * @author gengzhihao
     * @date 2023/6/27 8:40
     * @description 题目1
     * @param arr i时刻发生行为的顾客id的数组
     * @param op i时刻发生行为(true购买 or false退货)的数组
     * @param k 得奖区最大容量
     * @return List<List<Customer>>
     **/
    public static List<List<Integer>> qs1_process1(int[] arr,boolean[] op,int k){
        if (arr == null || arr.length == 0 || op == null || op.length == 0 || arr.length != op.length){
            return null;
        }

        EnhancedHeap<Customer> rewardHeap = new EnhancedHeap<>(new RewardComparator());
        EnhancedHeap<Customer> candidateHeap = new EnhancedHeap<>(new CandidateComparator());
        //key 为 count不为0的顾客的id，value 为 该顾客
        Map<Integer,Customer> custmers = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++){
            process1(rewardHeap,candidateHeap,custmers,result,arr,op,k,i);
        }
        return result;
    }

    //处理逻辑
    private static void process1(EnhancedHeap<Customer> rewardHeap, EnhancedHeap<Customer> candidateHeap,Map<Integer,Customer> customers, List<List<Integer>> result, int[] arr, boolean[] op, int k,int i ) {
        //该顾客count为0，且发生退货行为
        if (!customers.containsKey(arr[i]) && op[i] == false){
            //fixme 这里的复杂度应该是n平方了，这是为什么，看一下老师代码
            result.add(getRewardCustomers(rewardHeap));
            return;
        }
        //如果count不为0的顾客列表中没有当前顾客，先把这个顾客添加进customers中
        if (!customers.containsKey(arr[i])){
            customers.put(arr[i],new Customer(arr[i],0,0));
        }
        Customer customer = customers.get(arr[i]);
        //customers中有当前顾客
        if (op[i]){
            customer.count++;
        }
        else {
            customer.count--;
        }
        if (customers.get(arr[i]).count == 0){
            customers.remove(arr[i]);
        }

        //对得奖区和候选区进行处理
        //购买行为的处理
        if (op[i]){
            //如果得奖区没有满，推到得奖区中
            if (rewardHeap.size() < k && !rewardHeap.contains(customer)){
                customer.time = i;
                rewardHeap.push(customer);
            }
            else if (rewardHeap.contains(customer)){
                rewardHeap.resign(customer);
            }
            //如果得奖区已经满了，推到候选区
            else if (!candidateHeap.contains(customer)){
                customer.time = i;
                candidateHeap.push(customer);
            }
            else if (candidateHeap.contains(customer)){
                candidateHeap.resign(customer);
            }
        }
        //退货行为
        else {
            if (rewardHeap.contains(customer)){
                if (customer.count == 0){
                    rewardHeap.remove(customer);
                }else {
                    rewardHeap.resign(customer);
                }
            }
            else if (candidateHeap.contains(customer)){
                if (customer.count == 0){
                    candidateHeap.remove(customer);
                }else {
                    candidateHeap.resign(customer);
                }
            }
        }
        adjustRewardAndCandi(rewardHeap,candidateHeap,i,k);
        result.add(getRewardCustomers(rewardHeap));
    }

    //对获奖区和候选区进行调整
    private static void adjustRewardAndCandi(EnhancedHeap<Customer> rewardHeap, EnhancedHeap<Customer> candidateHeap, int i, int k) {
        //候选区为空，没必要调整
        if (candidateHeap.isEmpty()){
            return;
        }

        if (rewardHeap.size() < k){
            Customer customer = candidateHeap.pop();
            customer.time = i;
            rewardHeap.push(customer);
        }
        else {
            if (rewardHeap.peek().count < candidateHeap.peek().count){
                Customer rewardCustomer = rewardHeap.pop();
                Customer candiCustomer = candidateHeap.pop();
                rewardCustomer.time = i;
                candiCustomer.time = i;
                rewardHeap.push(candiCustomer);
                candidateHeap.push(rewardCustomer);
            }
        }
    }

    //拿到获奖区的所有顾客
    private static List<Integer> getRewardCustomers(EnhancedHeap<Customer> rewardHeap) {
        List<Customer> customers = rewardHeap.getAllElements();
        List<Integer> ans = new ArrayList<>();
        for (Customer c : customers) {
            ans.add(c.id);
        }
        return ans;
    }


    public static void main(String[] args) {
        int maxValue = 100;
        int maxLen = 50;
        int maxK = 10;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
//            System.out.println("第"+i+"次");
            Data testData = randomData(maxValue, maxLen);
//            int[] arr = {7,0,7,0,3};
//            boolean[] op = {true,true,true,false,false};
//            Data testData = new Data(arr,op);
//            int k = 2;
            int k = (int) (Math.random() * maxK) + 1;
//            arr = testData.arr;
//            op = testData.op;
            int[] arr = testData.arr;
            boolean[] op = testData.op;
//            for (int j = 0; j < arr.length; j++) {
//                System.out.println(arr[j] + " , " + op[j]);
//            }
            List<List<Integer>> ans1 = qs1_process1(arr, op, k);
            List<List<Integer>> ans2 = compare(arr, op, k);
            if (!sameAnswer(ans1, ans2)) {
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + " , " + op[j]);
                }
                System.out.println("k为 "+k);
                System.out.println("ans1"+ans1);
                System.out.println("ans2"+ans2);
                System.out.println("出错了！");
                break;
            }
        }
        System.out.println("测试结束");
    }

    // 为了测试
    public static class Data {
        public int[] arr;
        public boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    public static Data randomData(int maxValue, int maxLen) {
        int len = (int) (Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
            op[i] = Math.random() < 0.5 ? true : false;
        }
        return new Data(arr, op);
    }

    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourDaddy whoDaddies = new WhosYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            whoDaddies.operate(i, arr[i], op[i]);
            ans.add(whoDaddies.getDaddies());
        }
        return ans;
    }

    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 没有发生：用户购买数为0并且又退货了
            // 用户之前购买数是0，此时买货事件
            // 用户之前购买数>0， 此时买货
            // 用户之前购买数>0, 此时退货
            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            // 买、卖
            Customer c = map.get(id);
            if (buyOrRefund) {
                c.count++;
            } else {
                c.count--;
            }
            if (c.count == 0) {
                map.remove(id);
            }
            // c
            // 下面做
            if (!cands.contains(c) && !daddy.contains(c)) {
                if (daddy.size() < k) {
                    c.time = i;
                    daddy.add(c);
                } else {
                    c.time = i;
                    cands.add(c);
                }
            }
            cleanZeroBuy(cands);
            cleanZeroBuy(daddy);
            cands.sort(new CandidateComparator());
            daddy.sort(new RewardComparator());
            move(cands, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }

    public static boolean sameAnswer(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> cur1 = ans1.get(i);
            List<Integer> cur2 = ans2.get(i);
            if (cur1.size() != cur2.size()) {
                return false;
            }
            cur1.sort((a, b) -> a - b);
            cur2.sort((a, b) -> a - b);
            for (int j = 0; j < cur1.size(); j++) {
                if (!cur1.get(j).equals(cur2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class WhosYourDaddy {
        private HashMap<Integer, Customer> customers;
        private EnhancedHeap<Customer> candHeap;
        private EnhancedHeap<Customer> daddyHeap;
        private final int daddyLimit;

        public WhosYourDaddy(int limit) {
            customers = new HashMap<Integer, Customer>();
            candHeap = new EnhancedHeap<>(new CandidateComparator());
            daddyHeap = new EnhancedHeap<>(new RewardComparator());
            daddyLimit = limit;
        }

        // 当前处理i号事件，arr[i] -> id,  buyOrRefund
        public void operate(int time, int id, boolean buyOrRefund) {
            if (!buyOrRefund && !customers.containsKey(id)) {
                return;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer c = customers.get(id);
            if (buyOrRefund) {
                c.count++;
            } else {
                c.count--;
            }
            if (c.count == 0) {
                customers.remove(id);
            }
            if (!candHeap.contains(c) && !daddyHeap.contains(c)) {
                if (daddyHeap.size() < daddyLimit) {
                    c.time = time;
                    daddyHeap.push(c);
                } else {
                    c.time = time;
                    candHeap.push(c);
                }
            } else if (candHeap.contains(c)) {
                if (c.count == 0) {
                    candHeap.remove(c);
                } else {
                    candHeap.resign(c);
                }
            } else {
                if (c.count == 0) {
                    daddyHeap.remove(c);
                } else {
                    daddyHeap.resign(c);
                }
            }
            daddyMove(time);
        }

        public List<Integer> getDaddies() {
            List<Customer> customers = daddyHeap.getAllElements();
            List<Integer> ans = new ArrayList<>();
            for (Customer c : customers) {
                ans.add(c.id);
            }
            return ans;
        }

        private void daddyMove(int time) {
            if (candHeap.isEmpty()) {
                return;
            }
            if (daddyHeap.size() < daddyLimit) {
                Customer p = candHeap.pop();
                p.time = time;
                daddyHeap.push(p);
            } else {
                if (candHeap.peek().count > daddyHeap.peek().count) {
                    Customer oldDaddy = daddyHeap.pop();
                    Customer newDaddy = candHeap.pop();
                    oldDaddy.time = time;
                    newDaddy.time = time;
                    daddyHeap.push(newDaddy);
                    candHeap.push(oldDaddy);
                }
            }
        }

    }

    public static void cleanZeroBuy(ArrayList<Customer> arr) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer c : arr) {
            if (c.count!= 0) {
                noZero.add(c);
            }
        }
        arr.clear();
        for (Customer c : noZero) {
            arr.add(c);
        }
    }

    public static void move(ArrayList<Customer> cands, ArrayList<Customer> daddy, int k, int time) {
        if (cands.isEmpty()) {
            return;
        }
        // 候选区不为空
        if (daddy.size() < k) {
            Customer c = cands.get(0);
            c.time = time;
            daddy.add(c);
            cands.remove(0);
        } else { // 等奖区满了，候选区有东西
            if (cands.get(0).count > daddy.get(0).count) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(0);
                Customer newDaddy = cands.get(0);
                cands.remove(0);
                newDaddy.time = time;
                oldDaddy.time = time;
                daddy.add(newDaddy);
                cands.add(oldDaddy);
            }
        }
    }


    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer c : daddy) {
            ans.add(c.id);
        }
        return ans;
    }
}
