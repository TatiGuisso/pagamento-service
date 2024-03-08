package com.grupo16.pagamentoservice.gateway;

import java.util.List;

import com.grupo16.pagamentoservice.domain.Produto;

public interface EstoqueServiceGateway {

	void efetuarBaixa(List<Produto> produtos);

	void cancelarReserva(List<Produto> produtos);

}
