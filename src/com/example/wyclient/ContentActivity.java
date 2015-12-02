package com.example.wyclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.ab.bitmap.AbImageDownloader;
import com.example.function.GetJsonString;
import com.example.function.GetJsoupString;
import com.example.function.News;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentActivity extends Activity {
	private String contentUrl;
	private TextView title;
	private ImageView image;
	private TextView content;
	private AbImageDownloader abImageDownloader;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String result = msg.obj.toString();
			title.setText(result);
			try {
				JSONObject jsonObject = new JSONObject(result);
				title.setText(jsonObject.optString("title"));
				content.setText(jsonObject.optString("content"));
				abImageDownloader.display(image,
						jsonObject.optString("imgUrl1"));

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content_main);

		contentUrl = (String) this.getIntent().getExtras().get("contentUrl");

		title = (TextView) findViewById(R.id.title);
		image = (ImageView) findViewById(R.id.image);
		content = (TextView) findViewById(R.id.content);
		abImageDownloader = new AbImageDownloader(this);

		Thread thread = new Thread(new MyRunable(contentUrl));
		thread.start();

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			this.finish();
			break;

		default:
			break;
		}
	}

	class MyRunable implements Runnable {
		private String contentUrl;

		public MyRunable(String contentUrl) {
			// TODO Auto-generated constructor stub
			this.contentUrl = contentUrl;
		}

		@Override
		public void run() {

			String result = GetJsoupString.getResult(contentUrl);
			result = result.replace("&nbsp", "");
			Message message = new Message();
			message.obj = result;
			mHandler.sendMessage(message);
		}

	}
}
