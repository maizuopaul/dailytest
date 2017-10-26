package com.touna.leeo.storm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.PropertyConfigurator;
import org.apache.storm.Config;
import org.apache.storm.Constants;
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

public class TimerStormKafka {
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
		String id= "timerWordCounter-storm";
		SpoutConfig spoutConfig = new SpoutConfig(hosts, topic, zkRoot+topic, id);
		spoutConfig.startOffsetTime = kafka.api.OffsetRequest.LatestTime();
		spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
		KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);
		tp.setSpout("kafka_spout",kafkaSpout,2).setNumTasks(6);
		tp.setBolt("splitWord", new SplitWordBolt(),4).setNumTasks(8).localOrShuffleGrouping("kafka_spout");
		tp.setBolt("wordCounterBolt", new WordCounterBolt(),3).setNumTasks(12).localOrShuffleGrouping("splitWord");
		Config conf = new Config();
		try {
			StormSubmitter.submitTopology(id, conf, tp.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException
				| AuthorizationException e) {
			System.out.println(e.getMessage());
			logger.error("error!{}",e);
		}
	}
	
	
	private static class SplitWordBolt extends BaseBasicBolt{
		private static final long serialVersionUID = 1L;
		private OutputCollector collector;
		private Map stormConf; 
		private TopologyContext context;

        @SuppressWarnings("unused")
		public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        	this.stormConf = conf;
        	this.context = context;
        	this.collector = collector;
            
        }
		@Override
		public void execute(Tuple input, BasicOutputCollector collector) {
			try {
				String value = input.getString(0);
				if(null != value && value.length()>0) {
					String[] split = value.split(" ");
					for (String word : split) {
						this.collector.emit(UUID.randomUUID().toString(),input, new Values(word));
					}
					this.collector.ack(input);
				}
			} catch (Exception e) {
				this.collector.fail(input);
			}
		}
		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word"));
		}
		
	}
	
	private static class WordCounterBolt extends BaseBasicBolt{
		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		private OutputCollector collector;
		private Map stormConf; 
		private TopologyContext context;

        @SuppressWarnings("unused")
		public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
        	this.stormConf = conf;
        	this.context = context;
        	this.collector = collector;
            
        }
		
		@Override
		public void execute(Tuple input, BasicOutputCollector collector) {
			try{
				if(input.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)){
					if(hashMap.size()>0){
						FileOutputStream fos = new FileOutputStream(new File("/data/lizehua/logs/storm/timerWordCounter.out"),true);
						
						Set<Entry<String, Integer>> entrySet = hashMap.entrySet();
						
						for (Entry<String, Integer> entry : entrySet) {
							fos.write((entry.getKey()+"="+entry.getValue()).getBytes());
						}
						fos.close();
					}
				}else{
					//获取每一个单词
					String word = input.getStringByField("word");
					//在map中进行统计
					Integer integer = hashMap.get(word);
					if(integer==null){
						integer=0;
					}
					integer++;
					hashMap.put(word, integer);
				}
				this.collector.ack(input);
			}catch(Exception ex){
				logger.error("error!",ex);
				this.collector.fail(input);
			}
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			
		}
		@Override
		public Map<String, Object> getComponentConfiguration() {
			Map<String, Object> hashMap = new java.util.HashMap<>();
			hashMap.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, 5);
			return hashMap;
		}
	}
}
