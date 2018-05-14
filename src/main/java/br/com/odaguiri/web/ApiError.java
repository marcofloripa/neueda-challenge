package br.com.odaguiri.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus status;
	private LocalDateTime timestamp;
	private String error;
	private String request;

	public ApiError(HttpStatus status, LocalDateTime timestamp, String error, String request) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.error = error;
		this.request = request;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

}
