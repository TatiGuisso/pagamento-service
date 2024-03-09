package com.grupo16.pagamentoservice.gateway;

import com.grupo16.pagamentoservice.domain.Pagamento;

public interface PagamentoRepositoryGateway {

	Pagamento obtemPorPagamentoExternoId(String pagamentoExternoId);

	void atualizarStatus(Pagamento pagamento);

	Long criar(Pagamento pagamento);

}
