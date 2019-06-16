package com.nextcitizen.weather.app.controller;

import javax.validation.constraints.NotNull;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nextcitizen.weather.app.exception.WeatherNotFoundException;
import com.nextcitizen.weather.app.interfaces.IWeatherService;
import com.nextcitizen.weather.app.service.WeatherRestService;
import com.nextcitizen.weather.app.utils.WeatherAppUtils;

@RestController
@RequestMapping("/api/nextcitizen")
public class WeatherController implements IWeatherService{
	
	@Autowired
	private WeatherRestService weatherRestService;
	
	private final WeatherAppUtils weatherAppUtils = new WeatherAppUtils();
	
	/**
	 * @param countryName
	 * @param cityName
	 * @return weather Information
	 * @throws ParseException 
	 */
	@GetMapping(path = "/weather/{countryName}/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<?> getCurrentWeather(@NotNull @PathVariable final String countryName,
																  @NotNull @PathVariable final String cityName) throws ParseException {

		if ( weatherAppUtils.isAlphabhate(countryName) && weatherAppUtils.isAlphabhate(cityName)) {
			return weatherRestService.getCurrentWeather(countryName, cityName);
		} else {
			String message = "Invalid weather request received for Country : " + countryName + " / City :" + cityName;
			throw new WeatherNotFoundException(message, null);
		}
	}
	
	@GetMapping(path = "/forecast/{countryName}/{cityName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> getForecastWeather( @PathVariable String countryName,
									   @PathVariable String cityName ) {
		return null;
	}
	
}
