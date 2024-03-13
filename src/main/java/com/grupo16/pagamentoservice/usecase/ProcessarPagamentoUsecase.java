package com.grupo16.pagamentoservice.usecase;

import org.springframework.stereotype.Service;

import com.grupo16.pagamentoservice.domain.Carrinho;
import com.grupo16.pagamentoservice.domain.Pagamento;
import com.grupo16.pagamentoservice.domain.PagamentoExterno;
import com.grupo16.pagamentoservice.domain.StatusPagamento;
import com.grupo16.pagamentoservice.gateway.CarrinhoServiceGateway;
import com.grupo16.pagamentoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pagamentoservice.gateway.PagamentoRepositoryGateway;
import com.grupo16.pagamentoservice.gateway.PedidoServiceGateway;
import com.grupo16.pagamentoservice.gateway.SistemaPagamentoExternoGateway;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProcessarPagamentoUsecase {

	private SistemaPagamentoExternoGateway sistemaPagamentoExternoGateway;
	private CarrinhoServiceGateway carrinhoServiceGateway;
	private EstoqueServiceGateway estoqueServiceGateway;
	private PedidoServiceGateway pedidoServiceGateway;
	private PagamentoRepositoryGateway pagamentoRepositoryGateway;
	
	public void processar(String pagamentoExternoId) {
		PagamentoExterno pagamentoExterno = sistemaPagamentoExternoGateway.getPagamentoExterno(pagamentoExternoId);
		Pagamento pagamento = pagamentoRepositoryGateway.obtemPorPagamentoExternoId(pagamentoExternoId);
		Carrinho carrinho = carrinhoServiceGateway.obtemPorId(pagamento.getCarrinho().getId());
		if(pagamentoExterno.isOk()) {
			sagaPagamentoComSucesso(pagamento, carrinho);
		} else {
			sagaPagamentoInsucesso(pagamento, carrinho);
		}
		
	}
	
	//TODO: deve criar os fallbacks
	private void sagaPagamentoComSucesso(Pagamento pagamento, Carrinho carrinho) {
		estoqueServiceGateway.efetuarBaixa(carrinho.getProdutos());
		pedidoServiceGateway.efetuarFechamento(pagamento.getIdPedido());
		pagamentoRepositoryGateway.atualizarStatus(Pagamento.builder().id(pagamento.getId()).status(StatusPagamento.PAGO).build());
	}

	//TODO: deve criar os fallbacks
	private void sagaPagamentoInsucesso(Pagamento pagamento, Carrinho carrinho) {
		estoqueServiceGateway.cancelarReserva(carrinho.getProdutos());
		pedidoServiceGateway.efetuarCancelamento(pagamento.getIdPedido());
		pagamentoRepositoryGateway.atualizarStatus(Pagamento.builder().id(pagamento.getId()).status(StatusPagamento.CANCELADO).build());
	}
}
