package com.nextcitizen.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nextcitizen.weather.app.service.WeatherRestService;

@RestController
@RequestMapping("/api/nextcitizen")
public class WeatherByZipAndCityController {
	
	@Autowired
	private WeatherRestService weatherRestService;
	
	/**
	 * @param zipCode
	 * @return List
	 */
	@GetMapping(path = "/weather/{countryName}/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object  getCurrentWeather( @PathVariable String countryName,
									  @PathVariable String cityName ) {
		if(StringUtils.hasText(countryName) ) {
			return weatherRestService.getCurrentWeather(countryName, cityName);
		}
		return null;
	}
	
	@GetMapping(path = "/forecast/{countryName}/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object  getForecastWeather( @PathVariable String countryName,
									   @PathVariable String cityName ) {
		if(StringUtils.hasText(countryName) ) {
			return weatherRestService.getForeCastWeather(countryName, cityName);
		}
		return null;
	}
	
	/*@GetMapping(path = "/api/nextcitizen/weather", produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getWeatherInformation( @NotNull  @RequestBody final Map<String, String> requestPayload ) {
		if(!requestPayload.isEmpty()) {
			weatherRestService.callWeatherServiceAPI(requestPayload.get(COUNTRY_NAME),
								  requestPayload.get(CITY_NAME),
								  requestPayload.get(ZIP_CODE));
		}
		return null;
	}*/

	
}
