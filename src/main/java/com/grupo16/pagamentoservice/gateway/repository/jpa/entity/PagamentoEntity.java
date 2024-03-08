package com.grupo16.pagamentoservice.gateway.repository.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Pagamento")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long carrinhoId;
	private Long pedidoId;
	
	@Setter
	private Long status;
	private String pagamentoExternoId;
}
