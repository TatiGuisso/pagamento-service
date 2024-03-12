package com.grupo16.pagamentoservice.gateway.http.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "PEDIDO-SERVICE", path = "pedidos")
public interface PedidoServiceFeignClient {
	
	@PutMapping("{id}/concluir")
	void concluir(@PathVariable(value = "id") Long id);
	
	@PutMapping("{id}/cancelar")
	void cancelar(@PathVariable(value = "id") Long id);
}
