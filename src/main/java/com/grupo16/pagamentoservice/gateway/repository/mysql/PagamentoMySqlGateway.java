package com.grupo16.pagamentoservice.gateway.repository.mysql;

import org.springframework.stereotype.Component;

import com.grupo16.pagamentoservice.domain.Pagamento;
import com.grupo16.pagamentoservice.gateway.PagamentoRepositoryGateway;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PagamentoMySqlGateway implements PagamentoRepositoryGateway {

	@Override
	public Pagamento obtemPorPagamentoExternoId(String pagamentoExternoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizarStatus(Pagamento pagamento) {
		// TODO Auto-generated method stub

	}

}
