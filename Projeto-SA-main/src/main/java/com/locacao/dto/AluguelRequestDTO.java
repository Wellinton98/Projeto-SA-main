package com.locacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AluguelRequestDTO(
        Integer idCliente,
        Integer idImovel,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorMensal,
        Boolean seguroIncendio,
        String contratoAluguel,
        String nomeFiador,
        String cpfFiador,
        BigDecimal valorSeguroIncendio
) {}
