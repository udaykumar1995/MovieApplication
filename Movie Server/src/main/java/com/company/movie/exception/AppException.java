package com.company.movie.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private AppError error;
	private String errorMessage;

	public AppException(AppError error, String errorMessage) {
		super();
		this.error = error;
		this.errorMessage = errorMessage;
	}

	public AppException(AppError error) {
		super();
		this.error = error;
	}

	public AppException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public AppError getError() {
		return error;
	}

	public void setError(AppError error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
