package com.locacao.dto;

import java.math.BigDecimal;

public record AluguelRequestDTO(
    Integer idAluguel,
    Long idCliente,
    Long idImovel,
    String dataInicio,
    BigDecimal valorMensal,
    Boolean seguroIncendio,
    String contratoAluguel,
    String nomeFiado,
    String cpfFiador,
    BigDecimal valorSeguroIncendio,
    String dataFim

) {}