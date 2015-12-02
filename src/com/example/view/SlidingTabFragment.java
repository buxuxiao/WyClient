package com.example.view;

import com.ab.view.sliding.AbSlidingTabView;
import com.example.wyclient.R;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SlidingTabFragment extends Fragment {

	private AbSlidingTabView myTable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.sliding_tab, null);

		myTable = (AbSlidingTabView) view.findViewById(R.id.mAbSlidingTabView);

		myTable.setTabTextColor(Color.BLACK);
		myTable.setTabSelectColor(Color.rgb(30, 168, 131));
		myTable.setTabLayoutBackgroundResource(R.drawable.abc_ab_share_pack_holo_dark);

		myTable.addItemView(" 头条  ",new Fragment1());
		myTable.addItemView(" 娱乐  ",new Fragment2());
		myTable.addItemView(" 热点 ",new Fragment3());
		myTable.addItemView(" 体育  ",new Fragment4());
		myTable.addItemView(" 南京  ",new Fragment5());
		myTable.addItemView(" 财经  ",new Fragment6());

		myTable.setTabPadding(20, 8, 20, 8);
		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
