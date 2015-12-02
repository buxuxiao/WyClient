package com.example.function;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewAdapter extends PagerAdapter{
	private ArrayList<View> list;
	
	public  ViewAdapter(ArrayList<View> list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
//		return super.instantiateItem(container, position);\
		
		View v = list.get(position);
		container.addView(v);
		return v;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
//		super.destroyItem(container, position, object);
		container.removeView(list.get(position));
	}
}
