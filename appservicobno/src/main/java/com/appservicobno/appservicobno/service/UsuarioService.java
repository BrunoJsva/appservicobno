package com.appservicobno.appservicobno.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.appservicobno.appservicobno.dto.CriarUsuarioDTO;
import com.appservicobno.appservicobno.dto.LoginDTO;
import com.appservicobno.appservicobno.dto.UsuarioDTO;
import com.appservicobno.appservicobno.entity.Usuario;
import com.appservicobno.appservicobno.exception.EmailJaCadastradoException;
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
     * <p>Este método cria um novo usuário com base nos dados fornecidos em {@link CriarUsuarioDTO}.
     * Antes do cadastro, é verificado se o e-mail já está registrado. Caso não esteja, o usuário
     * é salvo no banco de dados com data de criação e atualização definidas como o momento atual.</p>
     *
     * @param criarUsuarioDTO O DTO que contém nome, e-mail e senha do novo usuário.
     * @return O ID do usuário recém-criado.
     * @throws IllegalArgumentException se o e-mail informado já estiver cadastrado.
     *
     * @author bruno.silva
     * @since 1.0
     * @created 29/04/2025
     */
    public Long cadastrarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        if (verificarEmailJaCadastrado(criarUsuarioDTO.email())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario(null, criarUsuarioDTO.nome(), criarUsuarioDTO.email(),
        		criarUsuarioDTO.senha(), Instant.now(), Instant.now(), criarUsuarioDTO.roles());

        usuarioRepositorio.save(usuario);

        return usuario.getId();
    }

    
    /**
     * Verifica se um e-mail já está cadastrado no sistema.
     *
     * <p>Consulta o repositório de usuários para verificar se existe um usuário
     * com o e-mail fornecido.</p>
     *
     * @param email O e-mail a ser verificado.
     * @return {@code true} se o e-mail já estiver cadastrado, {@code false} caso contrário.
     */
	private boolean verificarEmailJaCadastrado(String email) {
		return usuarioRepositorio.existsByEmail(email);
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
    
    /**
     * Deleta um usuário existente com base no ID fornecido.
     *
     * @param usuarioId o ID do usuário a ser deletado
     * @throws NumberFormatException se o usuário com o ID fornecido não for encontrado
     * @author bruno.silva
	 * @since 1.0
	 * @created 02/05/2025
     */
    public void deletarUsuario(long usuarioId) {
        Usuario usuarioConsultado = usuarioRepositorio.findById(usuarioId).orElse(null);

        if (usuarioConsultado == null) {
            throw new NumberFormatException("Usuário não encontrado.");
        }

        usuarioRepositorio.delete(usuarioConsultado);
    }
    
    /**
     * Verifica as credenciais do usuário para realizar o login.
     *
     * <p>Este método consulta o repositório de usuários buscando pelo e-mail informado.
     * Se o usuário for encontrado e a senha coincidir, retorna {@code true}. 
     * Caso contrário, lança uma {@link IllegalArgumentException} informando que
     * o e-mail ou senha são inválidos.</p>
     *
     * @param loginDTO Objeto contendo o e-mail e a senha do usuário.
     * @return {@code true} se as credenciais forem válidas.
     * @throws IllegalArgumentException se o e-mail não existir ou a senha estiver incorreta.
     */
    public boolean getLogin(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepositorio.findByEmail(loginDTO.getEmail());

        if (usuario == null || !usuario.getSenha().equals(loginDTO.getSenha())) {
            throw new IllegalArgumentException("E-mail ou senha inválidos.");
        }
        return true;
    }

}
