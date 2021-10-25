package com.dev.devlog.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 8934149350853764056L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
