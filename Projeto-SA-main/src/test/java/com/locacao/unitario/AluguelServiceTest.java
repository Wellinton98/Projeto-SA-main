package com.locacao.unitario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.locacao.dto.AluguelResponseDTO;
import com.locacao.model.Aluguel;
import com.locacao.repository.AluguelRepository;
import com.locacao.service.AluguelService;

@ExtendWith(MockitoExtension.class)
public class AluguelServiceTest {

    @Mock
    private AluguelRepository aluguelRepository;

    @InjectMocks
    private AluguelService aluguelService;

   /*@Test
    @DisplayName("Deve salvar um aluguel com sucesso")
    void testSalvarAluguel() {
        // Arrange
        Aluguel aluguel = new Aluguel();
        aluguel.setIdAluguel(1);
        aluguel.setDataInicio(LocalDate.now());
        aluguel.setDataFim(LocalDate.now().plusDays(30));
        aluguel.setValorMensal(new BigDecimal("1200"));

        when(aluguelRepository.save(any(Aluguel.class))).thenReturn(aluguel);

        // Act
        AluguelResponseDTO resultado = aluguelService.salvar(AluguelRequestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.idAluguel());
        assertEquals(aluguel.getDataInicio(), resultado.dataInicio());
        assertEquals(aluguel.getDataFim(), resultado.dataFim());
        assertEquals(aluguel.getValorMensal(), resultado.valorMensal());
        verify(aluguelRepository, times(1)).save(any(Aluguel.class));
    }
 */
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
        List<AluguelResponseDTO> resultado = aluguelService.listarTodos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(aluguelRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar aluguel por ID existente")
    void testBuscarPorIdExistente() {
        // Arrange
        Aluguel aluguel = new Aluguel();
        aluguel.setIdAluguel(1);
        aluguel.setDataInicio(LocalDate.now());
        aluguel.setDataFim(LocalDate.now().plusDays(30));
        aluguel.setValorMensal(new BigDecimal("1200"));

        when(aluguelRepository.findById(1)).thenReturn(java.util.Optional.of(aluguel));

        // Act
        AluguelResponseDTO resultado = aluguelService.buscarPorId(1);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.idAluguel());
        verify(aluguelRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar ID inexistente")
    void testBuscarPorIdInexistente() {
        // Arrange
        when(aluguelRepository.findById(999)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> aluguelService.buscarPorId(999));
        verify(aluguelRepository, times(1)).findById(999);
    }

    @Test
    @DisplayName("Deve deletar aluguel existente")
    void testDeletar() {
        // Arrange
        when(aluguelRepository.existsById(1)).thenReturn(true);

        // Act
        aluguelService.deletar(1);

        // Assert
        verify(aluguelRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar aluguel inexistente")
    void testDeletarInexistente() {
        // Arrange
        when(aluguelRepository.existsById(999)).thenReturn(false);

        // Act & Assert
        assertThrows(Exception.class, () -> aluguelService.deletar(999));
        verify(aluguelRepository, never()).deleteById(999);
    }
    
}