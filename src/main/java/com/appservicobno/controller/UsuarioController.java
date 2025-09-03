package com.appservicobno.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appservicobno.dto.CriarUsuarioDTO;
import com.appservicobno.dto.LoginDTO;
import com.appservicobno.dto.UsuarioDTO;
import com.appservicobno.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller responsável pelos endpoints relacionados a usuários.
 * @author bruno.silva
 * @created 29/04/2025
 */
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {
    
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /**
     * Cadastra um novo usuário no sistema.
     * 
     * @param criarUsuarioDTO dados para criação do usuário
     * @return 201 Created com URI do recurso criado
     * @author bruno.silva
     * @created 29/04/2025
     */
    @Operation(summary = "Cadastra um novo usuário", description = "Cria um novo usuário no sistema com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody CriarUsuarioDTO criarUsuarioDTO) {
        Long usuarioId = service.cadastrarUsuario(criarUsuarioDTO);
        String mensagem = "Usuário criado com sucesso. ID: " + usuarioId;
        return ResponseEntity
                .status(201)
                .body(mensagem);
    }

    /**
     * Consulta um usuário pelo ID.
     * 
     * @param usuarioId ID do usuário
     * @return 200 OK com os dados do usuário, ou 400 Bad Request se inválido
     * @author bruno.silva
     * @created 29/04/2025
     */
    @Operation(summary = "Consulta usuário pelo ID", description = "Retorna os dados do usuário correspondente ao ID fornecido.")
    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> consultarPeloId(@PathVariable long usuarioId) {
        try {
            UsuarioDTO usuarioConsultado = service.consultarUsuarioPorId(usuarioId);
            return ResponseEntity.ok(usuarioConsultado);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                .body("Não foi possível consultar o usuário, verifique o ID informado.");
        }
    }
    
    /**
     * Endpoint responsável por deletar um usuário com base no ID informado.
     *
     * @param usuarioId ID do usuário que será deletado.
     * @return {@code 200 OK} se o usuário for deletado com sucesso,
     *         ou {@code 400 Bad Request} caso o ID seja inválido ou o usuário não seja encontrado.
     * @created 29/04/2025
     */
    @Operation(summary = "Deleta usuário pelo ID", description = "Remove o usuário correspondente ao ID fornecido do sistema.")
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deletarUsuario(@PathVariable long usuarioId) {
        try {
            service.deletarUsuario(usuarioId);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                .body("Não foi possível deletar o usuário, verifique o ID informado.");
        }
    }
    
    /**
     * Endpoint responsável por autenticar um usuário com base nas credenciais fornecidas.
     *
     * <p>Este método recebe um objeto {@link LoginDTO} contendo o e-mail e a senha do usuário.
     * Ele delega a verificação para o {@code UsuarioService}. Se as credenciais forem válidas,
     * retorna uma resposta 200 OK com valor {@code true}. Caso contrário, retorna um erro 400
     * com uma mensagem indicando falha na autenticação.</p>
     *
     * @param loginDTO Objeto contendo as credenciais de login do usuário (e-mail e senha).
     * @return {@code ResponseEntity} com o resultado da autenticação.
     * @created 29/04/2025
     */
    @Operation(summary = "Autentica usuário", description = "Realiza a autenticação do usuário com base nas credenciais fornecidas.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
    	
    	try {
    		boolean usuarioValido = service.getLogin(loginDTO);
            return ResponseEntity.ok(usuarioValido);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest()
                .body("Não foi possível realizar o login, verifique as credenciais.");
        }
    }
}
