package com.locacao.dto;

import java.math.BigDecimal;

public record ImovelRequestDTO(

    
    String endereco,
    String tipo,
    Short quartos,
    Short banheiros,
    Short vagas,
    Boolean mobilia,
    Boolean disponivel,
    String descricao,
    BigDecimal valorAluguel,
    String foto,
    String negocio

) {}