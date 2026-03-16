package com.locacao.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AluguelResponseDTO(

    Integer idAluguel,
    String clienteNome,
    String imovelEndereco,
    LocalDate dataInicio,
    LocalDate dataFim,
    BigDecimal valorMensal

) {}