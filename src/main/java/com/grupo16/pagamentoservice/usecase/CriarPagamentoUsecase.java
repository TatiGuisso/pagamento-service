package com.grupo16.pagamentoservice.usecase;

import org.springframework.stereotype.Service;

import com.grupo16.pagamentoservice.domain.Pagamento;
import com.grupo16.pagamentoservice.gateway.PagamentoRepositoryGateway;
import com.grupo16.pagamentoservice.gateway.SistemaPagamentoExternoGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CriarPagamentoUsecase {

	private SistemaPagamentoExternoGateway pagamentoExternoGateway;
	
	private PagamentoRepositoryGateway pagamentoRepositoryGateway;
	
	public Long criar(Pagamento pagamento) {
		String pagamentoExternoId = pagamentoExternoGateway.criar(pagamento);
		pagamento.criar(pagamentoExternoId);
		
		return pagamentoRepositoryGateway.criar(pagamento);
	}
}
