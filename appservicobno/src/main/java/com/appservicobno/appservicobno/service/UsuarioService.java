package com.appservicobno.appservicobno.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.appservicobno.appservicobno.dto.CriarUsuarioDTO;
import com.appservicobno.appservicobno.dto.UsuarioDTO;
import com.appservicobno.appservicobno.entity.Usuario;
import com.appservicobno.appservicobno.repository.UsuarioRepositorio;

/**
 * Serviço responsável pela lógica de negócios relacionada aos usuários.
 * 
 * <p>Este serviço permite a criação e a consulta de usuários. Ele interage diretamente com o repositório de usuários
 * para armazenar e recuperar informações sobre usuários.</p>
 * 
 * @author bruno.silva
 * @since 1.0
 * @created 29/04/2025
 */
@Service
public class UsuarioService {

    private UsuarioRepositorio usuarioRepositorio;

    /**
     * Construtor que inicializa o serviço com o repositório de usuários.
     * 
     * @param usuarioRepositorio O repositório de usuários usado para operações CRUD.
     */
    public UsuarioService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Cadastra um novo usuário no sistema.
     * 
     * <p>Este método cria um novo usuário com base nos dados fornecidos no objeto {@link CriarUsuarioDTO}.
     * O ID do usuário é gerado aleatoriamente, e o usuário é salvo no banco de dados.</p>
     * 
     * @param criarUsuarioDTO O DTO que contém as informações do novo usuário.
     * @return O ID do usuário recém-criado.
     * @author bruno.silva
	 * @since 1.0
	 * @created 29/04/2025
     */
    public Long cadastrarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        Usuario usuario = new Usuario(
                null, 
                criarUsuarioDTO.nome(), 
                criarUsuarioDTO.email(), 
                criarUsuarioDTO.senha(), 
                Instant.now(), 
                Instant.now());

        usuarioRepositorio.save(usuario);

        return usuario.getId();
    }

    /**
     * Consulta um usuário pelo seu ID.
     * 
     * <p>Este método recupera um usuário do banco de dados com base no seu ID. Caso o usuário não seja encontrado,
     * é lançada uma exceção {@link NumberFormatException}.</p>
     * 
     * @param usuarioId O ID do usuário a ser consultado.
     * @return O DTO do usuário consultado.
     * @throws NumberFormatException Caso o usuário não seja encontrado.
	 * @author bruno.silva
	 * @since 1.0
	 * @created 29/04/2025
     */
    public UsuarioDTO consultarUsuarioPorId(long usuarioId) {
        Usuario usuarioConsultado = usuarioRepositorio.findById(usuarioId).orElse(null);

        if (usuarioConsultado == null) {
            throw new NumberFormatException("Usuário não encontrado.");
        }

        return converterUsuarioParaDTO(usuarioConsultado);
    }

    /**
     * Converte um {@link Usuario} para um {@link UsuarioDTO}.
     * 
     * @param usuario O usuário a ser convertido.
     * @return O DTO correspondente ao usuário.
     * @author bruno.silva
	 * @since 1.0
	 * @created 02/05/2025
     */
    private UsuarioDTO converterUsuarioParaDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha());
    }
}
