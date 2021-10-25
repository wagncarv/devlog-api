package com.dev.devlog.domain.service;

import org.springframework.stereotype.Service;

import com.dev.devlog.domain.exception.EntidadeNaoEncontradaException;
import com.dev.devlog.domain.model.Entrega;
import com.dev.devlog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
