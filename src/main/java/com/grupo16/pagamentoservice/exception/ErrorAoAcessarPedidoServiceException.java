package com.grupo16.pagamentoservice.exception;

import lombok.Getter;

@Getter
public class ErrorAoAcessarPedidoServiceException extends SystemBaseException {
	private static final long serialVersionUID = 3172051907551241565L;
	
	private final String code = "pagamento.erroAcessarPedidoService";//NOSONAR
	private final String message = "Erro ao acessar o pedido service.";//NOSONAR
	private final Integer httpStatus = 500;//NOSONAR
}
