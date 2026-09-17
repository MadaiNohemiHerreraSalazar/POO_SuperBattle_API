package com.projeto.herois.repository;

import com.projeto.herois.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface responsavel por toda a comunicacao com o banco de dados para a entidade Heroi.
// JpaRepository<Heroi, Long> significa: "entidade Heroi, cujo ID e do tipo Long"

// A interface traz:
// - save(heroi)          -> insere ou atualiza um heroi no banco
// - findAll()             -> retorna todos os herois salvos
// - findById(id)          -> busca um heroi especifico pelo ID
// - deleteById(id)        -> remove um heroi pelo ID
// - count()               -> conta quantos herois existem

public interface HeroiRepository extends JpaRepository<Heroi, Long> {
}