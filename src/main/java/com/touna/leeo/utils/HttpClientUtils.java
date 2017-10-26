package com.touna.leeo.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *                       
 * @Filename HttpClientUtils.java
 *
 * @Description httpclient 工具类
 *
 * @Version 1.0
 *
 * @Author Lijie
 *
 * @Email lijiewj39069@touna.cn
 *       
 * @History
 *<li>Author: Lijie</li>
 *<li>Date: 2017年3月1日</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 *
 */
public class HttpClientUtils {
	
	/**
	 * logger
	 */
	private final static Logger	logger	= LoggerFactory.getLogger(HttpClientUtils.class);
	
	/**
	 * httpclient get请求
	 * 
	 * @throws Exception
	 */
	public static String sendGet(String api, Map<String, String> bodys, Map<String, String> headers)
																									throws Exception {
		
		// 声明返回值
		String resStr = null;
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		URIBuilder uri = new URIBuilder(api);
		
		// 设置参数
		if(null!=bodys && bodys.size()>0){
			for (Map.Entry<String, String> entry : bodys.entrySet()) {
				
				// 添加表单数据
				uri.addParameter(entry.getKey(), entry.getValue());
			}
		}
		HttpGet hg = new HttpGet(uri.build());
		
		// 设置请求的报文头部的编码
		hg.setHeader(new BasicHeader("Content-Type",
			"application/x-www-form-urlencoded; charset=utf-8"));
		
		// 设置期望服务端返回的编码
		hg.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));
		
		// 设置header
		if(null!=headers && headers.size()>0){
			for (Map.Entry<String, String> entry1 : headers.entrySet()) {
				
				// 设置头信息
				hg.setHeader(new BasicHeader(entry1.getKey(), entry1.getValue()));
			}
		}
		// 请求服务
		CloseableHttpResponse response = client.execute(hg);
		
		// 获取响应码
		int statusCode = response.getStatusLine().getStatusCode();
		
		if (statusCode == 200) {
			
			HttpEntity entity = response.getEntity();
			
			resStr = EntityUtils.toString(entity, "utf-8");
			
			logger.info("请求成功,请求返回内容为:{} ", resStr);
			
		} else {
			
			logger.info("请求失败,错误码为:{} ", statusCode);
		}
		
		response.close();
		client.close();
		
		return resStr;
	}
	
	/**
	 * httpclient post 请求
	 * 
	 * @param api
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static String sendPost(String api, Map<String, String> bodys, Map<String, String> headers)
																										throws Exception {
		
		// 声明返回值
		String resStr = null;
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpPost post = new HttpPost(api);
		
		List<NameValuePair> formList = new ArrayList<>();
		
		// 设置参数
		for (Map.Entry<String, String> entry : bodys.entrySet()) {
			
			// 添加表单数据
			formList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		// 包装成一个Entity对象
		StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
		
		// 设置请求的内容
		post.setEntity(entity);
		
		// 设置请求的报文头部的编码
		post.setHeader(new BasicHeader("Content-Type",
			"application/x-www-form-urlencoded; charset=utf-8"));
		
		// 设置期望服务端返回的编码
		post.setHeader(new BasicHeader("Accept", "application/json;charset=utf-8"));
		
		// 设置header
		for (Map.Entry<String, String> entry1 : headers.entrySet()) {
			
			// 设置头信息
			post.setHeader(new BasicHeader(entry1.getKey(), entry1.getValue()));
		}
		
		CloseableHttpResponse response = client.execute(post);
		
		// 获取响应码
		int statusCode = response.getStatusLine().getStatusCode();
		
		if (statusCode == 200) {
			
			resStr = EntityUtils.toString(response.getEntity(), "utf-8");
			
			logger.info("请求成功,请求返回内容为:{} ", resStr);
			
		} else {
			
			logger.info("请求失败,错误码为:{} ", statusCode);
		}
		
		response.close();
		client.close();
		return resStr;
	}
	
	/**
	 * httpclient post 请求
	 * 
	 * @param api
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static byte[] sendPostRtnByteArr(String api, Map<String, String> bodys, Map<String, String> headers)
			throws Exception {
		
		// 声明返回值
		byte[] resByteArr = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(api);
		
		// 设置参数
		if(null != bodys && !bodys.isEmpty()){
			List<NameValuePair> formList = new ArrayList<>();
			for (Map.Entry<String, String> entry : bodys.entrySet()) {
				// 添加表单数据
				formList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 包装成一个Entity对象
			StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
			// 设置请求的内容
			post.setEntity(entity);
		}
		// 设置header
		if(null != headers && !headers.isEmpty()){
			
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				// 设置头信息
				post.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
			}
		}
		CloseableHttpResponse response = client.execute(post);
		// 获取响应码
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			resByteArr = EntityUtils.toByteArray(response.getEntity());
			logger.info("请求成功,请求返回内容为:{} ", resByteArr);
		} else {
			logger.info("请求失败,错误码为:{} ", statusCode);
		}
		response.close();
		client.close();
		return resByteArr;
	}
	
	/**
	 * httpclient post 请求
	 * 
	 * @param api
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public static String sendPostByMutiPart(String api, Map<String, Object> bodys, Map<String, String> headers)
			throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(api);
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		
		// 设置header
		if(null != headers && !headers.isEmpty()){
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				// 设置头信息
				httpPost.setHeader(new BasicHeader(entry.getKey(), entry.getValue()));
			}
		}
		// 设置参数
		if(null != bodys && !bodys.isEmpty()){
			for (Map.Entry<String, Object> entry : bodys.entrySet()) {

				if(entry.getValue() instanceof byte[]){
					 builder.addBinaryBody(entry.getKey(), (byte[])entry.getValue());// 文件流
				}else if(entry.getValue() instanceof String){
					builder.addTextBody(entry.getKey(), (String)entry.getValue());
				}else if(entry.getValue() instanceof String[]){
					String[] arr = (String[]) entry.getValue();
					String key = entry.getKey();
					for (String data : arr) {
						builder.addTextBody(key, data);
					}
				}else if(entry.getValue() instanceof Collection){
					@SuppressWarnings("rawtypes")
					Collection arr = (Collection) entry.getValue();
					String key = entry.getKey();
					for (Object object : arr) {
						if(object instanceof byte[]){
							 builder.addBinaryBody(key, (byte[])object);// 文件流
						}else if(object instanceof String){
							builder.addTextBody(key, (String)object);
						}else{
							builder.addTextBody(key, object.toString());
						}
					}
				}else{
					builder.addTextBody(entry.getKey(), entry.getValue().toString());
				}
			}
		}
		String rtnMsg = null;
		HttpEntity entity = builder.build();
		httpPost.setEntity(entity);
		CloseableHttpResponse response = client.execute(httpPost);// 执行提交
		// 获取响应码
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			rtnMsg = EntityUtils.toString(response.getEntity(), "utf-8");
			logger.info("请求成功,请求返回内容为:{} ", rtnMsg);
			return rtnMsg;
		} else {
			logger.info("请求失败,错误码为:{} ", statusCode);
		}
		response.close();
		client.close();
		return null;
	}
	
}
