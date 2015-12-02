package com.example.function;

import java.util.ArrayList;

import com.example.wyclient.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter_left extends BaseAdapter {
	private ArrayList<ImageText> list;
	private Context context;

	public ListViewAdapter_left(ArrayList<ImageText> list, Context context) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.context = context;
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
					R.layout.listveiw_item_left, null);
		}
		ImageView image = (ImageView) convertView.findViewById(R.id.imageview);
		TextView text = (TextView) convertView.findViewById(R.id.textview);

		image.setImageResource(list.get(position).getImage());
		text.setText(list.get(position).getText());
		return convertView;
	}

}
