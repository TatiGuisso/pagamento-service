package com.grupo16.pagamentoservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Produto {
	private Long id;
	private Long quantidade;
}
