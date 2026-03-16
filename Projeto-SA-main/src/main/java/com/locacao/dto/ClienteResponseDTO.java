package com.locacao.dto;

public record ClienteResponseDTO(
        Integer idCliente,
        String nome,
        String cpf,
        String endereco,
        String email,
        String telefone
) {}