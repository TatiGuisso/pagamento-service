package com.grupo16.pagamentoservice.gateway.http.estoque;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.pagamentoservice.domain.Produto;
import com.grupo16.pagamentoservice.exception.ErrorAoAcessarEstoqueServiceException;
import com.grupo16.pagamentoservice.exception.SystemBaseException;
import com.grupo16.pagamentoservice.exception.SystemExternalException;
import com.grupo16.pagamentoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pagamentoservice.gateway.http.estoque.json.EstoqueJson;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class EstoqueServiceOpenFeignGateway implements EstoqueServiceGateway {

	private EstoqueServiceFeignClient estoqueServiceFeignClient;

	private ObjectMapper objectMapper;
	
	@Override
	public void efetuarBaixa(List<Produto> produtos) {
		try {
			List<EstoqueJson> estoquesJson = produtos.stream().map(p -> new EstoqueJson(null, p.getId(), p.getQuantidade())).toList();
			
			estoqueServiceFeignClient.efetuarBaixas(estoquesJson);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(e instanceof FeignException feignException) {
				try {
					String exceptionResponseBody = feignException.contentUTF8();
					SystemBaseException systemBaseException = objectMapper.readValue(exceptionResponseBody, SystemExternalException.class);
					throw systemBaseException;

				} catch (Exception e2) {
					log.error(e2.getMessage(), e);
					throw new ErrorAoAcessarEstoqueServiceException();
				}
			}
			throw new ErrorAoAcessarEstoqueServiceException();
		}
		
	}

	@Override
	public void cancelarReserva(List<Produto> produtos) {
		try {
			List<EstoqueJson> estoquesJson = produtos.stream().map(p -> new EstoqueJson(null, p.getId(), p.getQuantidade())).toList();
			
			estoqueServiceFeignClient.cancelarReservas(estoquesJson);

		} catch (Exception e) {
			log.error(e.getMessage(), e);

			if(e instanceof FeignException feignException) {

				//TODO: implementar
				String exceptionResponseBody = feignException.contentUTF8();
				log.error(exceptionResponseBody);
			}
			throw new ErrorAoAcessarEstoqueServiceException();
		}

		
	}

}
