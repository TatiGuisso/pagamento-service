package com.grupo16.pagamentoservice.gateway.http.carrinho.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemJson {
	private Long idItem;
	private Long idProduto;
	private Integer quantidade;
	private Double precoUnitario;
}
