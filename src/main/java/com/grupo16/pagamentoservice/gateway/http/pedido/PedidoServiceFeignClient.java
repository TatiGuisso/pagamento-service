package com.grupo16.pagamentoservice.gateway.http.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PEDIDO-SERVICE", path = "pedidos")
public interface PedidoServiceFeignClient {
	
	@PatchMapping("{id}/concluir")
	void concluir(@PathVariable(value = "id") Long id);
	
	@PatchMapping("{id}/cancelar")
	void cancelar(@PathVariable(value = "id") Long id);
}
