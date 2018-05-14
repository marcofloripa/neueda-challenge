package br.com.odaguiri.service;

import java.util.Optional;

import br.com.odaguiri.dto.UrlStatisticResponse;

public interface UrlService {
	
	void registerClick(String shortUrl);
	Optional<UrlStatisticResponse> getStatistics(String shortUrl);
}
