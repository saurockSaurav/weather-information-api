package com.nextcitizen.weather.app.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.nextcitizen.weather.app.exception.WeatherNotFoundException;
import com.nextcitizen.weather.app.interfaces.IWeatherService;
import com.nextcitizen.weather.app.utils.WeatherAppUtils;

@Service
public class WeatherRestService implements IWeatherService{

	private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

	private static final String FORECAST_URL = "http://api.openweathermap.org/data/2.5/forecast?q={city},{country}&APPID={key}";

	private final RestTemplate restTemplate;
	
	private final WeatherAppUtils weatherAppUtils = new WeatherAppUtils();

	private final String apiKey;

	private static final Logger logger = LoggerFactory.getLogger(WeatherRestService.class);

	public WeatherRestService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = "bd181c6a089a32723ace4042ec94c024";
	}

	@Cacheable("weather")
	@Override
	public ResponseEntity<Object> getCurrentWeather(String country, String city) {

		logger.info("Requesting current weather for {}/{}", country, city);
		ResponseEntity<ResponsePOJO> response = null;

		try {
			URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
			logger.info("URL path {}", url);
			if (url != null && weatherAppUtils.isValidURL(url.toString())) {
				response = restTemplate.exchange(url, HttpMethod.GET, null, ResponsePOJO.class);
				if (response == null) {
					String message = "Invalid Http response received, response received is :" + response;
					throw new WeatherNotFoundException(message, url);
				}
			} else {
				String message = "Invalid URL path, requested URL is :" + url;
				throw new WeatherNotFoundException(message, url);
			}

		} catch (Exception exp) {
			String message = "Could not process weather request, Exception occured due to :" + exp.getMessage();
			logger.error(message);
			throw new WeatherNotFoundException(message, null);
		}
		logger.info("End of HTTP request/response ..!");
		return ResponseEntity.ok(response.getBody());
	}

	/*@Cacheable("weather")
	public Object getForeCastWeather(String country, String city) {
		logger.info("Requesting current weather for {}/{}", country, city);
		URI url = new UriTemplate(FORECAST_URL).expand(city, country, this.apiKey);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
		return response.getBody();
	}*/

	@Override
	public ResponseEntity<Object> getForecastWeather(String countryName, String cityName) {
		// TODO Auto-generated method stub
		return null;
	}

}
