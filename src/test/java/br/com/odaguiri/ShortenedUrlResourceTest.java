package br.com.odaguiri;

import static org.junit.Assert.assertEquals;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.odaguiri.config.GlobalProperties;
import br.com.odaguiri.domain.ShortenedUrl;
import br.com.odaguiri.repository.ShortenedUrlRepository;
import br.com.odaguiri.service.ShortenedUrlService;
import br.com.odaguiri.web.ShortenedUrlResource;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShortenedUrlResource.class, secure = false)
public class ShortenedUrlResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ShortenedUrlService shortenedUrlService;
	
	@MockBean
	ShortenedUrlRepository shortenedUrlRepository;
	
	@MockBean
	GlobalProperties globalProperties;
	
	@Test
	public void getAllShortenedUrls() throws Exception {
		List<ShortenedUrl> urls = new ArrayList<ShortenedUrl>();
		urls.add(new ShortenedUrl(URLEncoder.encode("http://google.com", "UTF-8"), "jhfueoso", URLEncoder.encode("http://localhost:8090/jhfueoso", "UTF-8")));
		urls.add(new ShortenedUrl(URLEncoder.encode("http://linkedin.com", "UTF-8"), "lorissje", URLEncoder.encode("http://localhost:8090/lorissje", "UTF-8")));
		Mockito.when(shortenedUrlRepository.findAll()).thenReturn(urls);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/shortened-urls").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{id:null,hashUrl:jhfueoso,longUrl:" + URLEncoder.encode("http://google.com", "UTF-8") 
				+ ",shortUrl:" + URLEncoder.encode("http://localhost:8090/jhfueoso", "UTF-8") 
				+ "},{id:null,hashUrl:lorissje,longUrl:" + URLEncoder.encode("http://linkedin.com", "UTF-8") 
				+ ",shortUrl:" + URLEncoder.encode("http://localhost:8090/lorissje", "UTF-8") + "}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void createShortenedUrl() throws Exception {
		
		String requestJson = "{\"longUrl\": \"https://www.neueda.com/\"}";
		
		Mockito.when(globalProperties.getScheme()).thenReturn("http");
		Mockito.when(globalProperties.getServerName()).thenReturn("localhost");
		Mockito.when(globalProperties.getServerPort()).thenReturn(8080);
		
		ShortenedUrl shortenedUrl = new ShortenedUrl("https://www.neueda.com/", "hgtesgdj", "http://localhost:8080/hgtesgdj");
		shortenedUrl.setId(1l);
		Mockito.when(shortenedUrlService.shorten("http://localhost:8080/", "https://www.neueda.com/"))
			.thenReturn(shortenedUrl);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/api/shortened-urls")
				.accept(MediaType.APPLICATION_JSON).content(requestJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("/api/shortened-urls/1", response.getHeader(HttpHeaders.LOCATION));
	}
	
	@Test
	public void findByHashUrl() throws Exception {
		
		ShortenedUrl shortenedUrl = new ShortenedUrl("http://microsoft.com", "juidklso", "http://localhost:8080/juidklso");
		Optional<ShortenedUrl> op = Optional.of(shortenedUrl);
		
		Mockito.when(shortenedUrlRepository.findByHashUrl("juidklso")).thenReturn(op);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/shortened-urls/juidklso").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
}
