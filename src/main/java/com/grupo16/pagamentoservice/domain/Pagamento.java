package com.grupo16.pagamentoservice.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pagamento {
	private Long id;
	private Long idPedido;
	private Carrinho carrinho;
	private String pagamentoExternoId;
	private StatusPagamento status;
	
	public void criar(String pagamentoExternoId) {
		status = StatusPagamento.AGUARDANDO_PAGAMENTO;
		this.pagamentoExternoId = pagamentoExternoId;
	}
}
