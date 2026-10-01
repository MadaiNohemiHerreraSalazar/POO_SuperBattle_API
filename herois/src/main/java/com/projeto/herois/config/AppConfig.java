package com.projeto.herois.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// @Configuration: Classe que define objetos (Beans) que podem ser injetados em outras classes do projeto
@Configuration
public class AppConfig {

    // RestTemplate: Ferramenta padrao do Spring pra fazer chamadas HTTP (GET, POST, etc.) para APIs externas.
    //
    // @Bean : Quando alguem precisar de um RestTemplate, use ESTE metodo pra criar ele
    // Assim, no HeroApiClient, a gente so precisa "pedir" um RestTemplate no construtor
    // que o Spring entrega automaticamente (Injecao de Dependencia).
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}