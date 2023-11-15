package zk;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestLock {


    ZooKeeper zk;
    DefaultWatch defaultWatch;

    @Before
    public void conn(){
        String address = "192.168.184.129:2181,192.168.184.130:2181,192.168.184.131:2181,192.168.184.132:2181/testLock";
        zk = ZKUtils.getZK(address,1000);
    }

    @After
    public void close(){
        ZKUtils.closeZK();
    }

    @Test
    public void testlock(){
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    WatchCallBack watchCallBack = new WatchCallBack();
                    watchCallBack.setZk(zk);
                    String name = Thread.currentThread().getName();
                    watchCallBack.setThreadName(name);

                    try {
                        //tryLock
                        watchCallBack.tryLock();
                        System.out.println(name + " at work");
                        watchCallBack.getRootData();
//                        Thread.sleep(1000);
                        //unLock
                        watchCallBack.unLock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
       while(true){

       }
    }
}
