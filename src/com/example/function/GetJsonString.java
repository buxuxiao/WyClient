package com.example.function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.content.Entity;

public class GetJsonString {
	public static String getResult(String Url) throws ClientProtocolException, IOException {

		String baseUrl = Url;
		List<BasicNameValuePair> params = new LinkedList<BasicNameValuePair>();
//		params.add(new BasicNameValuePair("", ""));
		String param = URLEncodedUtils.format(params, "UTF-8");
		
		HttpGet httpGet = new HttpGet(baseUrl);
		HttpClient client = new DefaultHttpClient();
		
		HttpResponse response = client.execute(httpGet);
		String result = EntityUtils.toString(response.getEntity(),"GBK");

		return result;
	}
}
