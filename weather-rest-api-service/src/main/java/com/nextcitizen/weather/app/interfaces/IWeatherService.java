package com.nextcitizen.weather.app.interfaces;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public interface IWeatherService {

	/*
	 * @param countryName, cityName
	 * 
	 * @return current weather condition
	 * 
	 */
	ResponseEntity<String> getCurrentWeather(final String countryName, final String cityName) throws ParseException;

	/*
	 * @param countryName, cityName
	 * 
	 * @return forecast weather condition
	 */
	ResponseEntity<String> getForecastWeather(final String countryName, final String cityName);
	
	/*
	 * @param county, city and weather/forecast url
	 * 
	 * @return HTTP response from weather service
	 */
	ResponseEntity<String> callWeatherAPI(String country, String city, String url);

	/*
	 * @return should return only needed information from HTTP Response
	 */
	JSONObject displayUserFiendlyWeatherAPI( final String httpResponse);

}
