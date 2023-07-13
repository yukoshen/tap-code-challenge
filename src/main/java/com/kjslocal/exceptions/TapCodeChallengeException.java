package com.kjslocal.exceptions;

public class TapCodeChallengeException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private final String errorMessage;

	public TapCodeChallengeException(String errMessage) {
		super();
		this.errorMessage = errMessage;
	}

	public String getErrMessage() {
		return errorMessage;
	}

}
