package com.example.trabalho_samambaia.model;

public class Planta {
    private String nome;
    private String nome_cientifico;
    private String imagem_url;

    private String quantidade_agua;

    public Planta(String nome, String nome_cientifico, String imagem_url) {
        this.nome = nome;
        this.nome_cientifico = nome_cientifico;
        this.imagem_url = imagem_url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome_cientifico() {
        return nome_cientifico;
    }

    public void setNome_cientifico(String nome_cientifico) {
        this.nome_cientifico = nome_cientifico;
    }

    public String getImagem_url() {
        return imagem_url;
    }

    public void setImagem_url(String imagem_url) {
        this.imagem_url = imagem_url;
    }

    public String getQuantidade_agua() {
        return quantidade_agua;
    }

    public void setQuantidade_agua(String quantidade_agua) {
        this.quantidade_agua = quantidade_agua;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "nome='" + nome + '\'' +
                ", nome_cientifico='" + nome_cientifico + '\'' +
                ", imagem_url='" + imagem_url + '\'' +
                '}';
    }
}
