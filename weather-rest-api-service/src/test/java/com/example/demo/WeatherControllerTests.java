package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.nextcitizen.weather.app.controller.WeatherController;
import com.nextcitizen.weather.app.exception.WeatherNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= WeatherController.class)
public class WeatherControllerTests {
	
	@Autowired
	private WeatherController weatherController;
	
	@Test(expected = WeatherNotFoundException.class)
	public void testGetCurrentWeatherWithNullCountryName() throws Exception {
	    ResponseEntity<Object > obj=  weatherController.getCurrentWeather(null, "Dallas");
	}
	
	@Test(expected = WeatherNotFoundException.class)
	public void testGetCurrentWeatherWithNullCityName() throws Exception {
	    ResponseEntity<Object > obj=  weatherController.getCurrentWeather("Delaware", null);
	}
	
	@Test(expected = WeatherNotFoundException.class)
	public void testGetCurrentWeatherWithNumericCityName() throws Exception {
	    ResponseEntity <Object > obj=  weatherController.getCurrentWeather("America", "123");
	}
	
	@Test(expected = WeatherNotFoundException.class)
	public void testGetCurrentWeatherWithNumericCountryName() throws Exception {
	    ResponseEntity<Object > obj=  weatherController.getCurrentWeather("123", "Dallas");
	}

}
