package com.grupo16.pagamentoservice.gateway.http.sistemaexterno;

import org.springframework.stereotype.Component;

import com.grupo16.pagamentoservice.domain.PagamentoExterno;
import com.grupo16.pagamentoservice.domain.StatusPagamentoExterno;
import com.grupo16.pagamentoservice.gateway.SistemaPagamentoExternoGateway;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MockSistemaExterno implements SistemaPagamentoExternoGateway {

	@Override
	public PagamentoExterno getPagamentoExterno(String pagamentoExternoId) {
		log.warn("### MOCK ###");
		return PagamentoExterno.builder().status(StatusPagamentoExterno.APROVADO).build();
	}

}
