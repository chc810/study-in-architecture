package rocketmq.quickstart;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <dl>
 * <dt>SimpleStart</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/2/27</dd>
 * </dl>
 *
 * @author cuihc
 */
public class SimpleStart {

    private static final Logger logger = LoggerFactory.getLogger(SimpleStart.class);

    @Test
    public void producer() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //groupname，在一个jvm唯一
        DefaultMQProducer producer = new DefaultMQProducer("testProducerGroup");
        producer.setNamesrvAddr("10.130.38.216:9876;10.130.38.217:9876");

        //非阻塞
        producer.start();

        for (int i =1;i<=10;i++) {
            Message msg = new Message(
                    "TopicQuickStart",  //topic
                    "TagA",      //tag，过滤作用
                    ("Hello RocketMq from " + i).getBytes()   //body
            );
            SendResult result = producer.send(msg);
            logger.info(result.toString());
        }
    }

    @Test
    public void consumer() throws MQClientException, InterruptedException {
        //groupname，在一个jvm唯一，消费端可以根据此group来负载均衡
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testcomsumerGroup");
        consumer.setNamesrvAddr("10.130.38.216:9876;10.130.38.217:9876");

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("TopicQuickStart","*");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                logger.info("-----------------------");
                for (MessageExt message : msgs) {
                    logger.info(String.valueOf(message));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //非阻塞
        consumer.start();
        Thread.sleep(3000000);
    }
}
