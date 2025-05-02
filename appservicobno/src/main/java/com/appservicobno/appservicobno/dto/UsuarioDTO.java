package com.appservicobno.appservicobno.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UsuarioDTO {
    private final Long id;
    private final String nome;
    private final String email;

    @JsonIgnore
    private final String senha;

    public UsuarioDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
