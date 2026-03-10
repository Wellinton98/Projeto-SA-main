package com.locacao.dto;

public record ClienteRequestDTO(
    Integer id,
    String nome,
    String cpf,
    String email,
    String telefone,
    String endereco
) {}