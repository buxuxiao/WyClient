package com.example.view;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ab.bitmap.AbImageDownloader;
import com.example.function.GetJsoupString;
import com.example.function.ListViewAdapter;
import com.example.function.News;
import com.example.function.ViewAdapter;
import com.example.wyclient.ContentActivity;
import com.example.wyclient.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class Fragment5 extends Fragment {

	public static final String URL = "http://58.213.135.145/pub/jsxfHD/zxgb/yjgb/";

	private ViewPager viewPager1;
	private ListView listview_bottom;
	private Context context;
	private View view;
	private LinearLayout header;
	private ArrayList<News> list = new ArrayList<News>();

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				listview_bottom.setAdapter(new ListViewAdapter(list, context));
				initViewAdapter(list);
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment1, null);
		return view;
	}

	private void initViewAdapter(ArrayList<News> list2) {
		// TODO Auto-generated method stub
		ArrayList<View> list = new ArrayList<View>();

		LinearLayout.LayoutParams params = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		AbImageDownloader mAbImageDownloader = new AbImageDownloader(context);
		for (int i = 0; i < 3; i++) {
			ImageView imageView = new ImageView(context);
			imageView.setLayoutParams(params);
			imageView.setScaleType(ScaleType.FIT_XY);
			mAbImageDownloader.display(imageView, list2.get(i).getImgUrl1());
			list.add(imageView);
		}
		viewPager1.setAdapter(new ViewAdapter(list));
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		context = getActivity();
		listview_bottom = (ListView) view.findViewById(R.id.listview_bottom);
		header = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.viewpager, null);
		viewPager1 = (ViewPager) header.findViewById(R.id.viewpager1);
		listview_bottom.addHeaderView(header);

		Thread thread = new Thread(new MyRunable(list));
		thread.start();

		listview_bottom.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, ContentActivity.class);
				intent.putExtra("contentUrl", list.get(position)
						.getContentUrl());
				context.startActivity(intent);
			}
		});
	}

	class MyRunable implements Runnable {
		private ArrayList<News> list;

		public MyRunable(ArrayList<News> list) {
			// TODO Auto-generated constructor stub
			this.list = list;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				String result = GetJsoupString.getResult(URL);
				JSONObject jsonObject = new JSONObject(result);
				JSONArray newsList = jsonObject.getJSONArray("newsList");
				for (int i = 0; i < newsList.length(); i++) {
					JSONObject tmp = newsList.getJSONObject(i);
					list.add(new News(tmp.optString("title"), tmp
							.optString("abstract"), tmp.optString("imgUrl1"),
							tmp.optString("imgUrl2", ""), tmp
									.optString("imgUrl3"), tmp
									.optString("contentUrl")));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Message message = new Message();
			message.what = 1;
			mHandler.sendMessage(message);
		}

	}
}
