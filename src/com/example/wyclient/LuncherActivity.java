package com.example.wyclient;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.PrivateCredentialPermission;

import com.ab.activity.AbActivity;
import com.ab.bitmap.AbImageDownloader;
import com.example.function.ImageDownload;
import com.example.function.ImageText;
import com.example.function.ListViewAdapter_left;
import com.example.function.MyDbHelper;
import com.example.view.SlidingTabFragment;

import android.app.LauncherActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class LuncherActivity extends AbActivity {
	private EditText et1, et2;
	private MyDbHelper myDbHelper;
	private SQLiteDatabase db;

	private ImageView imageview_launcher;
	private ListView listview_left, listview_right;
	private DrawerLayout mDrawer;
	private LinearLayout layout_left, layout_right;
	private ViewPager viewPager1;
	private ListView listview_bottom;
	private ArrayList<ImageText> list_left, list_right;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				// imageview_launcher.setImageBitmap((Bitmap) msg.obj);
				AbImageDownloader mAbImageDownloader = new AbImageDownloader(
						LuncherActivity.this);
				mAbImageDownloader.display(imageview_launcher,
						"http://d.3987.com/drzwtx_140415/001.jpg");
				Message message = new Message();
				message.what = 1;
				mHandler.sendMessageDelayed(message, 1000);
				break;
			case 1:
				imageview_launcher.setVisibility(View.GONE);
				break;
			default:
				break;
			}
		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_luncher);

		imageview_launcher = (ImageView) findViewById(R.id.imageview_launcher);
		Thread thread = new Thread(new MyRunable());
		thread.start();

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new SlidingTabFragment()).commit();

		listview_left = (ListView) findViewById(R.id.listview_left);
		listview_right = (ListView) findViewById(R.id.listview_right);

		mDrawer = (DrawerLayout) findViewById(R.id.drawer);
		layout_left = (LinearLayout) findViewById(R.id.layout_left);
		layout_right = (LinearLayout) findViewById(R.id.layout_right);

		list_left = new ArrayList<ImageText>();
		list_left.add(new ImageText(R.drawable.bg_news, "新闻"));
		list_left.add(new ImageText(R.drawable.bg_red, "订阅"));
		list_left.add(new ImageText(R.drawable.bg_pics, "图片"));
		list_left.add(new ImageText(R.drawable.bg_vedio, "视频"));
		list_left.add(new ImageText(R.drawable.bg_ties, "跟帖"));
		listview_left.setAdapter(new ListViewAdapter_left(list_left, this));

		list_right = new ArrayList<ImageText>();
		list_right.add(new ImageText(R.drawable.biz_pc_main_mall_icon, "俱乐部"));
		list_right
				.add(new ImageText(R.drawable.biz_pc_main_activity_icon, "活动"));
		list_right.add(new ImageText(R.drawable.biz_pc_main_app_icon, "应用"));
		list_right.add(new ImageText(R.drawable.biz_pc_main_game_icon, "游戏"));

		listview_right.setAdapter(new ListViewAdapter_left(list_right, this));

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		myDbHelper = new MyDbHelper(this);
		db = myDbHelper.getReadableDatabase();

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			if (mDrawer.isDrawerOpen(layout_left)) {
				mDrawer.closeDrawer(layout_left);
			} else if (mDrawer.isDrawerOpen(layout_right)) {
				mDrawer.closeDrawer(layout_right);
				mDrawer.openDrawer(layout_left);
			} else {
				mDrawer.openDrawer(layout_left);
			}
			break;
		case R.id.button2:
			if (mDrawer.isDrawerOpen(layout_right)) {
				mDrawer.closeDrawer(layout_right);
			} else if (mDrawer.isDrawerOpen(layout_left)) {
				mDrawer.closeDrawer(layout_left);
				mDrawer.openDrawer(layout_right);
			} else {
				mDrawer.openDrawer(layout_right);
			}
			break;
		case R.id.button3:

			doInsert("123", "123");
			String name = et1.getText().toString();
			String password = et2.getText().toString();
			switch (doQuery(name, password)) {
			case 1:
				Toast.makeText(LuncherActivity.this, "没有此用户名",
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(LuncherActivity.this, "密码错误", Toast.LENGTH_SHORT)
						.show();
				break;
			case 3:
				Toast.makeText(LuncherActivity.this, "登录成功", Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				break;
			}

			break;
		case R.id.button4:
			Intent intent = new Intent(LuncherActivity.this,
					ZhuceActivity.class);
			LuncherActivity.this.startActivity(intent);
			break;
		default:
			break;
		}
	}

	class MyRunable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				// Bitmap bitmap = ImageDownload
				// .getPicture("http://d.3987.com/drzwtx_140415/001.jpg");
				Message message = new Message();
				message.what = 0;
				// message.obj = bitmap;
				mHandler.sendMessageDelayed(message, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * 退出询问
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mDrawer.isDrawerOpen(layout_left)
					|| mDrawer.isDrawerOpen(layout_right)) {
				mDrawer.closeDrawer(layout_left);
				mDrawer.closeDrawer(layout_right);
			} else {
				keydown2();
			}
		}
		return false;
	}

	private static Boolean exit = false;

	void keydown2() {
		Timer time = null;
		if (exit == false) {
			exit = true;
			Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
			time = new Timer();
			time.schedule(new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					exit = false;
				}
			}, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

	protected int doQuery(String name, String password) {
		// TODO Auto-generated method stub
		Cursor c = db.query("user", null, "username=?", new String[] { name },
				null, null, null);
		if (c.moveToFirst()) {
			if (password.equals(c.getString(c.getColumnIndex("password")))) {
				return 3; // sucess;
			} else {
				return 2; // password err;
			}

		} else {
			return 1; // no this id;
		}

	}

	protected void doInsert(String name, String password) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put("username", name);
		cv.put("password", password);
		db.insert("user", null, cv);

	}
}