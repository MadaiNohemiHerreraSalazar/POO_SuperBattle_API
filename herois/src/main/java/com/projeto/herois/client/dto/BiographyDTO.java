package com.projeto.herois.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Representa o objeto "biography" do JSON da API:
// "biography": { "publisher": "DC Comics", "alignment": "good", ...}
//
// So mapeamos os campos que realmente vamos USAR na nossa aplicacao.
// Os demais campos do JSON (fullName, aliases, firstAppearance...) sao simplesmente ignorados
// @JsonIgnoreProperties(ignoreUnknown = true).

@JsonIgnoreProperties(ignoreUnknown = true)
public class BiographyDTO {

    private String publisher;
    private String alignment;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
}