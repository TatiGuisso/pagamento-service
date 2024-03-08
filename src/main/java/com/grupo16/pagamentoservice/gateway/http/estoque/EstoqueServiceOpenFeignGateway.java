package com.grupo16.pagamentoservice.gateway.http.estoque;

import java.util.List;

import org.springframework.stereotype.Component;

import com.grupo16.pagamentoservice.domain.Produto;
import com.grupo16.pagamentoservice.exception.ErrorAoAcessarEstoqueServiceException;
import com.grupo16.pagamentoservice.gateway.EstoqueServiceGateway;
import com.grupo16.pagamentoservice.gateway.http.estoque.json.EstoqueJson;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Component
public class EstoqueServiceOpenFeignGateway implements EstoqueServiceGateway {

	private EstoqueServiceFeignClient estoqueServiceFeignClient;

	@Override
	public void efetuarBaixa(List<Produto> produtos) {
		try {
			List<EstoqueJson> estoquesJson = produtos.stream().map(p -> new EstoqueJson(null, p.getId(), p.getQuantidade())).toList();
			
			estoqueServiceFeignClient.efetuarBaixas(estoquesJson);

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
