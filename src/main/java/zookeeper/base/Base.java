package zookeeper.base;

import org.apache.zookeeper.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * <dl>
 * <dt>Base</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/3</dd>
 * </dl>
 *
 * @author cuihc
 */
public class Base {

    static  final String host = "10.130.38.216:2181";
    private static final Logger logger = LoggerFactory.getLogger(Base.class);

    private ZooKeeper zk = null;
    final CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void before() throws InterruptedException {

        try {
            zk = new ZooKeeper(host, 10000, new Watcher() {
                /*
                 *连接成功收到事件取消延迟线程
                 */
                public void process(WatchedEvent event) {
                    logger.info("进入watch...EventType=" + event.getType() + ",state=" + event.getState() + ",path=" + event.getPath());
                    if (event.getType().equals(Event.EventType.None)
                            && event.getState().equals(
                            Event.KeeperState.SyncConnected)) {
                        logger.info("Zookeeper 连接成功..");
                        countDownLatch.countDown();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.await();
    }

    @After
    public void after() throws InterruptedException {
        synchronized (host) {
            host.wait();
        }
        zk.close();
    }

    @Test
    public void run() throws InterruptedException, KeeperException {

        //创建父节点
//        zk.create("/testRoot", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/fgewfwe", "testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);


    }

    @Test
    public void testWatcher() throws KeeperException, InterruptedException {
        //true是注册watch，并且使用默认的处理对象，就是new ZooKeeper 中的Watcher，也可以new一个新的，注意watcher注册触发了一次就失效，一般做法是在watcher方法中再次注册，类似于递归
        zk.exists("/mytest",true);
         zk.create("/mytest","mytest".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
             public void processResult(int rc, String path, Object ctx, String name) {
                 if (rc == KeeperException.Code.OK.intValue()) {
                     logger.info("path=" + path + ",ctx=" + ctx + ",name=" + name);
                 }
             }
         },"theCtx");
    }
}
