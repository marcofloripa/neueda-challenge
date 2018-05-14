package br.com.odaguiri.service;

import br.com.odaguiri.domain.ShortenedUrl;

public interface ShortenedUrlService {

	ShortenedUrl shorten(String serverName, String longUrl);
}
