package com.tinypace.mobistore.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 远程访问工具
 * @author Ethan
 *
 */
public class WebClientUtil {
		public static	Log logger = LogFactory.getLog(WebClientUtil.class);
		
		public static final String ContentType = "application/json";
		
		public static final String Encoding = "UTF-8";
	
	   public  static String post(String url, String json) {
	    	String resultJson = "";
	    	// 创建默认的httpClient实例.
	        CloseableHttpClient httpclient = HttpClients.createDefault();
	        
	        // 创建httppost
	        HttpPost httppost = new HttpPost(url);
	        // 设置超时时间
	        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000).setConnectTimeout(2000)  
	        	    .setSocketTimeout(10000).build();
	        httppost.setConfig(requestConfig);
	        
	        CloseableHttpResponse response = null;
	        try {
	        	StringEntity entity = new StringEntity(json, WebClientUtil.Encoding);
	        	entity.setContentEncoding(WebClientUtil.Encoding);
	        	entity.setContentType(WebClientUtil.ContentType);
	        	httppost.setEntity(entity);
	        	response = httpclient.execute(httppost);
	    		HttpEntity result = response.getEntity();
	    		resultJson = EntityUtils.toString(result, WebClientUtil.Encoding);
	        } catch (Exception e) {
	        		if(logger.isErrorEnabled()){
		        		logger.error(Exceptions.getStackTraceAsString(e));
		        	}
		        	JSONObject jsonobject = new JSONObject();
		        	jsonobject.put("code", -1);
		        	jsonobject.put("url",url);
		        	jsonobject.put("data", JSONObject.parse(json));
		        	jsonobject.put("msg", e.getMessage());
		        	resultJson = jsonobject.toJSONString();
	        } finally {
	        	// 关闭连接,释放资源
	        	if (response != null){
	        		try{response.close();}catch(IOException e){}
	        	}
	        	try{httpclient.close();}catch(IOException e){}
	        }
	        return resultJson;
	    }
}
