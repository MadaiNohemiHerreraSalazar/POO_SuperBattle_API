package com.projeto.herois.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Essa e a classe pricipal: representa um heroi INTEIRO, exatamente como ele
// vem da Superhero API. Ela "contem" as outras 3 classes DTO que criamos.

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeroApiDTO {

    private Long id;              
    private String name;           
    private PowerStatsDTO powerstats;   
    private BiographyDTO biography;     
    private ImagesDTO images;           

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PowerStatsDTO getPowerstats() {
        return powerstats;
    }

    public void setPowerstats(PowerStatsDTO powerstats) {
        this.powerstats = powerstats;
    }

    public BiographyDTO getBiography() {
        return biography;
    }

    public void setBiography(BiographyDTO biography) {
        this.biography = biography;
    }

    public ImagesDTO getImages() {
        return images;
    }

    public void setImages(ImagesDTO images) {
        this.images = images;
    }
}