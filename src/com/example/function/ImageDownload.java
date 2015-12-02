package com.example.function;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageDownload {
	public static Bitmap getPicture(String Url) throws Exception{
		HttpURLConnection conn = (HttpURLConnection) (new URL(Url)).openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		InputStream in = conn.getInputStream();
		byte[] b = input2byte(in);
		Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
		return bitmap;
	}

	private static byte[] input2byte(InputStream in) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[100];
		int rc;
		while((rc=in.read(buffer, 0, 100))>0){
			byteArrayOutputStream.write(buffer, 0, rc);
		}
		byte[] b = byteArrayOutputStream.toByteArray();
		return b;
	}
}
