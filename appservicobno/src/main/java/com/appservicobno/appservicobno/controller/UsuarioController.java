package com.appservicobno.appservicobno.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appservicobno.appservicobno.entity.Usuario;
import com.appservicobno.appservicobno.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
	
	private UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrar(@RequestBody CriarUsuarioDTO criarUsuarioDTO) {
		
		Long usuarioId = service.cadastrarUsuario(criarUsuarioDTO);

		return ResponseEntity.created(URI.create("/v1/usuarios/" + usuarioId.toString())).build();
	}
	
	@GetMapping("/{UsuarioId}")
	public ResponseEntity<Usuario> consultarPeloId(@PathVariable("UsuarioId") String usuarioId) {
		// Aqui você pode adicionar a lógica para salvar o usuário no banco de dados
		// e retornar a resposta apropriada.

		return null;
	}

}
