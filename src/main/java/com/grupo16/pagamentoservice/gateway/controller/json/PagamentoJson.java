package com.grupo16.pagamentoservice.gateway.controller.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PagamentoJson {
	private Long id;
	private Long carrinhoId;
	private Long pedidoId;
	private String pagamentoExternoId;
}
