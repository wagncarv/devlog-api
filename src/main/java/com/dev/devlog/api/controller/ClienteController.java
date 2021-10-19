package com.dev.devlog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devlog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var c1 = new Cliente();
		c1.setNome("Jonas");
		c1.setEmail("jonas@mail.com");
		c1.setTelefone("22999999999");
		c1.setId(1L);
		
		var c2 = new Cliente();
		c2.setNome("Maria");
		c2.setEmail("maria@mail.com");
		c2.setTelefone("31909899999");
		c2.setId(2L);
		return Arrays.asList(c1, c2);
	}

}
