package com.grupo16.pagamentoservice.gateway.http.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grupo16.pagamentoservice.gateway.http.estoque.json.EstoqueJson;

@FeignClient(value = "ESTOQUE-SERVICE", path = "estoques")
public interface EstoqueServiceFeignClient {
	
	@PutMapping("efetuar-baixas")
	void efetuarBaixas(@RequestBody List<EstoqueJson> estoqueJsonList);
	
	@PutMapping("cancelar-reservas")
	void cancelarReservas(@RequestBody List<EstoqueJson> estoqueJsonList);

}
