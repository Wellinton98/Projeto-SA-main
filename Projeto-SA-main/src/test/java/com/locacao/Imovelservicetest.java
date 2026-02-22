package com.locacao;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

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
    void testBuscarPorIdRetornoCorreto() {

        
        Integer id = 1;// ID do imovel a ser buscado

        Imovel imovel = new Imovel();// Criando o imóvel mockado
        imovel.setIdImovel(id); // Definido o id por imovel
        imovel.setEndereco("Rua A");// Definindo o endereço
        imovel.setTipo("Casa");// Definindo o tipo da casa
        imovel.setDescricao("Casa para alugar");// Definindo a descrição do imovel 
        imovel.setDisponivel(true);// Definindo a disponibilidade do imovel

        when(imovelRepository.findById(id))
                .thenReturn(Optional.of(imovel));

        // chamando o service
        Imovel resultado = imovelService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdImovel());
        assertEquals("Casa para alugar", resultado.getDescricao());
        assertTrue(resultado.getDisponivel());

        verify(imovelRepository, times(1)).findById(id);
    }

     @Test
    void testBuscarPorIdInexistenteDeveLancarExcecao() {

        Integer id = 999; // ID que não existe

        when(imovelRepository.findById(id))
                .thenReturn(Optional.empty()); // O repositório não encontrou nada

        
        Imovel resultado = imovelService.buscarPorId(id); //Chama o service
        assertEquals(null, resultado
        );

        // Verifica se o repository foi chamado uma vez
        verify(imovelRepository, times(1)).findById(id);
    }

}




