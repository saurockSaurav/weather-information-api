package com.nextcitizen.weather.app.utils;

import org.apache.commons.validator.routines.UrlValidator;

import com.nextcitizen.weather.app.exception.WeatherNotFoundException;

public class WeatherAppUtils extends CustomStringUtils {
	
	@Override
	public boolean isValidURL(final String url) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}
	
	@Override
	public boolean isAlphabhate(String value) {
		 if ( isNotBlank(value) &&  doesNotHaveSpecialCharacter(value)) {
			 return true;
		 }
		 else {
			 return false;
		 }
		 //String message = " Illegal Value in weather HTTP request : " + value;
		 //throw new WeatherNotFoundException( message);
	}
	
}
