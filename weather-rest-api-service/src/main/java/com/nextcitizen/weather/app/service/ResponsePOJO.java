package com.nextcitizen.weather.app.service;

public class ResponsePOJO {

	private String visibility;
	private String timeZone;
	private String base;
	private String name;
	private sys sys;

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimezone(String timezone) {
		this.timeZone = timezone;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getName() {
		return name;
	}

	public void setCityName(String name) {
		this.name = name;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public sys getSys() {
		return sys;
	}

	public void setSys(sys sys) {
		this.sys = sys;
	}

}
