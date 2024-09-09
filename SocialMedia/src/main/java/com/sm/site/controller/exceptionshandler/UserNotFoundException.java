package com.sm.site.controller.exceptionshandler;



public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException (String message) {
	super(message);
	
	}
}
