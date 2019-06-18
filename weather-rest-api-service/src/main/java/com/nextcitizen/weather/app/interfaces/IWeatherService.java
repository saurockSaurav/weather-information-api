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
	public ResponseEntity<String> getCurrentWeather(final String countryName, final String cityName) throws ParseException;

	/*
	 * @param countryName, cityName
	 * 
	 * @return forecast weather condition
	 */
	public ResponseEntity<String> getForecastWeather(final String countryName, final String cityName);

	/*
	 * @return should return only needed information from HTTP Response
	 */
	public JSONObject displayUserFiendlyWeatherJSON( final String httpResponse);

}
