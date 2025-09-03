package com.appservicobno.dto;

/**
 * DTO (Data Transfer Object) para o login de um usuário.
 * 
 * <p>Esta classe é utilizada para transferir os dados necessários para autenticar um usuário.</p>
 * 
 * @param email O e-mail do usuário.
 * @param senha A senha do usuário.
 */
public record LoginDTO (String email, String senha) {
}
