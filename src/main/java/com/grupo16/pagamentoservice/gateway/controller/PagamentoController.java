package com.grupo16.pagamentoservice.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grupo16.pagamentoservice.domain.Carrinho;
import com.grupo16.pagamentoservice.domain.Pagamento;
import com.grupo16.pagamentoservice.gateway.controller.json.PagamentoJson;
import com.grupo16.pagamentoservice.gateway.controller.json.ProcessarPagamentoJson;
import com.grupo16.pagamentoservice.usecase.CriarPagamentoUsecase;
import com.grupo16.pagamentoservice.usecase.ProcessarPagamentoUsecase;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RequestMapping("pagamentos")
public class PagamentoController {

	private CriarPagamentoUsecase criarPagamentoUsecase;
	
	private ProcessarPagamentoUsecase processarPagamentoUsecase;
	
	
	@PostMapping()
	public void criar(@RequestBody PagamentoJson requestBody) {
		log.trace("Start requestBody={}", requestBody);
		
		Pagamento pagamento = Pagamento.builder()
				.idPedido(requestBody.getId())
				.carrinho(Carrinho.builder().id(requestBody.getCarrinhoId()).build())
				.build();
		
		Long pagamentoId = criarPagamentoUsecase.criar(pagamento);
		
		log.trace("End pagamentoId={}", pagamentoId);
	}
	
	@PostMapping("processar")
	public void processar(@RequestBody ProcessarPagamentoJson requestBody) {
		log.trace("Start requestBody={}", requestBody);
		processarPagamentoUsecase.processar(requestBody.getPagamentoExternoId());
		log.trace("End");
	}
}
