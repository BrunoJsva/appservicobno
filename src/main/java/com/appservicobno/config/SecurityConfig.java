package com.appservicobno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

/**
 * Configura as políticas de segurança para a aplicação.
 * <p>
 * A política principal desta configuração é:
 * <ul>
 * <li><b>Restringir o acesso ao Swagger UI e à documentação OpenAPI</b> para requisições
 * originadas apenas de localhost.</li>
 * <li><b>Permitir acesso público e irrestrito</b> a todos os outros endpoints da API.</li>
 * </ul>
 * A API é configurada para ser stateless, não utilizando sessões HTTP.
 *
 * @author bruno.silva
 * @version 2.0
 * @since 2025-08-29
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define a cadeia de filtros de segurança que processa as requisições HTTP.
     * <p>
     * A mudança crucial aqui é a regra final `.anyRequest().permitAll()`, que torna
     * a API pública por padrão, com exceção da regra específica para o Swagger.
     *
     * @param http O objeto {@link HttpSecurity} para configurar a segurança web.
     * @return um objeto {@link SecurityFilterChain} que o Spring Security usará.
     * @throws Exception se ocorrer um erro durante a configuração.
     * @author bruno.silva
     * @since 2.0
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).access(new WebExpressionAuthorizationManager("hasIpAddress('127.0.0.1') or hasIpAddress('::1')"))
                .anyRequest().permitAll()
            );
        return http.build();
    }
}