package com.locacao.unitario;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.dto.ImovelResponseDTO;
import com.locacao.model.Imovel;
import com.locacao.repository.ImovelRepository;
import com.locacao.service.ImovelService;

public class Imovelservicetest {

    @Mock
    private ImovelRepository imovelRepository;

    @InjectMocks
    private ImovelService imovelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Buscar por ID e retornar corretamente")
    void testBuscarPorIdRetornoCorreto() {

        // Arrange
        Imovel imovel = new Imovel();
        imovel.setIdImovel(1);
        imovel.setEndereco("Rua A");

        when(imovelRepository.findById(1)).thenReturn(Optional.of(imovel));

        // Act
        ImovelResponseDTO resultado = imovelService.buscarPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.id());
        assertEquals("Rua A", resultado.endereco());
}

    @Test
    @DisplayName("Buscar por ID inexistente deve lançar exceção")
    void testBuscarPorIdInexistenteDeveLancarExcecao() {

        // Arrange
        when(imovelRepository.findById(99)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(ResponseStatusException.class, () -> {
            imovelService.buscarPorId(99);
        });
    }
}




