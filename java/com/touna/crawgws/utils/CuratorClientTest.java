package com.touna.crawgws.utils;

import org.junit.Before;
import org.junit.Test;

public class CuratorClientTest {
	String zkAddr = null;
	String path = null;
	String data = null;
	
	@Before
	public void init(){
		zkAddr = "10.0.4.141:2181,10.0.4.142:2181,10.0.4.143:2181";
		path = "/MTDCS/TASK/1101/status";
		data = "running";
	}
	
	@Test
	public void testCreatePath() throws InterruptedException{
		CuratorClientUtil.createPath(zkAddr, path, data);
	}
	
	@Test
	public void testGetData(){
		String dataStr = CuratorClientUtil.getDataStr(zkAddr, path);
		byte[] data2 = CuratorClientUtil.getData(zkAddr, path);
		System.out.println(dataStr);
		System.out.println(new String(data2));
	}
	@Test
	public void testSetData(){
		 CuratorClientUtil.setData(zkAddr, path, "running");
		 String dataStr = CuratorClientUtil.getDataStr(zkAddr, path);
		 System.out.println(dataStr);
	}
}
