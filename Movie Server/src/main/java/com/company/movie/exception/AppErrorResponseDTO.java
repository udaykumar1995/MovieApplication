package com.company.movie.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppErrorResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String message;

	public AppErrorResponseDTO(String code, String name, String message) {
		this.code = code;
		this.name = name;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
