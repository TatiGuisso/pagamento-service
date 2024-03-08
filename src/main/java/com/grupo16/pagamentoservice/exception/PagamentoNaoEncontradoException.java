package com.grupo16.pagamentoservice.exception;

import lombok.Getter;

@Getter
public class PagamentoNaoEncontradoException extends SystemBaseException {
	private static final long serialVersionUID = -4314308324201095326L;
	
	private final String code = "pagamento.pagamentoNaoEncontrado";//NOSONAR
	private final String message = "Pagamento não encontrado.";//NOSONAR
	private final Integer httpStatus = 404;//NOSONAR
}
