package com.locacao.unitario;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.locacao.model.Aluguel;
import com.locacao.repository.AluguelRepository;
import com.locacao.service.AluguelService;

@ExtendWith(MockitoExtension.class)
public class AluguelServiceTest {

    @Mock
    private AluguelRepository aluguelRepository;

    @InjectMocks
    private AluguelService aluguelService;

    @Test
    @DisplayName("Deve salvar um aluguel com sucesso")
    void testSalvarAluguel(){

        // Arrange 
        Aluguel aluguel = new Aluguel();

        when(aluguelRepository.save(aluguel))
                .thenReturn(aluguel);

        //  Act 
        Aluguel resultado =
                aluguelService.salvar(aluguel);

        // Assert 
        assertNotNull(resultado);
        }
    @Test
    @DisplayName("Deve listar o aluguel por imóvel")
    void deveListarPorImovel(){

        //  Arrange
        Integer idImovel = 1;
        when(aluguelRepository.findByImovelIdImovel(idImovel))
                .thenReturn(List.of(new Aluguel()));

        //  Act 
        List<Aluguel> resultado =
                aluguelService.listarAlugueisPorImovel(idImovel);

        //  Assert 
        assertNotNull(resultado);
        verify(aluguelRepository)
                .findByImovelIdImovel(idImovel);
    }
@Test
@DisplayName("Deve verificar se o imóvel está disponível")
void deveVerificarDisponibilidade(){

    // Arrange 

    when(aluguelRepository.verificarDisponibilidade(
            anyInt(),
            any(LocalDate.class),
            any(LocalDate.class)))
        .thenReturn(List.of());

    //  Act 
    boolean resultado =
            aluguelService.imovelDisponivel(
                    1,
                    LocalDate.now(),
                    LocalDate.now().plusDays(5));

    // Assert 
    assertTrue(resultado);
    verify(aluguelRepository)
            .verificarDisponibilidade(
                    anyInt(),
                    any(LocalDate.class),
                    any(LocalDate.class));
}
    @Test
    @DisplayName("deve reotnar falso quando o imovel não estiver disponivel")
    void deveRetornarImovelIndisponivel(){
            //arrange
            when(aluguelRepository.verificarDisponibilidade(
                anyInt(),
                any(LocalDate.class),
                any(LocalDate.class)))
            .thenReturn(List.of(new Aluguel()));

            //act
            boolean resultado =
                aluguelService.imovelDisponivel(
                        1,
                        LocalDate.now(),
                        LocalDate.now().plusDays(5));

                        //assert
                        assertFalse(resultado);
                        verify(aluguelRepository)
                .verificarDisponibilidade(
                        anyInt(),
                        any(LocalDate.class),
                        any(LocalDate.class));

    }
    
}