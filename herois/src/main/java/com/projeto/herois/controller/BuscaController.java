package com.projeto.herois.controller;

import com.projeto.herois.client.HeroApiClient;
import com.projeto.herois.client.dto.HeroApiDTO;
import com.projeto.herois.model.Heroi;
import com.projeto.herois.repository.HeroiRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @Controller: essa classe responde por URLs e devolve PAGINAS HTML (Thymeleaf).
@Controller
@RequestMapping("/herois")
public class BuscaController {

    private final HeroApiClient heroApiClient; // pra buscar dados na API externa
    private final HeroiRepository heroiRepository; //  pra salvar o heroi escolhido no nosso banco

    public BuscaController(HeroApiClient heroApiClient, HeroiRepository heroiRepository) {
        this.heroApiClient = heroApiClient;
        this.heroiRepository = heroiRepository;
    }

    // Responde a URL: GET /herois/buscar?nome=batman
    // @RequestParam(required = false) permite acessar a pagina sem digitar nada ainda
    // (ex: na primeira vez que o usuario abre a tela de busca)
    @GetMapping("/buscar")
    public String buscar(@RequestParam(required = false) String nome, Model model) {
        List<HeroApiDTO> resultados = new ArrayList<>();

        if (nome != null && !nome.isBlank()) {
            resultados = heroApiClient.buscarPorNome(nome);
        }

        // model.addAttribute manda esses dados para o HTML
        model.addAttribute("resultados", resultados);
        model.addAttribute("nome", nome); 

        return "busca";
    }

    // Responde a URL: POST /herois/salvar
    // Recebe cada campo do heroi separadamente (eles vem dos <input type="hidden"> do busca.html)
    @PostMapping("/salvar")
    public String salvar(@RequestParam Long apiId,
                          @RequestParam String nome,
                          @RequestParam String imagemUrl,
                          @RequestParam String publisher,
                          @RequestParam String alinhamento,
                          @RequestParam int inteligencia,
                          @RequestParam int forca,
                          @RequestParam int velocidade,
                          @RequestParam int durabilidade,
                          @RequestParam int poder,
                          @RequestParam int combate) {

      
        Heroi heroi = new Heroi(apiId, nome, imagemUrl, publisher, alinhamento,
                inteligencia, forca, velocidade, durabilidade, poder, combate);

        heroiRepository.save(heroi);

        return "redirect:/herois";
    }
}