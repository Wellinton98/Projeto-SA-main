package com.locacao.model;

import java.math.BigDecimal;

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

    private BigDecimal valorAluguel;

    private String foto;

    private String negocio; // Alugar ou Comprar
}