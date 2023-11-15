package zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WatchCallBack implements Watcher, AsyncCallback.StringCallback,AsyncCallback.Children2Callback,AsyncCallback.StatCallback,AsyncCallback.DataCallback {
    final Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
    String threadName;
    String nodeName;
    ZooKeeper zk;
    CountDownLatch cc = new CountDownLatch(1);;

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    public void tryLock() {
        zk.create("/lock",threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL,this,threadName);
        try {
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //ChildrenCallback,getchildren
    @Override
    public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
        if (CollectionUtils.isEmpty(list)){
            logger.info("list is null");
            return;
        }
        Collections.sort(list);
        int index = list.indexOf(nodeName);
//        System.out.println("list元素个数 " + list.size());
//        printList(list);
//        System.out.println("index为" +index);
        if (index == 0){
            System.out.println("threadName: " + threadName + " nodeName:" + nodeName +" i am first...");
            try {
                zk.setData("/",threadName.getBytes(),-1);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cc.countDown();
        }else {
//            System.out.println("超出界限之前 index 为" + index);
            System.out.println("threadName: " + threadName + " nodeName:" + nodeName +" watch "+list.get(index-1));
            try {
                zk.exists("/" + list.get(index-1),this);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printList(List<String> list) {
        if (CollectionUtils.isEmpty(list)){
            return;
        }
        for (String str : list){
            System.out.print(" " + str);
        }
        System.out.println();

    }

    //StringCallback,create
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        nodeName = s1.substring(1);
        System.out.println(o.toString()+" create path: "+ s1);
        zk.getChildren("/",false,this,threadName);
    }

    //Watcher
    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()){
            case NodeDeleted:
                zk.getChildren("/",false,this,threadName);
                break;
        }

    }

    public void getRootData() throws KeeperException, InterruptedException {
        logger.info(new String(zk.getData("/",false,new Stat())));;
    }

    public void unLock() {
        try {
            logger.info("threadName: " + threadName + " nodeName:" + nodeName + "unLock");
            zk.delete("/" + nodeName,-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    //StatCallback
    @Override
    public void processResult(int i, String s, Object o, Stat stat) {

    }

    //DataCallback
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {

    }
}
