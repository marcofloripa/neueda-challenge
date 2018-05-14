package br.com.odaguiri.dto;

import java.io.Serializable;

public class ShortenedUrlRequest implements Serializable {

	private static final long serialVersionUID = 6010438363953376011L;
	
	private String longUrl;
	
	public ShortenedUrlRequest() {}
	
	public ShortenedUrlRequest(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
}
