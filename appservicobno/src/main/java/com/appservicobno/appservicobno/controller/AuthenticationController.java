package com.appservicobno.appservicobno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appservicobno.appservicobno.dto.AutenticadorDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AutenticadorDTO autenticadorDTO) {
		
		var userNamePassaword = new UsernamePasswordAuthenticationToken(
				autenticadorDTO.login(), autenticadorDTO.senha());
		
		var auth = this.authenticationManager.authenticate(userNamePassaword);
		return ResponseEntity.ok().build();
	}
}
