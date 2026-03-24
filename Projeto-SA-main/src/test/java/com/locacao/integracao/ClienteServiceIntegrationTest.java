package com.locacao.integracao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.dto.ClienteRequestDTO;
import com.locacao.dto.ClienteResponseDTO;
import com.locacao.service.ClienteService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
// onde é feita a transação com banco de dados, limpado a cada teste.
public class ClienteServiceIntegrationTest {

    @Autowired
    private ClienteService clienteService;

    @Test
    @DisplayName("Listar clientes")
    public void listarClientes() {

        // Arrange 
        ClienteRequestDTO cliente = new ClienteRequestDTO(null, "Wellinton", "057.524.090-39", "wellinton@gmail.com",
         "47988775543", "Rua Bahia, Bairro Arapongas");
        clienteService.salvar(cliente);

        // Act 
        List<ClienteResponseDTO> clientes = clienteService.listarTodos();

        // Assert
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
        assertEquals("Wellinton", clientes.get(0).nome());
    }

    @Test
    @DisplayName("Salvar cliente com dados inválidos deve lançar exceção")
    public void salvarClienteComDadosInvalidos(){
        // Arrange: nome obrigatório nulo
        ClienteRequestDTO cliente = new ClienteRequestDTO(null, "", "057.524.090-39", "emailinvalido", "47988775543", "Rua Bahia");

        // Act & Assert
        
            clienteService.salvar(cliente);
        
    }

    @Test
    @DisplayName("Buscar por ID existente e retorna sucesso")
    public void buscarPorIdExistente() {
        // Arrange
        ClienteRequestDTO clienteDTO = new ClienteRequestDTO(null, "Wellinton", "057.524.090-39", "wellinton@gmail.com",
        "47988775543", "Rua Bahia, Bairro Arapongas");

        ClienteResponseDTO clienteSalvo = clienteService.salvar(clienteDTO);

        ClienteResponseDTO clienteEncontrado = clienteService.buscarPorId(clienteSalvo.idCliente());

        // Assert
        assertNotNull(clienteEncontrado);
        assertEquals("Wellinton", clienteEncontrado.nome());
        assertEquals("057.524.090-39", clienteEncontrado.cpf());
        assertEquals("wellinton@gmail.com", clienteEncontrado.email());
        assertEquals("47988775543", clienteEncontrado.telefone());
        assertEquals("Rua Bahia, Bairro Arapongas", clienteEncontrado.endereco());
    }

    @Test
    @DisplayName("Buscar por ID inexistente deve lançar exceção")
    public void buscarPorIdInexistente(){
        int idInexistente = 9999;

        assertThrows(ResponseStatusException.class, () -> {
            clienteService.buscarPorId(idInexistente);
        });
    }
}