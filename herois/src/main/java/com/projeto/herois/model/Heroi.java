package com.projeto.herois.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @Entity diz ao Spring/JPA: "essa classe representa uma tabela no banco de dados"
@Entity
// @Table define o nome da tabela no banco. Se nao colocar, o JPA usa o nome da classe.
@Table(name = "herois")
public class Heroi {

    // @Id marca esse campo como chave primaria (PK) da tabela
    @Id
    // @GeneratedValue diz que o proprio banco gera o valor do ID (1, 2, 3, 4...)
    // IDENTITY = usa o auto-incremento nativo do banco (como o AUTO_INCREMENT do MySQL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    //Info do SuperHeroi
    private Long apiId; 
    private String nome;
    private String imagemUrl;
    private String publisher;
    private String alinhamento;

    // ------ Powerstats: todos vao de 0 a 100, conforme a API ------
    private int inteligencia;
    private int forca;
    private int velocidade;
    private int durabilidade;
    private int poder;
    private int combate;

    public Heroi() {
    }

    public Heroi(Long apiId, String nome, String imagemUrl, String publisher, String alinhamento,
                 int inteligencia, int forca, int velocidade, int durabilidade, int poder, int combate) {
        this.apiId = apiId;
        this.nome = nome;
        this.imagemUrl = imagemUrl;
        this.publisher = publisher;
        this.alinhamento = alinhamento;
        this.inteligencia = inteligencia;
        this.forca = forca;
        this.velocidade = velocidade;
        this.durabilidade = durabilidade;
        this.poder = poder;
        this.combate = combate;
    }

    // ------ Getters e Setters ------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlinhamento() {
        return alinhamento;
    }

    public void setAlinhamento(String alinhamento) {
        this.alinhamento = alinhamento;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getCombate() {
        return combate;
    }

    public void setCombate(int combate) {
        this.combate = combate;
    }
}