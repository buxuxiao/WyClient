package com.example.function;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GetJsoupString {
	public static String getResult(String Url) {

		String result = null;
		Document doc;
		try {
			doc = Jsoup.connect(Url).get();

			result = doc.body().toString();
			result = result.replace("<body>", "");
			result = result.replace("<br>", "");
			result = result.replace("</body>", "");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
