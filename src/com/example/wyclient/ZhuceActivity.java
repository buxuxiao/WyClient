package com.example.wyclient;

import com.example.function.MyDbHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ZhuceActivity extends Activity {
	private EditText et1, et2, et3;
	private String name, password1, password2;

	private MyDbHelper myDbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhuce);

		et1 = (EditText) findViewById(R.id.edittext1);
		et2 = (EditText) findViewById(R.id.edittext2);
		et3 = (EditText) findViewById(R.id.edittext3);

		myDbHelper = new MyDbHelper(this);
		db = myDbHelper.getReadableDatabase();

	}

	public void onClickButton1(View v) {
		name = et1.getText().toString();
		password1 = et2.getText().toString();
		password2 = et3.getText().toString();
		
		if(doQuery(name, password1)==1){
			Toast.makeText(ZhuceActivity.this, "此用户名已注册", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		if (password1.equals(password2)) {
			if (doInsert(name, password1) >= 0) {
				Toast.makeText(ZhuceActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(ZhuceActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(ZhuceActivity.this, "密码不相同", Toast.LENGTH_SHORT).show();
		}
		System.out.println("onClickbutton1");
	}
	
	public void onClick(View v){
		switch (v.getId()) {
		case R.id.button_back:
			this.finish();
			break;

		default:
			break;
		}
	}
	
	protected long doInsert(String name, String password) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put("username", name);
		cv.put("password", password);
		return db.insert("user", null, cv);

	}

	protected int doQuery(String name, String password) {
		// TODO Auto-generated method stub
		Cursor c = db.query("user", null, "username=?", new String[] { name },
				null, null, null);
		if (c.moveToFirst()) {
			return 1;
		}
		return 0;
	}
}
