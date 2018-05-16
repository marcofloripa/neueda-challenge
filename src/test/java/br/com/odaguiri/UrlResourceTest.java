package br.com.odaguiri;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.odaguiri.dto.UrlStatisticResponse;
import br.com.odaguiri.mongo.repository.ShortenedUrlMongoRepository;
import br.com.odaguiri.service.UrlService;
import br.com.odaguiri.web.UrlResource;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UrlResource.class, secure = false)
public class UrlResourceTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UrlService urlService;
	
	@MockBean
	ShortenedUrlMongoRepository shortenedUrlMongoRepository;
	
	@Test
	public void clickStats() throws Exception {
		
		UrlStatisticResponse urlStatisticResponse = new UrlStatisticResponse("ijsgkke", 34, 2);
		Optional<UrlStatisticResponse> op = Optional.of(urlStatisticResponse);
		
		Mockito.when(urlService.getStatistics("ijsgkke")).thenReturn(op);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/urls/stats?shortUrl=ijsgkke").accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
