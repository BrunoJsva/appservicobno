package com.appservicobno.dto;

/**
 * DTO (Data Transfer Object) para representar um usuário.
 * 
 * <p>Esta classe é utilizada para transferir os dados de um usuário.</p>
 * 
 * @param id    O identificador único do usuário.
 * @param nome  O nome do usuário.
 * @param email O e-mail do usuário.
 * @param senha A senha do usuário.
 */
public record UsuarioDTO(Long id, String nome, String email, String senha) {
}
