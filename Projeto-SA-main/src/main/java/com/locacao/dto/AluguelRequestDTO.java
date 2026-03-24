package com.locacao.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

   public record AluguelRequestDTO(

    @NotNull
    Integer idCliente,

    @NotNull
    Integer idImovel,

    @NotNull
    LocalDate dataInicio,

    @NotNull
    LocalDate dataFim,

    @NotNull
    @Positive
    BigDecimal valorMensal,

    @NotNull
    Boolean seguroIncendio,

    String contratoAluguel,

    @Size(max = 100)
    String nomeFiador,

    @Pattern(regexp = "\\d{11}")
    String cpfFiador,

    @Positive
    BigDecimal valorSeguroIncendio

) {}