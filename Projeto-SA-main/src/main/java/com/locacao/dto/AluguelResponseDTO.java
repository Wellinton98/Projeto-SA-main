package com.locacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AluguelResponseDTO(
        Integer idAluguel,
        String nomeCliente,
        String enderecoImovel,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorMensal,
        Boolean seguroIncendio,
        BigDecimal valorSeguroIncendio,
        String nomeFiador,
        String cpfFiador
) {}