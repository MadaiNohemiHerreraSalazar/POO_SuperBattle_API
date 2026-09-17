package com.projeto.herois.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

// @Configuration diz ao Spring: "essa classe define objetos (Beans) que
// podem ser injetados em outras classes do projeto"
@Configuration
public class AppConfig {

    // RestTemplate e a ferramenta padrao do Spring pra fazer chamadas HTTP
    // (GET, POST, etc.) para APIs externas, como a Superhero API.
    //
    // @Bean diz: "quando alguem precisar de um RestTemplate, use ESTE metodo pra criar ele"
    // Assim, no HeroApiClient, a gente so precisa "pedir" um RestTemplate no construtor
    // que o Spring entrega automaticamente (isso se chama Injecao de Dependencia).
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}