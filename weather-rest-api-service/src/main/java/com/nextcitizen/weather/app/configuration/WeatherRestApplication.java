package com.nextcitizen.weather.app.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan ({"com.nextcitizen.weather", "com.github.fedy2.weather"})
public class WeatherRestApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherRestApplication.class, args);
	}
	

}
