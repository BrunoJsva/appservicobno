package com.appservicobno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appservicobno.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity findByEmail(String email);
	boolean existsByEmail(String email);

}
