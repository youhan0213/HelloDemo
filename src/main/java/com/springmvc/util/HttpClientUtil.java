package com.springmvc.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	private static int httpClinetSoTimeout = 30000;
	private static int httpClientConnectionTimeout = 30000;

	/**
	 * HTTP GET方式
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String sendHttpForGet(String url) throws ClientProtocolException, IOException {
		HttpClient client = null;
		try {
			client = new DefaultHttpClient();
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, httpClientConnectionTimeout);
			client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, httpClinetSoTimeout);
			HttpGet get = new HttpGet(url);
			HttpResponse httpResponse = client.execute(get);
			String respData = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
			return respData;
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
	

	public static String dateTimeFormate(Long date,String partten){
		 if(date==null){
			 return "";
		 }
		 SimpleDateFormat sdf = new SimpleDateFormat(partten);
		 return sdf.format(date);
	}
	
	public static boolean delFile(List<String> filePath){
		if(filePath==null || filePath.size()<1){
			return true;
		}
		for(String str:filePath){
			File delFile = new File(str);
			if(delFile.exists())
			    delFile.delete();
		}
		
		return true;
	}

}
