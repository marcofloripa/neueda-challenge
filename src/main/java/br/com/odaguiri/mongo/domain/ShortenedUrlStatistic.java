package br.com.odaguiri.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortenedurlstatistic")
public class ShortenedUrlStatistic {

	@Id
	private String id;
	
	@Indexed(unique = true)
	private String longUrl;
	
	@Indexed(unique = true)
	private String shortUrl;
	
	private Integer clicks;
	
	private Integer creationAttempts;
	
	public ShortenedUrlStatistic() {}
	
	public ShortenedUrlStatistic(String longUrl, String shortUrl) {
		this.longUrl = longUrl;
		this.shortUrl = shortUrl;
		this.clicks = 1;
		this.creationAttempts = 1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
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