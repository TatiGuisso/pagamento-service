package com.grupo16.pagamentoservice.gateway.repository.jpa.springdata;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo16.pagamentoservice.gateway.repository.jpa.entity.PagamentoEntity;

public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Long>{

	Optional<PagamentoEntity> findByPagamentoExternoId(String pagamentoExternoId);

}
