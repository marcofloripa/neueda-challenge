package br.com.odaguiri.web;

import io.swagger.annotations.ApiOperation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.odaguiri.dto.UrlStatisticResponse;
import br.com.odaguiri.service.UrlService;

@RestController
@RequestMapping("/api")
public class UrlResource {

	private final UrlService urlService;
	
	public UrlResource(UrlService urlService) {
        this.urlService = urlService;
    }
	
	@ApiOperation(value = "Get statistics about the shortened urls")
	@GetMapping("/urls/stats")
	public ResponseEntity<?> clickStats(@RequestParam String shortUrl) {
		Optional<UrlStatisticResponse> response = urlService.getStatistics(shortUrl);
		return response
	            .map(result -> new ResponseEntity<>(
	                result,
	                HttpStatus.OK))
	            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
