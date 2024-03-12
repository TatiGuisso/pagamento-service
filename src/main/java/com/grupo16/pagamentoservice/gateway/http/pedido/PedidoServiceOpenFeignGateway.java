package com.grupo16.pagamentoservice.gateway.http.pedido;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.pagamentoservice.exception.ErrorAoAcessarPedidoServiceException;
import com.grupo16.pagamentoservice.exception.SystemBaseException;
import com.grupo16.pagamentoservice.exception.SystemExternalException;
import com.grupo16.pagamentoservice.gateway.PedidoServiceGateway;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PedidoServiceOpenFeignGateway implements PedidoServiceGateway {
	
	private PedidoServiceFeignClient pedidoServiceFeignClient;
	
	private ObjectMapper objectMapper;
	
	@Override
	public void efetuarFechamento(Long idPedido) {
		try {
			
			pedidoServiceFeignClient.concluir(idPedido);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(e instanceof FeignException feignException) {
				try {
					String exceptionResponseBody = feignException.contentUTF8();
					SystemBaseException systemBaseException = objectMapper.readValue(exceptionResponseBody, SystemExternalException.class);
					throw systemBaseException;

				} catch (Exception e2) {
					log.error(e2.getMessage(), e);
					throw new ErrorAoAcessarPedidoServiceException();
				}
			}
			throw new ErrorAoAcessarPedidoServiceException();
		}
	}

	@Override
	public void efetuarCancelamento(Long idPedido) {
		try {
			
			pedidoServiceFeignClient.cancelar(idPedido);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(e instanceof FeignException feignException) {
				try {
					String exceptionResponseBody = feignException.contentUTF8();
					SystemBaseException systemBaseException = objectMapper.readValue(exceptionResponseBody, SystemExternalException.class);
					throw systemBaseException;

				} catch (Exception e2) {
					log.error(e2.getMessage(), e);
					throw new ErrorAoAcessarPedidoServiceException();
				}
			}
			throw new ErrorAoAcessarPedidoServiceException();
		}
	}
}
