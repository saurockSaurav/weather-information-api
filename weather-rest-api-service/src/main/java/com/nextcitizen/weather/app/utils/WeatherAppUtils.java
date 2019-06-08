package com.nextcitizen.weather.app.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.routines.UrlValidator;

import com.nextcitizen.weather.app.service.ResponsePOJO;

public class WeatherAppUtils {
	
	public Map<String, String> userFriendlyInformation(final ResponsePOJO response) {
		
		HashMap<String, String> hashMap = new HashMap<>();
		if (response != null) {
			hashMap.put("City", response.getName());
			hashMap.put("Timezone", response.getTimeZone());
			hashMap.put("Base", response.getBase());
			hashMap.put("Visibility", response.getVisibility());
		}
		return hashMap;
		
	}
	
	public boolean isValidURL(final String url) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}
	
	public static void main(String[] args) {
		WeatherAppUtils weatherAppUtils = new WeatherAppUtils();
		System.out.println(weatherAppUtils.isNumeric("9"));
		System.out.println(weatherAppUtils.reverseTheString("RaceCar"));
	}
	
	public boolean isNumeric(String value) {
		return value.matches("[0-9]+");
	}
	
	public String reverseTheString(final String str) {
		StringBuilder strBulder= new StringBuilder(str);
		return strBulder.reverse().toString();
	}
	
}
