package com.locacao.dto;
package com.locacao.dto;

import jakarta.validation.constraints.*;

public record ClienteRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 150)
        String nome,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
        String cpf,

        @NotBlank(message = "Endereço é obrigatório")
        @Size(max = 250)
        String endereco,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        @Size(max = 150)
        String email,

        @Size(max = 20)
        String telefone
) {}