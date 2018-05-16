package br.com.odaguiri.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.odaguiri.dto.UrlStatisticResponse;
import br.com.odaguiri.mongo.domain.ShortenedUrlStatistic;
import br.com.odaguiri.mongo.repository.ShortenedUrlMongoRepository;
import br.com.odaguiri.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
	ShortenedUrlMongoRepository shortenedUrlMongoRepository; 
	
	@Override
	public void registerClick(String shortUrl) {
		shortenedUrlMongoRepository.incClicks(shortUrl);
	}

	@Override
	public Optional<UrlStatisticResponse> getStatistics(String shortUrl) {
		Optional<ShortenedUrlStatistic> statistic = shortenedUrlMongoRepository.findFirstByShortUrl(shortUrl);
		return statistic
				.map(result -> 
					Optional.of(new UrlStatisticResponse(result.getShortUrl(), result.getClicks(), result.getCreationAttempts())))
				.orElse(Optional.empty());
	}

}
