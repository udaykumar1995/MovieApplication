package com.company.movie.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.company.movie.exception.AppError;
import com.company.movie.exception.AppErrorResponseDTO;
import com.company.movie.exception.AppException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = { AppException.class })
	public ResponseEntity<AppErrorResponseDTO> wrapperSDPException(final AppException e) {

		logError(e);
		AppError error = e.getError();
		AppErrorResponseDTO err = new AppErrorResponseDTO(error.getCode(), error.name(),
				StringUtils.isEmpty(e.getErrorMessage()) ? error.getUserMessage() : e.getErrorMessage());
		return error(err, error.getHttpStatusCode());
	}

	private ResponseEntity<AppErrorResponseDTO> error(final AppErrorResponseDTO err, final int httpStatusCode) {
		return new ResponseEntity<AppErrorResponseDTO>(err,
				org.springframework.http.HttpStatus.valueOf(httpStatusCode));
	}

	private void logError(AppException e) {
		String errorMessage = StringUtils.isEmpty(e.getErrorMessage()) ? e.getError().getUserMessage()
				: e.getErrorMessage();
		System.out.println("Caught exception while methodX. Please investigate:" + errorMessage.toString());
			}
}
