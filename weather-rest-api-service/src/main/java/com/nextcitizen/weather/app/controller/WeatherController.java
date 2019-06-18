package com.nextcitizen.weather.app.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextcitizen.weather.app.exception.WeatherNotFoundException;
import com.nextcitizen.weather.app.service.WeatherRestService;

@RestController
@RequestMapping("/api/nextcitizen")
public class WeatherController {
	
	@Autowired
	private WeatherRestService weatherRestService;
	
	/**
	 * @param countryName
	 * @param cityName
	 * @return weather Information
	 * @throws WeatherNotFoundException 
	 */
	@GetMapping(path = "/weather/{countryName}/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getCurrentWeather(@NotNull @PathVariable final String countryName,
															      @NotNull @PathVariable final String cityName){

		return weatherRestService.getCurrentWeather(countryName, cityName);
	}

	@GetMapping(path = "/forecast/{countryName}/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getForecastWeather( @NotNull @PathVariable String countryName,
															        @NotNull @PathVariable String cityName ) {
		return weatherRestService.getForecastWeather(countryName, cityName);
	}
	
}
