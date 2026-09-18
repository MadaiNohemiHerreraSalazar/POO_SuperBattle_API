package com.projeto.herois.controller;

import com.projeto.herois.model.Heroi;
import com.projeto.herois.repository.HeroiRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// @Controller = essa classe responde por URLs e devolve PAGINAS HTML (Thymeleaf).
@Controller
// Todas as rotas daqui comecam com "/herois"
@RequestMapping("/herois")
public class HeroiController {

    // Unica dependencia: o repositorio, que conversa com o banco de dados.
    // Nao precisamos do HeroApiClient aqui porque essa tela so mexe com o que ja foi salvo.
    private final HeroiRepository heroiRepository;

    // O Spring entrega o repositorio pronto aqui no construtor (injecao de dependencia)
    public HeroiController(HeroiRepository heroiRepository) {
        this.heroiRepository = heroiRepository;
    }

    // ======================================================================
    // LISTAGEM - GET /herois
    // E pra ca que o BuscaController redireciona depois de salvar um heroi.
    // ======================================================================
    @GetMapping
    public String listar(Model model) {
        // findAll() ja devolve todos os registros da tabela "herois"
        model.addAttribute("herois", heroiRepository.findAll());
        return "lista"; // renderiza templates/lista.html
    }

    // ======================================================================
    // EDICAO - GET /herois/editar/5
    // @PathVariable pega o numero que veio na propria URL e joga na variavel "id"
    // ======================================================================
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        // findById devolve um Optional (pode ou nao existir aquele id no banco).
        // orElse(null) simplifica: se nao achar, vira null.
        Heroi heroi = heroiRepository.findById(id).orElse(null);

        // Se alguem digitar um id que nao existe, mandamos de volta pra listagem
        // em vez de deixar a pagina quebrar tentando ler campos de um objeto nulo.
        if (heroi == null) {
            return "redirect:/herois";
        }

        // O editar.html usa th:object="${heroi}", entao o nome do atributo
        // precisa ser exatamente "heroi"
        model.addAttribute("heroi", heroi);
        return "editar";
    }

    // ======================================================================
    // ATUALIZACAO - POST /herois/atualizar
    // @ModelAttribute monta um objeto Heroi juntando automaticamente os campos
    // do formulario que tem o mesmo nome dos atributos da classe (nome, forca, etc).
    // Como o id vem junto (no input hidden), o save() faz UPDATE em vez de INSERT.
    // ======================================================================
    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute Heroi heroi) {
        heroiRepository.save(heroi);
        return "redirect:/herois";
    }

    // ======================================================================
    // EXCLUSAO - POST /herois/deletar/5
    // E POST (e nao GET) porque a acao muda dados no banco.
    // ======================================================================
    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        heroiRepository.deleteById(id);
        return "redirect:/herois";
    }

    // ======================================================================
    // DUELO - GET /herois/luta  (e tambem /herois/luta?id1=1&id2=2)
    // Os ids sao required=false porque a primeira vez que a pessoa abre a tela
    // ela ainda nao escolheu ninguem: aparece so o formulario de selecao.
    // ======================================================================
    @GetMapping("/luta")
    public String luta(@RequestParam(required = false) Long id1,
                       @RequestParam(required = false) Long id2,
                       Model model) {

        // A lista completa sempre vai pro HTML, porque e ela que preenche os <select>
        model.addAttribute("herois", heroiRepository.findAll());

        // Sem os dois escolhidos, mostramos so o formulario e paramos por aqui
        if (id1 == null || id2 == null) {
            return "luta";
        }

        Heroi heroi1 = heroiRepository.findById(id1).orElse(null);
        Heroi heroi2 = heroiRepository.findById(id2).orElse(null);

        if (heroi1 == null || heroi2 == null) {
            return "luta";
        }

        model.addAttribute("heroi1", heroi1);
        model.addAttribute("heroi2", heroi2);

        // Regra do duelo: vence quem ganhar mais atributos (melhor de 6).
        model.addAttribute("vencedor", decidirVencedor(heroi1, heroi2));

        return "luta";
    }

    // ----------------------------------------------------------------------
    // Metodo auxiliar (private, so essa classe usa).
    // Compara os seis atributos e devolve quem ganhou mais deles.
    // Devolve null em caso de empate, e o luta.html trata isso mostrando "Empate!".
    // ----------------------------------------------------------------------
    private Heroi decidirVencedor(Heroi a, Heroi b) {
        int pontosA = 0;
        int pontosB = 0;

        // Cada par de valores e um "round". Empate no atributo nao da ponto pra ninguem.
        // A List.of guarda os valores na mesma ordem nos dois lados.
        List<Integer> statsA = List.of(a.getInteligencia(), a.getForca(), a.getVelocidade(),
                a.getDurabilidade(), a.getPoder(), a.getCombate());
        List<Integer> statsB = List.of(b.getInteligencia(), b.getForca(), b.getVelocidade(),
                b.getDurabilidade(), b.getPoder(), b.getCombate());

        for (int i = 0; i < statsA.size(); i++) {
            if (statsA.get(i) > statsB.get(i)) {
                pontosA++;
            } else if (statsB.get(i) > statsA.get(i)) {
                pontosB++;
            }
        }

        if (pontosA > pontosB) {
            return a;
        }
        if (pontosB > pontosA) {
            return b;
        }
        return null; // empate
    }
}