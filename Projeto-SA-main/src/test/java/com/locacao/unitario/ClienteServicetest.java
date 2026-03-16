package com.locacao.unitario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.model.Aluguel;
import com.locacao.repository.AluguelRepository;
import com.locacao.service.AluguelService;

@ExtendWith(MockitoExtension.class)
public class ClienteServicetest {

    @Mock
    private AluguelRepository aluguelRepository;

    @InjectMocks
    private AluguelService aluguelService;

    @Test
    @DisplayName("Deve salvar um aluguel com sucesso")
    void testSalvarAluguel() {
        // Arrange
        AluguelRequestDTO dto = new AluguelRequestDTO(
                1, 1,
                LocalDate.now(),
                LocalDate.now().plusMonths(1),
                new BigDecimal("1200.00"),
                true,
                "Contrato teste",
                "João Fiador",
                "123.456.789-00",
                new BigDecimal("100.00")
        );

        Aluguel aluguelSalvo = new Aluguel();
        aluguelSalvo.setIdAluguel(1);
        aluguelSalvo.setDataInicio(dto.dataInicio());
        aluguelSalvo.setDataFim(dto.dataFim());
        aluguelSalvo.setValorMensal(dto.valorMensal());

        when(aluguelRepository.save(any(Aluguel.class))).thenReturn(aluguelSalvo);

        // Act
        var resultado = aluguelService.salvar(dto);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.idAluguel());
        assertEquals(dto.dataInicio(), resultado.dataInicio());
        assertEquals(dto.valorMensal(), resultado.valorMensal());

        verify(aluguelRepository, times(1)).save(any(Aluguel.class));
    }

    @Test
    @DisplayName("Deve listar todos os aluguéis")
    void testListarTodos() {
        // Arrange
        Aluguel aluguel1 = new Aluguel();
        aluguel1.setIdAluguel(1);
        Aluguel aluguel2 = new Aluguel();
        aluguel2.setIdAluguel(2);

        when(aluguelRepository.findAll()).thenReturn(List.of(aluguel1, aluguel2));

        // Act
        var lista = aluguelService.listarTodos();

        // Assert
        assertNotNull(lista);
        assertEquals(2, lista.size());
        verify(aluguelRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve verificar se o imóvel está disponível")
    void testImovelDisponivel() {
        // Arrange
        when(aluguelRepository.verificarDisponibilidade(
                anyInt(),
                any(LocalDate.class),
                any(LocalDate.class)
        )).thenReturn(List.of()); // sem aluguéis conflitantes

        // Act
        boolean disponivel = aluguelService.imovelDisponivel(1, LocalDate.now(), LocalDate.now().plusDays(5));

        // Assert
        assertTrue(disponivel);
        verify(aluguelRepository).verificarDisponibilidade(anyInt(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    @DisplayName("Deve retornar falso quando o imóvel não estiver disponível")
    void testImovelIndisponivel() {
        // Arrange
        when(aluguelRepository.verificarDisponibilidade(
                anyInt(),
                any(LocalDate.class),
                any(LocalDate.class)
        )).thenReturn(List.of(new Aluguel())); // já existe aluguel nesse período

        // Act
        boolean disponivel = aluguelService.imovelDisponivel(1, LocalDate.now(), LocalDate.now().plusDays(5));

        // Assert
        assertFalse(disponivel);
        verify(aluguelRepository).verificarDisponibilidade(anyInt(), any(LocalDate.class), any(LocalDate.class));
    }
}