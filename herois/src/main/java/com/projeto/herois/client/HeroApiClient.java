package com.projeto.herois.client;

import com.projeto.herois.client.dto.HeroApiDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// @Component diz ao Spring: "gerencie essa classe pra mim" (cria uma unica instancia dela,
// e permite que outras classes, como o BuscaController, "peçam" ela pronta no construtor)
@Component
public class HeroApiClient {

    // URL fixa que retorna TODOS os herois da API de uma vez so, em um unico JSON gigante.
    // A API nao tem um endpoint de busca por nome, entao filtramos no nosso proprio codigo.
    private static final String URL_TODOS =
            "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/all.json";

    // O RestTemplate e "injetado" (entregue automaticamente) pelo Spring aqui no construtor,
    // gracas ao @Bean que criamos na classe AppConfig.
    private final RestTemplate restTemplate;

    public HeroApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Faz a chamada HTTP GET para a URL_TODOS e converte a resposta (um array JSON)
    // automaticamente em um array de objetos HeroApiDTO.
    public List<HeroApiDTO> buscarTodos() {
        HeroApiDTO[] herois = restTemplate.getForObject(URL_TODOS, HeroApiDTO[].class);

        // Se a API nao responder nada (null), retornamos uma lista vazia
        // em vez de deixar a aplicacao quebrar com NullPointerException
        return herois != null ? Arrays.asList(herois) : List.of();
    }

    // Filtra a lista completa de herois pelo nome digitado pelo usuario.
    public List<HeroApiDTO> buscarPorNome(String nome) {
        // Deixamos tudo em minusculo pra a busca nao diferenciar maiusculas de minusculas
        // (assim "batman", "Batman" e "BATMAN" retornam o mesmo resultado)
        String termo = nome.toLowerCase();

        return buscarTodos().stream()
                // Mantem na lista somente os herois cujo nome CONTENHA o termo buscado
                .filter(h -> h.getName() != null && h.getName().toLowerCase().contains(termo))
                // Transforma o resultado do stream de volta em uma List
                .collect(Collectors.toList());
    }
}