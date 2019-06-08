package com.nextcitizen.weather.app.exception;

import java.net.URI;

public class WeatherNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The url which the error occured
     */
    private URI url;

    /**
     * The error message
     */
    private String message;

    public WeatherNotFoundException(String message, URI url) {
        this.message = message;
        this.url = url;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}