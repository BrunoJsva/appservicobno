package com.appservicobno.appservicobno.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuração de segurança da aplicação.
 * <p>
 * Nesta configuração, todos os endpoints são liberados (permitAll),
 * CSRF está desabilitado e o gerenciamento de sessão é stateless.
 * </p>
 * 
 * @since 1.0
 * @author bruno.silva
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Define a cadeia de filtros de segurança (SecurityFilterChain).
     * <p>
     * - Desabilita CSRF.<br>
     * - Define a política de sessão como {@code STATELESS}.<br>
     * - Libera acesso a todos os endpoints via {@code permitAll()}.
     * </p>
     * 
     * @param http o builder para configuração de requisições HTTP
     * @return o objeto {@link SecurityFilterChain} configurado
     * @throws Exception caso ocorra erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers(HttpMethod.POST, "/v1/usuarios").hasRole("ADMIN")
                		.requestMatchers(HttpMethod.DELETE, "/v1/usuarios/**").hasRole("ADMIN")
                		.anyRequest().permitAll()
                                //.requestMatchers("/public/**").permitAll()  // <- LIBERA TODOS OS ENDPOINTS
                               // .anyRequest().permitAll() // <- LIBERA TODOS OS ENDPOINTS
                )
                .httpBasic(withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
	public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	}
}
