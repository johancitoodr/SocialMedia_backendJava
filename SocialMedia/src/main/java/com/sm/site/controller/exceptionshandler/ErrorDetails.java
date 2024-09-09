package com.sm.site.controller.exceptionshandler;

import java.time.LocalDateTime;



public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String details;
	public ErrorDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timeStamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
}
