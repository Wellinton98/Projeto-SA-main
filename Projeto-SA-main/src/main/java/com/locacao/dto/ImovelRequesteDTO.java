package com.locacao.dto;

public record ImovelRequesteDTO(
    Long id,
    String endereco,
    String tipo,
    Short quartos,
    Short banheiros,
    Boolean mobilia,
    Boolean disponivel,
    String descricao,
    Double valorAluguel
) {}