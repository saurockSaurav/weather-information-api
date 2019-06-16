package com.nextcitizen.weather.app.interfaces;

import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;

public  interface IWeatherService {
	
	/*
	 * @param  countryName, cityName
	 *  @return current weather condition
	 * 
	 */
	public ResponseEntity<?> getCurrentWeather(String countryName, String cityName) throws ParseException;
	
	/*
	 * @param  countryName, cityName
	 *  @return forecast weather condition
	 */
	public ResponseEntity<?> getForecastWeather(String countryName, String cityName);
}
