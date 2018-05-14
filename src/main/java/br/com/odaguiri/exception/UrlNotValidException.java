package br.com.odaguiri.exception;


public class UrlNotValidException extends RuntimeException {

	private static final long serialVersionUID = -4495561596653331487L;

	public UrlNotValidException(String exception) {
		super(exception);
	}
}
