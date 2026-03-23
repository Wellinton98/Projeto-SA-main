package com.locacao.dto;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;

public record ImovelRequestDTO(
    @NotNull
    String endereco,

    @NotNull
    String tipo,

    @NotNull
    Short quartos,

    @NotNull
    Short banheiros,

    @NotNull
    Short vagas,

    @NotNull
    Boolean mobilia,

    @NotNull
    Boolean disponivel,

    @NotNull
    String descricao,

    @NotNull
    @Positive
    BigDecimal valorAluguel,
    
    String foto,
    String negocio

) {}