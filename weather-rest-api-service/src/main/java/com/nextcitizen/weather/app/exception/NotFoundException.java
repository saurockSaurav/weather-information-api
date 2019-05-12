package com.nextcitizen.weather.app.exception;

public class NotFoundException extends Exception {

	static final long serialVersionUID = -7776L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String msg) {
		super(msg);
	}

	public NotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
