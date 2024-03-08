package com.grupo16.pagamentoservice.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Carrinho {
	private Long id;
	private List<Produto> produtos;

}
