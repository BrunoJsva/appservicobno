package com.appservicobno.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do OpenAPI para a documentação da API.
 * 
 * Esta classe configura os detalhes da documentação da API, incluindo informações
 * sobre os servidores disponíveis e metadados da API, como título, versão e descrição.
 * @author bruno.silva
 * @version 1.0
 * @since 2024-06-15
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura o OpenAPI com informações personalizadas sobre a API.
     * 
     * @return uma instância de {@link OpenAPI} contendo as configurações da API.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .addServersItem(new Server().url("http://localhost:8080").description("Servidor Local"))
            .info(new Info()
                .title("API de Serviço BNO")
                .version("v1.0")
                .description("Esta API permite a criação, atualização, exclusão e consulta de dados.")
                .termsOfService("http://swagger.io/terms/")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://springdoc.org")));
    }
}