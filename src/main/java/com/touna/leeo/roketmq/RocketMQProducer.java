package com.touna.leeo.roketmq;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;



public class RocketMQProducer {
	 public static void main(String[] args) throws Exception {
		  /*DefaultMQProducer credit = new DefaultMQProducer("credit_topic");
	      DefaultMQProducer loan = new DefaultMQProducer("MQ_FIN_TOPIC_LOAN_SUCCESS_NOTICE");
	      DefaultMQProducer register = new DefaultMQProducer("register_Topic");
	      DefaultMQProducer test = new DefaultMQProducer(UUID.randomUUID().toString());
	      
	      credit.setNamesrvAddr("10.0.4.144:9876");
	      credit.setInstanceName("credit_topic");
	      credit.start();
	      
	      register.setNamesrvAddr("10.0.4.144:9876");
	      register.start();
	      
	      loan.setNamesrvAddr("10.0.4.144:9876");
	      loan.start();
	      
	      test.setNamesrvAddr("10.0.4.144:9876");
	      test.start();
	      
	     Random random = new Random();
	     String jsonString = null;
	         
	         for (int i = 0; i < 100; i++) {
		        	CreditDetailMqVo vo = new CreditDetailMqVo();
		        	vo.setStatus(200);
		        	vo.setMessage("success");
		        	CreditDetail data = new CreditDetail();
		        	data.setUuid(UUID.randomUUID().toString());
		        	data.setResultStatus(1);
		        	data.setProvince("江西省");
		        	data.setMobile("18565750665");
		        	data.setLongitude("102.12");
		        	data.setLatitude("45.25");
		        	data.setDecisionTime(new Date());
		        	data.setCustName("leeo");
		        	data.setCustIdCard("20111904");
		        	data.setCustId("1254897");
		        	data.setCreditBalance(12458.02);
		        	data.setCode("UPYTYRTU");
		        	data.setCity("抚州市");
		        	data.setArea("芙荣镇");
					data.setAuditResult(Math.abs(random.nextInt())%2);
					data.setInsertTime(new Date());
		        	vo.setData(data );
		        	jsonString = JSONObject.toJSONString(vo);
	             Message msg = new Message("credit_topic",null,UUID.randomUUID().toString(),jsonString.getBytes("UTF-8"));
	             credit.send(msg, new SendCallback() {
	                 @Override
	                 public void onSuccess(SendResult sendResult) {
	                 	System.out.println(sendResult.getMsgId());
	                 }
	                 @Override
	                 public void onException(Throwable e) {
	                     e.printStackTrace();
	                 }
	             });
	             
	             LoanDetailVo loanDataVo = new LoanDetailVo();
	         	LoanDetail loanData = new LoanDetail();
	         	loanData.setAmount(1.152D);
	         	loanData.setInsertTime(new Date());
	         	loanData.setOperateDate(new Date());
	         	loanData.setOrderId(UUID.randomUUID().toString());
	         	loanData.setType("1");
	         	loanData.setUuid(UUID.randomUUID().toString());
	 			loanDataVo.setData(loanData);
	 			jsonString = JSONObject.toJSONString(loanDataVo);
	 			msg = new Message("MQ_FIN_TOPIC_LOAN_SUCCESS_NOTICE",null,UUID.randomUUID().toString(),jsonString.getBytes("UTF-8"));
	             loan.send(msg, new SendCallback() {
	         	  @Override
	               public void onSuccess(SendResult sendResult) {
	               	System.out.println(sendResult.getMsgId());
	               }
	               @Override
	               public void onException(Throwable e) {
	                   e.printStackTrace();
	               }
	           });
	             
	           RegDetail reg = new RegDetail();
	           reg.setUuid(UUID.randomUUID().toString());
	           reg.setRegTime(new Date());
	           reg.setRegMobileNum("18565750665");
	           reg.setRegCode("200");
	           reg.setProvince("广东省");
	           reg.setCity("深圳市");
	           reg.setArea("宝安区");
	           reg.setLongitude("102.32");
	           reg.setLatitude("45.23");
	           
	           jsonString = JSONObject.toJSONString(reg);
	           msg = new Message("register_Topic",null,UUID.randomUUID().toString(),jsonString.getBytes("UTF-8"));
	           register.send(msg, new SendCallback() {
	         	  @Override
		                public void onSuccess(SendResult sendResult) {
		                	System.out.println(sendResult.getMsgId());
		                }
		                @Override
		                public void onException(Throwable e) {
		                    e.printStackTrace();
		                }
	           });
	           
	           msg = new Message("TopicTest1",null,UUID.randomUUID().toString(),UUID.randomUUID().toString().getBytes("UTF-8"));
	           test.send(msg, new SendCallback() {
	        	   @Override
	        	   public void onSuccess(SendResult sendResult) {
	        		   System.out.println(sendResult.getMsgId());
	        	   }
	        	   @Override
	        	   public void onException(Throwable e) {
	        		   e.printStackTrace();
	        	   }
	           });
		   }
	         credit.shutdown();
	         register.shutdown();
	         loan.shutdown();
	         test.shutdown();*/
		 
		 
		 DefaultMQProducer test = new DefaultMQProducer(UUID.randomUUID().toString());
	      
	     
	      
	      test.setNamesrvAddr("10.0.4.64:9876");
	      test.start();
		 
	      Message msg = new Message("MQ_LOAN_RM_CREDIT_TOPIC_BIZ_STATISTICS_RESULT",UUID.randomUUID().toString(),"leeo".getBytes("UTF-8"));
	      test.send(msg, new SendCallback() {
        	  @Override
	                public void onSuccess(SendResult sendResult) {
	                	System.out.println(sendResult.getMsgId());
	                }
	                @Override
	                public void onException(Throwable e) {
	                    e.printStackTrace();
	                }
          });
		 
	      test.shutdown();
		 
		 
		 
	    }
}
