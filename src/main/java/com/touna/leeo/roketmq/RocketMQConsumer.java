package com.touna.leeo.roketmq;

import java.util.Iterator;
import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;



public class RocketMQConsumer {
	public static void main(String[] args) throws MQClientException{
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test_consumer-lzh");
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setNamesrvAddr("10.0.4.78:9876");
        consumer.setInstanceName("test_consumer-lzh");
        
        /**
         * 订阅指定topic下所有消息<br>
         * 注意：一个consumer对象可以订阅多个topic
         */
        consumer.subscribe("MQ_LOAN_RM_CREDIT_TOPIC_BIZ_STATISTICS_RESULT","*");
        consumer.setConsumeThreadMax(10);
        consumer.setConsumeThreadMin(10);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                    ConsumeConcurrentlyContext context) {
            		try{
            			System.out.println(Thread.currentThread().getName()
                                 +" Receive New Messages-size: " + msgs.size());
            			Iterator<MessageExt> iterator = msgs.iterator();
       					while(iterator.hasNext()){
       						MessageExt msg = iterator.next();
       						String topic = msg.getTopic();
       						String keys = msg.getKeys();
       						byte[] body = msg.getBody();
       						System.out.println("topic:"+topic+"\tkeys="+keys+"\tbody="+new String(body));
                        }
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            		}catch(Exception x){
            			x.printStackTrace();
            			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            		}
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
	}
}
