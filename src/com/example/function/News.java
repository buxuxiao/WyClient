package com.example.function;

public class News {

	private String title;
	private String shortContent;
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	private String contentUrl;

	public News(String title, String shortContent, String imgUrl1,
			String imgUrl2, String imgUrl3, String contentUrl) {
		super();
		this.title = title;
		this.shortContent = shortContent;
		this.imgUrl1 = imgUrl1;
		this.imgUrl2 = imgUrl2;
		this.imgUrl3 = imgUrl3;
		this.contentUrl = contentUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getShortContent() {
		return shortContent;
	}

	public String getImgUrl1() {
		return imgUrl1;
	}

	public String getImgUrl2() {
		return imgUrl2;
	}

	public String getImgUrl3() {
		return imgUrl3;
	}

	public String getContentUrl() {
		return contentUrl;
	}

}
