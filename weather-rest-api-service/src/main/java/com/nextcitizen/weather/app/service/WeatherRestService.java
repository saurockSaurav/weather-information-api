package com.nextcitizen.weather.app.service;

import java.net.URI;

import org.json.simple.JSONObject;
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
public class WeatherRestService implements IWeatherService {

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

	/**
	 * @param countryName
	 * @param cityName
	 * @return
	 * @throws WeatherNotFoundException
	 */
	@Cacheable("weather")
	@Override
	public ResponseEntity<String> getCurrentWeather(String country, String city) {

		if (weatherAppUtils.isAlphabhate(country) && weatherAppUtils.isAlphabhate(city)) {
			logger.info("Step 1/4 Start of HTTP request/response ");
			return callWeatherService(country, city, WEATHER_URL);
		} else {
			String message = "Invalid weather request received for Country : " + country + " / City :" + city;
			throw new WeatherNotFoundException(message, null);
		}

	}

	@Cacheable("forecast")
	@Override
	public ResponseEntity<String> getForecastWeather(String country, String city) {
		if (weatherAppUtils.isAlphabhate(country) && weatherAppUtils.isAlphabhate(city)) {
			logger.info("Step 1/4 Start of HTTP request/response ");
			return callWeatherService(country, city, FORECAST_URL);
		} else {
			String message = "Invalid weather request received for Country : " + country + " / City :" + city;
			throw new WeatherNotFoundException(message, null);
		}
	}

	/**
	 * @param country
	 * @param city
	 * @return
	 * @throws WeatherNotFoundException
	 */
	private ResponseEntity<String> callWeatherService(String country, String city, String url) {

		long methodStartTime = System.currentTimeMillis();

		ResponseEntity<String> response = null;

		try {
			URI expandURL = new UriTemplate(url).expand(city, country, this.apiKey);
			logger.info("Step 2/4 Requesting current weather for {}/{}", country, city);
			if (expandURL != null && weatherAppUtils.isValidURL(expandURL.toString())) {
				logger.info("Step 3/4 URL path {}", expandURL);
				response = restTemplate.exchange(expandURL, HttpMethod.GET, null, String.class);
				if (response == null) {
					String message = "Invalid Http response received, response received is :" + response;
					throw new WeatherNotFoundException(message, expandURL);
				}
			} else {
				String message = "Invalid URL path, requested URL is :" + expandURL;
				throw new WeatherNotFoundException(message, expandURL);
			}

		} catch (Exception exp) {
			String message = "Could not process weather request, Exception occured due to :" + exp.getMessage();
			logger.error(message);
			throw new WeatherNotFoundException(message, null);
		}

		logger.info("Step 4/4 End of HTTP request/response, response status code is: {} / Time taken {} ms ",
				response.getStatusCodeValue(), System.currentTimeMillis() - methodStartTime);
		logger.info("***************************************************************************************************");
		return ResponseEntity.ok(response.getBody());
	}

	@Override
	public JSONObject displayUserFiendlyWeatherJSON(ResponseEntity<?> httpResponse) {
		// TODO Auto-generated method stub
		return null;
	}

}
