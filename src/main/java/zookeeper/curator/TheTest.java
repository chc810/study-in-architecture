package zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/3/5 0005.
 */
public class TheTest {
    private static final Logger logger = LoggerFactory.getLogger(TheTest.class);


    /** zookeeper地址 */
    static final String CONNECT_ADDR = "10.130.38.216:2181";
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
    public void testNodeCache() throws Exception {
        //4 建立一个cache缓存
        final NodeCache cache = new NodeCache(cf, "/super", false);
        cache.start(true);
        cache.getListenable().addListener(new NodeCacheListener() {
            /**
             * <B>方法名称：</B>nodeChanged<BR>
             * <B>概要说明：</B>触发事件为创建节点和更新节点，删除时cache.getCurrentData() == null<BR>
             * @see org.apache.curator.framework.recipes.cache.NodeCacheListener#nodeChanged()
             */
            public void nodeChanged() throws Exception {
                logger.info("cache=" + cache);
                logger.info("cache.getCurrentData()=" + cache.getCurrentData());
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

        Thread.sleep(1000);
        cf.delete().forPath("/super");

        Thread.sleep(Integer.MAX_VALUE);
    }

    //此cache只能监听子节点的变化，本身节点不会被触发
    @Test
    public void testPathChildrenCache() throws Exception {
        //4 建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
        PathChildrenCache cache = new PathChildrenCache(cf, "/super", true);
        //5 在初始化的时候就进行缓存监听
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            /**
             * <B>方法名称：</B>监听子节点变更<BR>
             * <B>概要说明：</B>新建、修改、删除<BR>
             * @see org.apache.curator.framework.recipes.cache.PathChildrenCacheListener#childEvent(org.apache.curator.framework.CuratorFramework, org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent)
             */
            public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED :" + event.getData().getPath());
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED :" + event.getData().getPath());
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED :" + event.getData().getPath());
                        break;
                    default:
                        break;
                }
            }
        });

        //创建本身节点不发生变化
//        cf.create().forPath("/super", "init".getBytes());
//
//        //添加子节点
//        Thread.sleep(1000);
//        cf.create().forPath("/super/c1", "c1内容".getBytes());
//        Thread.sleep(1000);
//        cf.create().forPath("/super/c2", "c2内容".getBytes());
//
//        //修改子节点
//        Thread.sleep(1000);
//        cf.setData().forPath("/super/c1", "c1更新内容".getBytes());
//
//        //删除子节点
//        Thread.sleep(1000);
//        cf.delete().forPath("/super/c2");

        //删除本身节点
//        Thread.sleep(1000);
//        cf.delete().deletingChildrenIfNeeded().forPath("/super");

        Thread.sleep(Integer.MAX_VALUE);
    }

    //这个是上面两个的结合体，可以感知孙子节点和自己节点本身
    @Test
    public void testTreeCache() throws Exception {
        //4 建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
        TreeCache  cache = new TreeCache (cf, "/super");
        //5 在初始化的时候就进行缓存监听
        cache.getListenable().addListener(new TreeCacheListener() {
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                ChildData data = event.getData();
                if(data !=null){
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println("NODE_ADDED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                            System.out.println("NODE_REMOVED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;
                        case NODE_UPDATED:
                            System.out.println("NODE_UPDATED : "+ data.getPath() +"  数据:"+ new String(data.getData()));
                            break;

                        default:
                            break;
                    }
                }else{
                    System.out.println( "data is null : "+ event.getType());
                }
            }
        });

        //开始监听
        cache.start();

        //创建本身节点不发生变化
//        cf.create().forPath("/super", "init".getBytes());
//
//        //添加子节点
//        Thread.sleep(1000);
//        cf.create().forPath("/super/c1", "c1内容".getBytes());
//        Thread.sleep(1000);
//        cf.create().forPath("/super/c2", "c2内容".getBytes());
//
//        //修改子节点
//        Thread.sleep(1000);
//        cf.setData().forPath("/super/c1", "c1更新内容".getBytes());
//
//        //删除子节点
//        Thread.sleep(1000);
//        cf.delete().forPath("/super/c2");

        //删除本身节点
//        Thread.sleep(1000);
//        cf.delete().deletingChildrenIfNeeded().forPath("/super");

        Thread.sleep(Integer.MAX_VALUE);
    }

}
