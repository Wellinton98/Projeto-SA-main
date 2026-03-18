package com.locacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.processing.Pattern;

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

    @Size
    String nomeFiador,

    @Pattern
    String cpfFiador,

    @Positive
    BigDecimal valorSeguroIncendio

) {}