package com.company.movie.exception;

import org.apache.http.HttpStatus;

public enum AppError {

	MOVIE_FETCH_FAILED("MOVIE_001", "Error While fetching Movie", HttpStatus.SC_INTERNAL_SERVER_ERROR),
	MOVIES_FETCH_FAILED("MOVIE_002", "Error While fetching Movies", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    MOVIE_UPDATE_FAILED("MOVIE_003", "Error While updating Movies", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    MOVIE_ADD_FAILED("MOVIE_004", "Error While adding Movie", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    MOVIE_DELETE_FAILED("MOVIE_005", "Error While deleting Movie", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    MOVIES_DOES_NOT_EXIST("MOVIE_006", "Movies does not exist", HttpStatus.SC_INTERNAL_SERVER_ERROR),
    MOVIE_DOES_NOT_EXIST("MOVIE_007", "Movie does not exist", HttpStatus.SC_INTERNAL_SERVER_ERROR),
	AUTH_TOKEN_INVALID("MOVIE_008", "Auth Token Invalid", HttpStatus.SC_UNAUTHORIZED),
	AUTH_TOKEN_VALIDATION_FAILED("MOVIE_009", "Auth Token Invalid", HttpStatus.SC_INTERNAL_SERVER_ERROR),
	LOGIN_INVALID("MOVIE_010", "Invalid Login", HttpStatus.SC_INTERNAL_SERVER_ERROR);
    private final String code;
    private final String userMessage;
    private final int httpStatusCode;
    
    private AppError(String code, String userMessage, int httpStatusCode) {
        this.code = code;
        this.userMessage = userMessage;
        this.httpStatusCode = httpStatusCode;
    }

	public String getCode() {
		return code;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}
}
