package br.com.odaguiri.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.odaguiri.domain.ShortenedUrl;
import br.com.odaguiri.dto.ShortenedUrlRequest;
import br.com.odaguiri.exception.UrlNotValidException;
import br.com.odaguiri.repository.ShortenedUrlRepository;
import br.com.odaguiri.service.ShortenedUrlService;
import br.com.odaguiri.util.UrlUtil;

@RestController
@RequestMapping("/api")
public class ShortenedUrlResource {

    private final ShortenedUrlService shortenedUrlService;
    private final ShortenedUrlRepository shortenedUrlRepository;

    public ShortenedUrlResource(ShortenedUrlService shortenedUrlService, ShortenedUrlRepository shortenedUrlRepository) {
        this.shortenedUrlService = shortenedUrlService;
        this.shortenedUrlRepository = shortenedUrlRepository;
    }

    @ApiOperation(value = "Create a shortened url")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid longUrl parameter provided")
    })
    @PostMapping("/shortened-urls")
    public ResponseEntity<ShortenedUrl> createShortenedUrl(@Valid @RequestBody ShortenedUrlRequest shortenedUrlRequest, 
    		HttpServletRequest request) throws URISyntaxException {
    	
    	if (!ResourceUtils.isUrl(shortenedUrlRequest.getLongUrl())) {
    		throw new UrlNotValidException("Invalid longUrl parameter provided");
    	}
    	
    	String serverName = UrlUtil.buildUrl(request.getScheme(), request.getServerName(), request.getServerPort());    	
        ShortenedUrl result = shortenedUrlService.shorten(serverName, shortenedUrlRequest.getLongUrl());
        if (result.isExists()) {
        	return ResponseEntity.ok(result);
        } else {
        	return ResponseEntity.created(new URI("/api/shortened-urls/" + result.getId())).body(result);
        }
    }
    
    @ApiOperation(value = "View a list of available shortened urls")
    @GetMapping("/shortened-urls")
    public ResponseEntity<List<ShortenedUrl>> getAllShortenedUrls() {
    	List<ShortenedUrl> urls = shortenedUrlRepository.findAll();
        return ResponseEntity.ok(urls);
    }
    
    @ApiOperation(value = "Get information about the shortened url")
    @GetMapping("/shortened-urls/expand")
    public ResponseEntity<ShortenedUrl> findByShortUrl(@RequestParam String shortUrl) {
    	return shortenedUrlRepository.findByShortUrl(shortUrl)
    			.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @ApiOperation(value = "Get information about the shortened url")
    @GetMapping("/shortened-urls/{hashUrl}")
    public ResponseEntity<ShortenedUrl> findByHashUrl(@PathVariable String hashUrl) {
    	return shortenedUrlRepository.findByHashUrl(hashUrl)
    			.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
    			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
