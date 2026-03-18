package com.locacao.integracao;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.locacao.dto.ImovelRequestDTO;
import com.locacao.dto.ImovelResponseDTO;
import com.locacao.service.ImovelService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class ImovelServiceIntegrationTest {

    @Autowired
    private ImovelService imovelService;

@Test
@DisplayName("Deve salvar e buscar imóvel por id com sucesso")
public void deveSalvarEBuscar() {

    ImovelRequestDTO dto = new ImovelRequestDTO(
        "Rua Teste",
        "Casa",
        (short) 3,
        (short) 2,
        (short) 1,
        true,
        true,
        "Casa",
        new BigDecimal("1500.00"),
        "foto.jpg",
        "Aluguel"
    );

    ImovelResponseDTO salvo = imovelService.salvar(dto);
    ImovelResponseDTO encontrado = imovelService.buscarPorId(salvo.id());

    assertNotNull(encontrado);
    assertEquals("Rua Teste", encontrado.endereco());
    assertEquals("Casa", encontrado.tipo());
}
}