package com.dev.devlog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.devlog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	private EntregaRepository entregaRepository;
	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public void finalizar(Long entregaId) {
		var entrega = buscaEntregaService.buscar(entregaId);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}

}
