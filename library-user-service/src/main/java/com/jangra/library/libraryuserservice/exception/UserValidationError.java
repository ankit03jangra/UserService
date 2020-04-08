package com.jangra.library.libraryuserservice.exception;


public class UserValidationError {
	
	private String errorCode;
    private String message;
 
	public UserValidationError(String errorCode, String message) {
		super();
        this.errorCode = errorCode;
        this.message = message;	
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}