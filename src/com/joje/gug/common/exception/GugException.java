package com.joje.gug.common.exception;

public class GugException extends RuntimeException {

	private static final long serialVersionUID = 112312314123L;

	public GugException() {
	}

	public GugException(String message) {
		super(message);
	}

	public GugException(Throwable cause) {
		super(cause);
	}

	public GugException(String message, Throwable cause) {
		super(message, cause);
	}
}
