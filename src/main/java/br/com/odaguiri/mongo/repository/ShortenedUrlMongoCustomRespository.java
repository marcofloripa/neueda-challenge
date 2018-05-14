package br.com.odaguiri.mongo.repository;

public interface ShortenedUrlMongoCustomRespository {

	void incClicks(String shortUrl);
	void incCreationAttempts(String longUrl);
}
