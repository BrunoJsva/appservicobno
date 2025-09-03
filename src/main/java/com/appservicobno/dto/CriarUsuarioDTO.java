package com.appservicobno.dto;

/**
 * DTO (Data Transfer Object) para a criação de um usuário.
 * 
 * <p>Esta classe é utilizada para transferir os dados necessários para criar um novo usuário.</p>
 * 
 * @param nome  O nome do usuário.
 * @param email O e-mail do usuário.
 * @param senha A senha do usuário.
 */
public record CriarUsuarioDTO(String nome, String email, String senha) {
}
