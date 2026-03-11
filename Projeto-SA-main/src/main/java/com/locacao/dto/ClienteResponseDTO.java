package com.locacao.dto;

public record ClienteResponseDTO(
    Integer id,
    String nome,
    String cpf,
    String email,
    String telefone,
    String endereco
) {}