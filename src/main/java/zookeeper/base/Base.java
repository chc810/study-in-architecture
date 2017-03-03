package zookeeper.base;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
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

    @Test
    public void run() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            ZooKeeper zookeeper = new ZooKeeper(host, 10000, new Watcher() {
                /*
                 *连接成功收到事件取消延迟线程
                 */
                public void process(WatchedEvent event) {
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
        logger.info("出来了....");
    }
}
