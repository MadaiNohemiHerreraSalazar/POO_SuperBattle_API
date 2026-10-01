package com.projeto.herois.client;

import com.projeto.herois.client.dto.HeroApiDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// @Component: Cria uma unica instancia dela e permite que outras classes, como o BuscaController, pidan ela pronta no construtor.
@Component
public class HeroApiClient {

    // URL que retorna TODOS os herois da API de uma vez so, em um unico JSON gigante.
    private static final String URL_TODOS =
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/all.json";

    private final RestTemplate restTemplate;

    public HeroApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Faz a chamada HTTP GET para a URL_TODOS e converte a resposta (um array JSON) 
    // automaticamente em um array de objetos HeroApiDTO.
    public List<HeroApiDTO> buscarTodos() {
        HeroApiDTO[] herois = restTemplate.getForObject(URL_TODOS, HeroApiDTO[].class);

        return herois != null ? Arrays.asList(herois) : List.of();
    }


    public List<HeroApiDTO> buscarPorNome(String nome) {
        String termo = nome.toLowerCase();

        return buscarTodos().stream()
                // Mantem na lista somente os herois cujo nome CONTENHA o termo buscado
                .filter(h -> h.getName() != null && h.getName().toLowerCase().contains(termo))
                .collect(Collectors.toList());
    }
}