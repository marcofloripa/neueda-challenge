package br.com.odaguiri.dto;

import java.io.Serializable;

public class UrlStatisticResponse implements Serializable {

	private static final long serialVersionUID = 735592644664949532L;

	private String shortUrl;
	
	private Integer clicks;
	
	private Integer creationAttempts;
	
	public UrlStatisticResponse() {}

	public UrlStatisticResponse(String shortUrl, Integer clicks, Integer creationAttempts) {
		super();
		this.shortUrl = shortUrl;
		this.clicks = clicks;
		this.creationAttempts = creationAttempts;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public Integer getClicks() {
		return clicks;
	}

	public void setClicks(Integer clicks) {
		this.clicks = clicks;
	}

	public Integer getCreationAttempts() {
		return creationAttempts;
	}

	public void setCreationAttempts(Integer creationAttempts) {
		this.creationAttempts = creationAttempts;
	}
	
}
