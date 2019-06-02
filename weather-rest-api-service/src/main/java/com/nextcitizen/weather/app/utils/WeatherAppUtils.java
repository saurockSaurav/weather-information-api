package com.nextcitizen.weather.app.utils;

public class WeatherAppUtils {
	
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
