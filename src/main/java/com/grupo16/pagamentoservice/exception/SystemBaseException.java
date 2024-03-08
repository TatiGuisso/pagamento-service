package com.grupo16.pagamentoservice.exception;

public abstract class SystemBaseException extends RuntimeException {
	private static final long serialVersionUID = 3919057225565415385L;
	
	public abstract String getCode();
	public abstract Integer getHttpStatus();
	@Override
	public abstract String getMessage();
}
