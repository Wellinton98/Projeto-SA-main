package com.locacao.dto;

public record ClienteRequestDTO(
    Integer id,

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = " Cdf é  obriogatorio")
    String cpf,

    @NotBlank(message = "email é obrigatorio")
    String email,

    String telefone,
    String endereco
    
) {}