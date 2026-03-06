package com.locacao.dto;

public record ClienteRequestDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    String endereco
) {}