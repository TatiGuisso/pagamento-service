package com.grupo16.pagamentoservice.gateway;

public interface PedidoServiceGateway {

	void efetuarFechamento(Long idPedido);

	void efetuarCancelamento(Long idPedido);

}
