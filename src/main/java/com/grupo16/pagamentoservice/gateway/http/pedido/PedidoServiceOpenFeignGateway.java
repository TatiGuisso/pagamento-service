package com.grupo16.pagamentoservice.gateway.http.pedido;

import org.springframework.stereotype.Component;

import com.grupo16.pagamentoservice.exception.ErrorAoAcessarPedidoServiceException;
import com.grupo16.pagamentoservice.gateway.PedidoServiceGateway;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PedidoServiceOpenFeignGateway implements PedidoServiceGateway {
	
	private PedidoServiceFeignClient pedidoServiceFeignClient;
	
	@Override
	public void efetuarFechamento(Long idPedido) {
		try {
			
			pedidoServiceFeignClient.concluir(idPedido);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorAoAcessarPedidoServiceException();
		}
	}

	@Override
	public void efetuarCancelamento(Long idPedido) {
		try {
			
			pedidoServiceFeignClient.cancelar(idPedido);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErrorAoAcessarPedidoServiceException();
		}
	}
}
