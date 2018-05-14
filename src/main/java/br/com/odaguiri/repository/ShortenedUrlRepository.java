package br.com.odaguiri.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.odaguiri.domain.ShortenedUrl;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {
		
	Optional<ShortenedUrl> findByLongUrl(String longUrl);
	Optional<ShortenedUrl> findByShortUrl(String shortUrl);
	Optional<ShortenedUrl> findByHashUrl(String hashUrl);
}
