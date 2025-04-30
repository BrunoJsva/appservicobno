package com.appservicobno.appservicobno.service;

import java.time.Instant;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.appservicobno.appservicobno.controller.CriarUsuarioDTO;
import com.appservicobno.appservicobno.entity.Usuario;
import com.appservicobno.appservicobno.repository.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	private UsuarioRepositorio usuarioRepositorio;
	
	public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	public Long cadastrarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
		
		Usuario usuario = new Usuario(
				new Random().nextLong(), 
				criarUsuarioDTO.nome(), 
				criarUsuarioDTO.email(), 
				criarUsuarioDTO.senha(), 
				Instant.now(), 
				Instant.now());
		
		usuarioRepositorio.save(usuario);
		
		return usuario.getId();
	}

	public void consultarUsuarioPorId(String usuarioId) {
		// Aqui você pode adicionar a lógica para consultar o usuário pelo ID
		// e retornar a resposta apropriada.

	}
}
