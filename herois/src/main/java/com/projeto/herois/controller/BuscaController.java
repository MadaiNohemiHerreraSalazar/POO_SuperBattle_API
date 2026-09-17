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

// @Controller diz ao Spring que essa classe vai responder por rotas (URLs) do site
// e retornar PAGINAS HTML (via Thymeleaf), diferente de @RestController que retorna JSON puro.
@Controller
// Todas as rotas dessa classe comecam com "/herois"
@RequestMapping("/herois")
public class BuscaController {

    // Dependencias que essa classe precisa pra funcionar:
    // - heroApiClient: pra buscar dados na API externa
    // - heroiRepository: pra salvar o heroi escolhido no nosso banco
    private final HeroApiClient heroApiClient;
    private final HeroiRepository heroiRepository;

    // O Spring "injeta" essas duas dependencias automaticamente aqui no construtor
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

        // So faz a busca na API se o usuario realmente digitou algo
        if (nome != null && !nome.isBlank()) {
            resultados = heroApiClient.buscarPorNome(nome);
        }

        // model.addAttribute manda esses dados para o HTML (busca.html) poder usar
        model.addAttribute("resultados", resultados);
        model.addAttribute("nome", nome); // pra manter o texto digitado no campo apos buscar

        // Retorna o nome do arquivo HTML (sem ".html") que vai ser renderizado
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

        // Monta um objeto Heroi (Entity) com os dados recebidos do formulario
        Heroi heroi = new Heroi(apiId, nome, imagemUrl, publisher, alinhamento,
                inteligencia, forca, velocidade, durabilidade, poder, combate);

        // Salva no banco de dados (o JPA cuida do INSERT automaticamente)
        heroiRepository.save(heroi);

        // Depois de salvar, redireciona o usuario pra tela de listagem (do Pessoa B)
        return "redirect:/herois";
    }
}