package com.cafe24.shoppingmall.frontend.config.app;

import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("${spring.restTemplate.factory.readTimeout}")
    private int READ_TIMEOUT;
	@Value("${spring.restTemplate.factory.connectTimeout}")
    private int CONNECT_TIMEOUT;
	@Value("${spring.restTemplate.httpClient.maxConnTotal}")
    private int MAX_CONN_TOTAL;
	@Value("${spring.restTemplate.httpClient.maxConnPerRoute}")
    private int MAX_CONN_PER_ROUTE;
	
	@Bean
	public RestTemplate restTemplate() {
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		    
		HttpClient httpClient = HttpClientBuilder.create() 
            .setMaxConnTotal(MAX_CONN_TOTAL) 
            .setMaxConnPerRoute(MAX_CONN_PER_ROUTE) 
            .build();
        factory.setHttpClient(httpClient);
		
		RestTemplate restTemplate = new RestTemplate(factory);
		
		return restTemplate;
	}


	
	
	
}
