package com.locacao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idImovel;

    private String endereco;
    private String tipo;
    private Short quartos;
    private Short banheiros;
    private Short vagas;
    private Boolean mobilia;
    private Boolean disponivel;
    private String descricao;
    private String valorAluguel;

    private String foto; // ✅ AGORA SIM existe o campo

    @Override
    public String toString() {
        return "Imovel [idImovel=" + idImovel + ", endereco=" + endereco + ", tipo=" + tipo + ", quartos=" + quartos
                + ", banheiros=" + banheiros + ", vagas=" + vagas + ", mobilia=" + mobilia + ", disponivel="
                + disponivel + ", descricao=" + descricao + ", valorAluguel=" + valorAluguel + ", foto=" + foto + "]";
    }

}