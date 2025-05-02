package com.appservicobno.appservicobno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appservicobno.appservicobno.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	boolean existsByEmail(String email);

}
