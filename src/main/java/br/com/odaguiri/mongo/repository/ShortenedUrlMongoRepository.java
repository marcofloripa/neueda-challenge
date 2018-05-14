package br.com.odaguiri.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.odaguiri.mongo.domain.ShortenedUrlStatistic;

public interface ShortenedUrlMongoRepository extends MongoRepository<ShortenedUrlStatistic, String>, ShortenedUrlMongoCustomRespository {
	
	Optional<ShortenedUrlStatistic> findFirstByShortUrl(String shortUrl);
}
