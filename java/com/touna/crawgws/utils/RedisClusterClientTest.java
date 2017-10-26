package com.touna.crawgws.utils;

import org.junit.Test;

import redis.clients.jedis.JedisCluster;

public class RedisClusterClientTest {
	
	@Test
	public void testGetSingleton(){
		JedisCluster singleton = JedisClusterUtil.getSingleton();
		/*for(int i = 0;i < 1;i++){
			String str ="{\"contents\":[{\"crawlstatus\":\"0\",\"extra_url\":[\"www.news.baidu.com\",\"www.news.sina.com\",\"www.news.souhu.com\"],\"fetchtimes\":1,\"headers\":{\"time\":\"1497926058072\",\"app-key\":\"app-key\"},\"httpstatus\":\"200\",\"respbody\":\"Ynl0ZXMgZGF0YSBmb3IgYmFzZTY0\",\"url\":\"www.baidu.com\"},{\"crawlstatus\":\"0\",\"extra_url\":[\"www.news.baidu.com\",\"www.news.sina.com\",\"www.news.souhu.com\"],\"fetchtimes\":1,\"headers\":{\"time\":\"1497926058072\",\"app-key\":\"app-key\"},\"httpstatus\":\"200\",\"respbody\":\"Ynl0ZXMgZGF0YSBmb3IgYmFzZTY0\",\"url\":\"www.baidu.com\"},{\"crawlstatus\":\"0\",\"extra_url\":[\"www.news.baidu.com\",\"www.news.sina.com\",\"www.news.souhu.com\"],\"fetchtimes\":1,\"headers\":{\"time\":\"1497926058072\",\"app-key\":\"app-key\"},\"httpstatus\":\"200\",\"respbody\":\"Ynl0ZXMgZGF0YSBmb3IgYmFzZTY0\",\"url\":\"www.baidu.com\"}],\"taskId\":\"cccc\",\"type\":\"simple-http\"}";
			System.out.println(singleton.lpush("MTDCS_REDIS_RESULT_lzh", str));
		}
		System.out.println(singleton.lpop("MTDCS_REDIS_RESULT_lzh"));*/
		while(true){
			System.out.println(singleton.lpop("MTDCS_REDIS_RESULT"));
//			System.out.println(singleton.lpop("MTDCS_REDIS_SEEDS"));
		}
	}
}	
