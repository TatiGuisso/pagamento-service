package com.grupo16.pagamentoservice.gateway.http.carrinho;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupo16.pagamentoservice.domain.Carrinho;
import com.grupo16.pagamentoservice.domain.Produto;
import com.grupo16.pagamentoservice.exception.ErrorAoAcessarCarrinhoServiceException;
import com.grupo16.pagamentoservice.exception.SystemBaseException;
import com.grupo16.pagamentoservice.exception.SystemExternalException;
import com.grupo16.pagamentoservice.gateway.CarrinhoServiceGateway;
import com.grupo16.pagamentoservice.gateway.http.carrinho.json.CarrinhoJson;

import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class CarrinhoServiceOpenFeignGateway implements CarrinhoServiceGateway {

	private CarrinhoServiceFeignClient carrinhoServiceFeignClient;

	private ObjectMapper objectMapper;
	
	@Override
	public Carrinho obtemPorId(Long id) {
		try {

			CarrinhoJson carrinhoJson = carrinhoServiceFeignClient.obterPorId(id);

			List<Produto> produtos = 
					carrinhoJson.getItens().stream()
					.map(i -> Produto.builder().id(i.getIdProduto()).quantidade(i.getQuantidade().longValue()).build())
					.toList();

			return Carrinho.builder()
					.id(id)
					.produtos(produtos)
					.build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if(e instanceof FeignException feignException) {
				try {
					String exceptionResponseBody = feignException.contentUTF8();
					SystemBaseException systemBaseException = objectMapper.readValue(exceptionResponseBody, SystemExternalException.class);
					throw systemBaseException;

				} catch (Exception e2) {
					log.error(e2.getMessage(), e);
					throw new ErrorAoAcessarCarrinhoServiceException();
				}
			}
			throw new ErrorAoAcessarCarrinhoServiceException();
		}	
	}

}
