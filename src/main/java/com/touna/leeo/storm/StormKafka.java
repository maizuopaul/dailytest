package com.touna.leeo.storm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StormKafka {
	private static Logger logger = LoggerFactory.getLogger("StormKafka");
	static {  
		String filePathName = System.getProperty("user.dir") + File.separator  + File.separator  + "log4j.properties";
		File file=new File(filePathName);  
		if(file.exists()){
			PropertyConfigurator.configure(filePathName);  
		}
	} 	
	public static void main(String[] args) {
		final TopologyBuilder tp = new TopologyBuilder();
		BrokerHosts hosts = new ZkHosts("10.0.4.141:2182,10.0.4.142:2182,10.0.4.143:2182");
		String topic = "kafka2storm";
		String zkRoot ="/";
		String id= "43c017a2-3a75-4b14-a60f-22054852bbe4";
		SpoutConfig spoutConfig = new SpoutConfig(hosts, topic, zkRoot+topic, id);
		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		tp.setSpout("kafka_spout",kafkaSpout,2).setNumTasks(6);
		tp.setBolt("bolt", new Mybolt(),4).setNumTasks(12).shuffleGrouping("kafka_spout");
		Config conf = new Config();
		try {
			
			StormSubmitter.submitTopology(id, conf, tp.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException
				| AuthorizationException e) {
			System.out.println(e.getMessage());
			logger.error("error!{}",e);
		}
	}
	
	
	private static class Mybolt extends BaseBasicBolt{
		private static final long serialVersionUID = 1L;
		private OutputCollector collector;
		private Map stormConf; 
		private TopologyContext context;

        @SuppressWarnings("unused")
		public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        	this.stormConf = stormConf;
        	this.context = context;
        	this.collector = collector;
            
        }
		@Override
		public void execute(Tuple input, BasicOutputCollector collector) {
			String value = input.getString(0);
			try {
				FileOutputStream fos = new FileOutputStream(new File("/data/lizehua/logs/storm/kafkastorm2.out"),true);
				fos.write(value.getBytes());
				fos.close();
				this.collector.ack(input);
				this.collector.emit(input, new Values(input.getString(0)));
			} catch (FileNotFoundException e) {
				this.collector.fail(input);
				logger.error("error!{}",e);
			} catch (IOException e) {
				this.collector.fail(input);
				logger.error("error!{}",e);
			}
		}
		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("message"));
		}
	}
}
