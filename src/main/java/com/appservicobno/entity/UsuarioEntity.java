package com.appservicobno.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "tb_usuarios")
public record UsuarioEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id,

    @Column(name = "nome", length = 100, nullable = false)
    String nome,

    @Column(name = "email", length = 50, nullable = false)
    String email,

    @Column(name = "senha", length = 255, nullable = false)
    String senha,

    @CreationTimestamp
    Instant dataHoraCriação,

    @UpdateTimestamp
    Instant dataHoraAtualização
) {
}
