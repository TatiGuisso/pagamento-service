package com.grupo16.pagamentoservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagamentoExterno {
	private StatusPagamentoExterno status;

	
	public boolean isOk() {
		return StatusPagamentoExterno.APROVADO.equals(status);
	}
}
