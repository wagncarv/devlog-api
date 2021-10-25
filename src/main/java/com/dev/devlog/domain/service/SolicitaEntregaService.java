package com.dev.devlog.domain.service;


import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.devlog.domain.model.Entrega;
import com.dev.devlog.domain.model.StatusEntrega;
import com.dev.devlog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitaEntregaService {
	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		var cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega.setCliente(cliente);
		
		return entregRepository.save(entrega);
	}
}
