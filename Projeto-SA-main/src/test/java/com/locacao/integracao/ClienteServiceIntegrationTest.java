package com.locacao.integracao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.locacao.dto.ClienteRequestDTO;
import com.locacao.model.Cliente;
import com.locacao.service.ClienteService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class ClienteServiceIntegrationTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    @DisplayName("Listar clientes")
    public void listarClientes() {

        // Arrange 
        ClienteRequestDTO cliente = new ClienteRequestDTO(null, "Wellinton", "057.524.090-39", "wellinton@gmail.com", "47988775543", "Rua Bahia, Bairro Arapongas");
        clienteService.salvar(cliente);

        //  Act 
        List<Cliente> clientes =
                clienteService.listarTodos();

        // Assert
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }
}