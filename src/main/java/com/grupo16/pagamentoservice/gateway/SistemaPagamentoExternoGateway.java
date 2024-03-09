package com.grupo16.pagamentoservice.gateway;

import com.grupo16.pagamentoservice.domain.Pagamento;
import com.grupo16.pagamentoservice.domain.PagamentoExterno;

public interface SistemaPagamentoExternoGateway {

	PagamentoExterno getPagamentoExterno(String pagamentoExternoId);

	String criar(Pagamento pagamento);

}
