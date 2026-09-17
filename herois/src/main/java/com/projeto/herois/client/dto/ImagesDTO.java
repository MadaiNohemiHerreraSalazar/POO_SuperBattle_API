package com.projeto.herois.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Representa o objeto "images" do JSON, que ja vem com o link da foto em 4 tamanhos prontos:
// "images": { "xs": "...", "sm": "...", "md": "...", "lg": "..." }
//
// Vamos usar principalmente o "md" (tamanho medio) pra exibir nos cards.
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesDTO {

    private String xs; // extra small
    private String sm; // small
    private String md; // medium (o que vamos usar nos cards)
    private String lg; // large

    public String getXs() {
        return xs;
    }

    public void setXs(String xs) {
        this.xs = xs;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }
}