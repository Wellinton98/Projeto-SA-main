package com.locacao.dto;

public record ClienteRequesteDTO(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    String endereco
) {}