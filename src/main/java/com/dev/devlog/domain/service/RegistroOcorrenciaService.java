package com.dev.devlog.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.devlog.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		var entrega = buscaEntregaService.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
}
