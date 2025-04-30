package com.appservicobno.appservicobno.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "senha", length = 255, nullable = false)
	private String senha;

	@CreationTimestamp
	private Instant dataHoraCriação;

	@UpdateTimestamp
	private Instant dataHoraAtualização;

	public Usuario() {
	}

	public Usuario(Long id, String nome, String email, String senha, Instant dataHoraCriação,
			Instant dataHoraAtualização) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataHoraCriação = dataHoraCriação;
		this.dataHoraAtualização = dataHoraAtualização;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Instant getDataHoraCriação() {
		return dataHoraCriação;
	}

	public void setDataHoraCriação(Instant dataHoraCriação) {
		this.dataHoraCriação = dataHoraCriação;
	}

	public Instant getDataHoraAtualização() {
		return dataHoraAtualização;
	}

	public void setDataHoraAtualização(Instant dataHoraAtualização) {
		this.dataHoraAtualização = dataHoraAtualização;
	}
}
