package com.appservicobno.appservicobno.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appservicobno.appservicobno.dto.CriarUsuarioDTO;
import com.appservicobno.appservicobno.dto.UsuarioDTO;
import com.appservicobno.appservicobno.service.UsuarioService;

/**
 * Controller responsável pelos endpoints relacionados a usuários.
 * @author bruno.silva
 * @created 29/04/2025
 */
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
     * @author bruno.silva
     * @created 29/04/2025
     */
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
}
