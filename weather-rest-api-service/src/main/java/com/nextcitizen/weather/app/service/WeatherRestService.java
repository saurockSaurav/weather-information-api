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
			return callWeatherAPI(country, city, WEATHER_URL);
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
			return callWeatherAPI(country, city, FORECAST_URL);
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
	public ResponseEntity<String> callWeatherAPI(String country, String city, String url) {

		long methodStartTime = System.currentTimeMillis();
		ResponseEntity<String> response = null;
		URI expandURL = new UriTemplate(url).expand(city, country, this.apiKey);

		try {
			
			logger.info("Step 2/4 Requesting current weather for {}/{}", country, city);
			if (expandURL != null && weatherAppUtils.isValidURL(expandURL.toString())) {
				logger.info("Step 3/4 URL path {}", expandURL);
				response = restTemplate.exchange(expandURL, HttpMethod.GET, null, String.class);
				if (response == null) {
					String message = "Invalid Http response received, response received is :" + response;
					throw new WeatherNotFoundException(message, expandURL);
				}
			} else {
				String message = "Invalid Http requst, requested URL is :" + expandURL;
				throw new WeatherNotFoundException(message, expandURL);
			}

		}
		catch (IllegalArgumentException illegalArgumentException) {
			String message = "IllegalArgumentException caught in response, Exception occured due to "+ illegalArgumentException;
			logger.error(message);
			throw new WeatherNotFoundException(message, expandURL);
		}
		catch (Exception anyException) {
			String message = "Exception caught in response, Exception occured due to " + anyException;
			throw new WeatherNotFoundException(message, expandURL);
		}
		
		//TODO implementation plan to prepare neat and only required JSON 
		/** JSONObject jobject = displayUserFiendlyWeatherJSON(response.getBody()); **/
		
		logger.info("Step 4/4 End of HTTP request/response, response status code is: {} / Time taken {} ms ",
				response.getStatusCodeValue(), System.currentTimeMillis() - methodStartTime);
		logger.info("***************************************************************************************************");
		return ResponseEntity.ok(response.getBody());
	}

	@Override
	public <T> ResponseEntity<T> displayUserFiendlyWeatherAPI(T httpResponse) {
		return null;
	}

}
