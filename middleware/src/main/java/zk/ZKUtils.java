package zk;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZKUtils {
    static ZooKeeper zk ;
//    static ZKConf conf ;
//    static DefaultWatch watch ;
//    static CountDownLatch c = new CountDownLatch(1);
//
//    public static void setConf(ZKConf conf) {
//        com.msb.zookeeper.configurationcenter.ZKUtils.conf = conf;
//    }
//
//    public static void setWatch(DefaultWatch watch) {
//        watch.setInit(c);
//        ZKUtils.watch = watch;
//
//    }
//
//    public static ZooKeeper getZK(){
////
//        try {
//            zk = new ZooKeeper(conf.getAddress(),conf.getSessionTime(),watch);
//            c.await();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return zk;
//    }

    public static void closeZK(){
        if(zk != null){
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ZooKeeper getZK(String address,int sessionTime) {
        try {
            zk = new ZooKeeper(address,sessionTime,new DefaultWatch());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zk;
    }
}
