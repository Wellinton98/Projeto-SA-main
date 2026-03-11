package com.locacao.integracao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.locacao.service.ClienteService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional

public class AluguelServiceintegrationTest {

    @Autowired
    private ClienteService clienteService;

    @Test
@DisplayName("Buscar imóvel por id existente")
public void listarImovelPorId(){

    // Arrange

    
}
}
