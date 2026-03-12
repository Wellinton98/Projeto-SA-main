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
        ClienteRequestDTO cliente = new ClienteRequestDTO(null, "Wellinton", "057.524.090-39", "wellinton@gmail.com",
         "47988775543", "Rua Bahia, Bairro Arapongas");
        clienteService.salvar(cliente);

        //  Act 
        clienteService.salvar(cliente);

        // Assert
        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }

    
    @Test
    @DisplayName("salvar cliente com dados invalidos")
    public void salvarClienteComDadosInvalidos(){
        // Arrange
        ClienteRequestDTO cliente = new ClienteRequestDTO(null, "nome invalido","057.524.090-39","wellinton@gmail.com",
        "47988775543","Rua Bahia, Bairro Arapongas");
        clienteService.salvar(cliente);

        assertThrows(RuntimeException.class, () -> {
        clienteService.salvar(cliente);
    });
}
    @Test
    @DisplayName("Buscar por ID existente e retorna sucesso")
    public void buscarPorIdExistente() {
        // Arrange
        ClienteRequestDTO clienteDTO = new ClienteRequestDTO(null, "Wellinton", "057.524.090-39", "wellinton@gmail.com",
        "47988775543", "Rua Bahia, Bairro Arapongas");

        Cliente clienteSalvo = clienteService.salvar(clienteDTO);

        Cliente clienteEncontrado = clienteService.buscarPorId(clienteSalvo.getIdCliente());

        assertNotNull(clienteEncontrado);
        assertEquals("Wellinton", clienteEncontrado.getNome());
        assertEquals("057.524.090-39", clienteEncontrado.getCpf());
        assertEquals("wellinton@gmail.com", clienteEncontrado.getEmail());
        assertEquals("47988775543", clienteEncontrado.getTelefone());
        assertEquals("Rua Bahia, Bairro Arapongas", clienteEncontrado.getEndereco());
}
    @Test
    @DisplayName("Buscar por ID inexistente: deve lançar exceção")
    public void buscarPorIdInexistente(){

        int idInexistente = 9999;

        assertThrows(RuntimeException.class, () -> {
            clienteService.buscarPorId(idInexistente);
    });

}
}