package zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zookeeper.base.Base;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class TheTest {
    private static final Logger logger = LoggerFactory.getLogger(TheTest.class);


    /** zookeeper地址 */
    static final String CONNECT_ADDR = "127.0.0.1:2181";
    /** session超时时间 */
    static final int SESSION_OUTTIME = 25000;//ms

    CuratorFramework cf = null;
    @Before
    public void before() {
        //1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        //2 通过工厂创建连接
        cf = CuratorFrameworkFactory.builder()
                .connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_OUTTIME)
                .retryPolicy(retryPolicy)
                .build();

        //3 建立连接
        cf.start();
    }

    @After
    public void after() throws InterruptedException {
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    public void test1() throws Exception {
        //4 建立一个cache缓存
        final NodeCache cache = new NodeCache(cf, "/super", false);
        cache.start(true);
        cache.getListenable().addListener(new NodeCacheListener() {
            /**
             * <B>方法名称：</B>nodeChanged<BR>
             * <B>概要说明：</B>触发事件为创建节点和更新节点，在删除节点的时候并不触发此操作。<BR>
             * @see org.apache.curator.framework.recipes.cache.NodeCacheListener#nodeChanged()
             */
            public void nodeChanged() throws Exception {
                logger.info("路径为：" + cache.getCurrentData().getPath());
                logger.info("数据为：" + new String(cache.getCurrentData().getData()));
                logger.info("状态为：" + cache.getCurrentData().getStat());
                logger.info("---------------------------------------");
            }
        });
        Thread.sleep(1000);
        cf.create().forPath("/super", "123".getBytes());

        Thread.sleep(1000);
        cf.setData().forPath("/super", "456".getBytes());

//        Thread.sleep(1000);
//        cf.delete().forPath("/super");

        Thread.sleep(Integer.MAX_VALUE);
    }


}
