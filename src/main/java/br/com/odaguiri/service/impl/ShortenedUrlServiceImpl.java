package br.com.odaguiri.service.impl;

import java.util.Optional;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.odaguiri.domain.ShortenedUrl;
import br.com.odaguiri.mongo.domain.ShortenedUrlStatistic;
import br.com.odaguiri.mongo.repository.ShortenedUrlMongoRepository;
import br.com.odaguiri.repository.ShortenedUrlRepository;
import br.com.odaguiri.service.ShortenedUrlService;

@Service
public class ShortenedUrlServiceImpl implements ShortenedUrlService {

	@Autowired
	ShortenedUrlRepository shortenedUrlRepository; 
	
	@Autowired
	ShortenedUrlMongoRepository shortenedUrlMongoRepository;  
	
	@Override
	public ShortenedUrl shorten(String serverName, String longUrl) {
		ShortenedUrl shortenedUrl = null;
		Optional<ShortenedUrl> url = shortenedUrlRepository.findByLongUrl(longUrl);
		if (!url.isPresent()) {
			shortenedUrl = createShortenedUrl(serverName, longUrl);
			createShortenedUrlStatistic(shortenedUrl);
		} else {
			shortenedUrl = url.get();
			shortenedUrl.setExists(true);
			shortenedUrlMongoRepository.incCreationAttempts(longUrl);
		}
		return shortenedUrl;
	}

	private ShortenedUrl createShortenedUrl(String serverName, String longUrl) {
		String hashUrl = RandomStringUtils.randomAlphanumeric(8);
		ShortenedUrl shortenedUrl = new ShortenedUrl(longUrl, hashUrl, serverName + hashUrl);
		shortenedUrlRepository.save(shortenedUrl);		
		return shortenedUrl;
	}
	
	private void createShortenedUrlStatistic(ShortenedUrl shortenedUrl) {
		ShortenedUrlStatistic statistic = new ShortenedUrlStatistic(shortenedUrl.getLongUrl(), shortenedUrl.getShortUrl());
		shortenedUrlMongoRepository.save(statistic);
	}

}
