package redis;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * <dl>
 * <dt>TestClusterRedis</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/2/17</dd>
 * </dl>
 *
 * @auhor cuihc
 */
public class TestClusterRedis {


    private static JedisCluster jc;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7001));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7002));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7003));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7004));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7005));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7006));
        //GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
        //JedisCluster jc = new JedisCluster(jedisClusterNode,2000,100, goConfig);
        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMaxTotal(100);
        cfg.setMaxIdle(20);
        cfg.setMaxWaitMillis(-1);
        cfg.setTestOnBorrow(true);
        jc = new JedisCluster(jedisClusterNode,6000,1000,cfg);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        jc.close();
    }

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String result = jc.set("spn" + i, "n" + i);
        }
        long end = System.currentTimeMillis();
        System.out.println("shardPool SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test1() {
        long start = System.currentTimeMillis();
        final int threadNum = 100;
        final CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 1;i<=threadNum;i++) {

            new Thread(new Runnable() {
                public void run() {
                    Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
                    jedisClusterNode.add(new HostAndPort("10.130.38.216", 7001));
                    jedisClusterNode.add(new HostAndPort("10.130.38.216", 7002));
                    jedisClusterNode.add(new HostAndPort("10.130.38.216", 7003));
                    jedisClusterNode.add(new HostAndPort("10.130.38.216", 7004));
                    jedisClusterNode.add(new HostAndPort("10.130.38.216", 7005));
                    jedisClusterNode.add(new HostAndPort("10.130.38.216", 7006));
                    //GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
                    //JedisCluster jc = new JedisCluster(jedisClusterNode,2000,100, goConfig);
                    JedisPoolConfig cfg = new JedisPoolConfig();
                    cfg.setMaxTotal(400);
                    cfg.setMaxIdle(20);
                    cfg.setMaxWaitMillis(-1);
                    cfg.setTestOnBorrow(true);
                    JedisCluster jc = new JedisCluster(jedisClusterNode,6000,1000,cfg);
                    for (int j= 0 ; j<100000 / threadNum; j++) {
                        String result = jc.set(UUID.randomUUID().toString(), "n" + j);
                    }
                    latch.countDown();
                    try {
                        jc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("shardPool SET: " + ((end - start)/1000.0) + " seconds");
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        final int threadNum = 100;
        final CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 1;i<=threadNum;i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j= 0 ; j<100000 / threadNum; j++) {
                        String result = jc.set(UUID.randomUUID().toString(), "n" + j);
                    }
                    latch.countDown();
                }
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("shardPool SET: " + ((end - start)/1000.0) + " seconds");
    }

    public static void main(String[] args) {

        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7001));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7002));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7003));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7004));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7005));
        jedisClusterNode.add(new HostAndPort("10.130.38.216", 7006));
        //GenericObjectPoolConfig goConfig = new GenericObjectPoolConfig();
        //JedisCluster jc = new JedisCluster(jedisClusterNode,2000,100, goConfig);
        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMaxTotal(100);
        cfg.setMaxIdle(20);
        cfg.setMaxWaitMillis(-1);
        cfg.setTestOnBorrow(true);
        JedisCluster jc = new JedisCluster(jedisClusterNode,6000,1000,cfg);

        System.out.println(jc.set("age","20"));
        System.out.println(jc.set("sex","男"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("name"));
        System.out.println(jc.get("age"));
        System.out.println(jc.get("sex"));
        try {
            jc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
