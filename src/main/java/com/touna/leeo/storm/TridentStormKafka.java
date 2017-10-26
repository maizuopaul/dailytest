package com.touna.leeo.storm;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.UUID;

import org.apache.log4j.PropertyConfigurator;
import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.kafka.trident.TransactionalTridentKafkaSpout;
import org.apache.storm.kafka.trident.TridentKafkaConfig;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.trident.Stream;
import org.apache.storm.trident.TridentTopology;
import org.apache.storm.trident.operation.BaseFilter;
import org.apache.storm.trident.operation.builtin.Count;
import org.apache.storm.trident.testing.Split;
import org.apache.storm.trident.tuple.TridentTuple;
import org.apache.storm.tuple.Fields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TridentStormKafka {
	private static Logger logger = LoggerFactory.getLogger("StormKafka");
	static {  
		String filePathName = System.getProperty("user.dir") + File.separator  + File.separator  + "log4j.properties";
		File file=new File(filePathName);  
		if(file.exists()){
			PropertyConfigurator.configure(filePathName);  
		}
	} 	
	public static void main(String[] args) {
		BrokerHosts hosts = new ZkHosts("10.0.4.141:2182,10.0.4.142:2182,10.0.4.143:2182");
		String topic = "kafka2storm";
		String txId = "kfk_"+UUID.randomUUID().toString();
		
		TridentTopology topology = new TridentTopology();  
		TridentKafkaConfig spoutConf = new TridentKafkaConfig(hosts, topic,txId);  
		spoutConf.scheme = new SchemeAsMultiScheme(new StringScheme());  
		TransactionalTridentKafkaSpout spout = new TransactionalTridentKafkaSpout(spoutConf);
		Stream newStream = topology.newStream(txId, spout).parallelismHint(3).shuffle();
		Stream word = newStream.each(new Split(), new Fields("word"));
		word.groupBy(new Fields("word")).aggregate(new Fields("word"), new Count(),new Fields("sum"))
		.parallelismHint(6).each(new Fields("sum"), new PrintFilter());
		
	    Config config = new Config();  
        config.setDebug(false);  
        config.setNumWorkers(2);
        try {
			StormSubmitter.submitTopology(txId, config,topology.build());
		} catch (AlreadyAliveException | InvalidTopologyException
				| AuthorizationException e) {
			e.printStackTrace();
			logger.error("error!{}",e);
		}  
	}
	
	static class PrintFilter extends BaseFilter{
		private static final long serialVersionUID = 1L;

		@Override
		public boolean isKeep(TridentTuple tuple) {
			try{
				Fields fields = tuple.getFields();
				Iterator<String> iterator = fields.iterator();
				while(iterator.hasNext()){
					String next = iterator.next();
					if(null != next){
						FileOutputStream fos = new FileOutputStream(new File("/data/lizehua/logs/storm/tridentTest.out"),true);
						fos.write(next.getBytes());
						fos.close();
					}
				}
			}catch(Exception ex){
				logger.error("error!{}",ex);
			}
			return false;
		}
	}
}
