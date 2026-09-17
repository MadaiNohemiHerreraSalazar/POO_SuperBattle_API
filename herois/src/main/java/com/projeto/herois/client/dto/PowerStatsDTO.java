package com.projeto.herois.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// DTO = "Data Transfer Object": uma classe que serve so pra RECEBER os dados
// que vem prontos da API externa, no formato JSON.
//
// Essa classe representa o objeto "powerstats" dentro do JSON de cada heroi. Exemplo real:
// "powerstats": { "intelligence": 100, "strength": 26, "speed": 27, "durability": 50, "power": 47, "combat": 100 }

// @JsonIgnoreProperties(ignoreUnknown = true) evita erro caso a API mande algum campo
// extra que a gente nao mapeou aqui (o Jackson so ignora, em vez de quebrar a aplicacao)

@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerStatsDTO {

    private int intelligence;
    private int strength;
    private int speed;
    private int durability;
    private int power;
    private int combat;

    // Getters e setters
    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }
}