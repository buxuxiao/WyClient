package com.example.function;

import java.util.ArrayList;

import com.ab.bitmap.AbImageDownloader;
import com.example.wyclient.R;
import com.example.wyclient.R.id;
import com.example.wyclient.R.layout;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private ArrayList<News> list;
	private Context context;
	private AbImageDownloader mAbImageDownloader;

	public ListViewAdapter(ArrayList<News> list, Context context) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
		mAbImageDownloader = new AbImageDownloader(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.listview_item, null);
		}
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imageview);
		TextView text1 = (TextView) convertView.findViewById(R.id.textview1);
		TextView text2 = (TextView) convertView.findViewById(R.id.textview2);

		mAbImageDownloader.display(imageView, list.get(position).getImgUrl2());
		// imageView.setImageBitmap(list.get(position).getImage());

		text1.setText(list.get(position).getTitle());
		text2.setText(list.get(position).getShortContent());
		return convertView;
	}
}
