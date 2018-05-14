package br.com.odaguiri.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import br.com.odaguiri.mongo.domain.ShortenedUrlStatistic;

public class ShortenedUrlMongoRepositoryImpl implements ShortenedUrlMongoCustomRespository {

	@Autowired
	MongoTemplate mongoTemplate; 
	
	@Override
	public void incClicks(String shortUrl) {
		Query query = new Query(Criteria.where("shortUrl").is(shortUrl));
		Update update = new Update();
		update.inc("clicks", 1);
		mongoTemplate.updateFirst(query, update, ShortenedUrlStatistic.class);
	}

	@Override
	public void incCreationAttempts(String longUrl) {
		Query query = new Query(Criteria.where("longUrl").is(longUrl));
		Update update = new Update();
		update.inc("creationAttempts", 1);
		mongoTemplate.updateFirst(query, update, ShortenedUrlStatistic.class);
	}

}
