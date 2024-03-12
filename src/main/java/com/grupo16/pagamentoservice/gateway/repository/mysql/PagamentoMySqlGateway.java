package com.grupo16.pagamentoservice.gateway.repository.mysql;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.grupo16.pagamentoservice.domain.Carrinho;
import com.grupo16.pagamentoservice.domain.Pagamento;
import com.grupo16.pagamentoservice.domain.StatusPagamento;
import com.grupo16.pagamentoservice.exception.ErroAoAcessarBancoDeDadosException;
import com.grupo16.pagamentoservice.exception.PagamentoNaoEncontradoException;
import com.grupo16.pagamentoservice.gateway.PagamentoRepositoryGateway;
import com.grupo16.pagamentoservice.gateway.repository.jpa.entity.PagamentoEntity;
import com.grupo16.pagamentoservice.gateway.repository.jpa.springdata.PagamentoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class PagamentoMySqlGateway implements PagamentoRepositoryGateway {

	private PagamentoRepository pagamentoRepository;

	@Override
	public Pagamento obtemPorPagamentoExternoId(String pagamentoExternoId) {
		try {

			Optional<PagamentoEntity> pagamentoOp = pagamentoRepository.findByPagamentoExternoId(pagamentoExternoId);

			if(pagamentoOp.isPresent()) {
				PagamentoEntity pagamentoEntity = pagamentoOp.get();

				StatusPagamento[] statusList = StatusPagamento.values();

				return Pagamento.builder()
						.id(pagamentoEntity.getId())
						.idPedido(pagamentoEntity.getPedidoId())
						.carrinho(Carrinho.builder().id(pagamentoEntity.getCarrinhoId()).build())
						.pagamentoExternoId(pagamentoEntity.getPagamentoExternoId())
						.status(statusList[pagamentoEntity.getStatus().intValue()])
						.build();
			}

			throw new PagamentoNaoEncontradoException();
		} catch (PagamentoNaoEncontradoException e) {
			log.warn("Pagamento não encontrado. pagamentoExternoId={}", pagamentoExternoId);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDeDadosException();
		}
	}

	@Override
	public void atualizarStatus(Pagamento pagamento) {
		try {

			Optional<PagamentoEntity> pagamentoOp = pagamentoRepository.findById(pagamento.getId());

			if(pagamentoOp.isPresent()) {
				PagamentoEntity pagamentoEntity = pagamentoOp.get();
				pagamentoEntity.setStatus((long) pagamento.getStatus().ordinal());
				pagamentoRepository.save(pagamentoEntity);
			}

			throw new PagamentoNaoEncontradoException();
		} catch (PagamentoNaoEncontradoException e) {
			log.warn("Pagamento não encontrado. pagamentoId={}", pagamento.getId());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDeDadosException();
		}

	}

	@Override
	public Long criar(Pagamento pagamento) {
		try {

			PagamentoEntity pagamentoEntity = PagamentoEntity.builder()
					.carrinhoId(pagamento.getCarrinho().getId())
					.pedidoId(pagamento.getIdPedido())
					.status((long) pagamento.getStatus().ordinal())
					.pagamentoExternoId(pagamento.getPagamentoExternoId())
					.build();

			pagamentoRepository.save(pagamentoEntity);

			return pagamentoEntity.getId();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarBancoDeDadosException();
		}
	}

}
