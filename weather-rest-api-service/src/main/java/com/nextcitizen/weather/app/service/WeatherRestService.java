package com.nextcitizen.weather.app.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Service
public class WeatherRestService {

	private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

	private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

	private final RestTemplate restTemplate;

	private final String apiKey;

	private static final Logger logger = LoggerFactory.getLogger(WeatherRestService.class);

	public WeatherRestService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = "bd181c6a089a32723ace4042ec94c024";
	}

	@Cacheable("weather")
	public Object getCurrentWeather(String country, String city) throws RestClientException {
		logger.info("Requesting current weather for {}/{}", country, city);
		try {
			URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
			return response.getBody();
		}
		catch(Exception exp ) {
			return exp.getMessage();
		}
		
	}
	@Cacheable("weather")
	public Object getForeCastWeather(String country, String city) {
		logger.info("Requesting current weather for {}/{}", country, city);
		URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
		return response.getBody();
	}

}


