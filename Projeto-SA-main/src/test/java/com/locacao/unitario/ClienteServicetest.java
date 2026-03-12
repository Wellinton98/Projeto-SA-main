package com.locacao.unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.locacao.dto.ClienteRequestDTO;
import com.locacao.dto.ClienteResponseDTO;
import com.locacao.model.Cliente;
import com.locacao.repository.ClienteRepository;
import com.locacao.service.ClienteService;

@ExtendWith(MockitoExtension.class)
class ClienteServicetest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveCriarNovoClienteComSucesso() {

        // Arrange — criando cliente enviado pelo usuário (sem ID ainda)
        ClienteRequestDTO clienteDTO = new ClienteRequestDTO(1,"João Silva","12345678900","joao@gmail.com",
        "11999999999","Rua A, 123");
        
        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteDTO.id());
        cliente.setNome(clienteDTO.nome());
        cliente.setCpf(clienteDTO.cpf());
        cliente.setEmail(clienteDTO.email());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setEndereco(clienteDTO.endereco());

        // Mock do save()
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        // Act — chamando o service
        ClienteResponseDTO resultado = clienteService.salvar(clienteDTO);

        // Assert
        assertNotNull(resultado); // Verifica se o resultado não é nulo
        assertEquals(1, resultado.getIdCliente()); // Verifica se o ID foi atribuído
        assertEquals("João Silva", resultado.getNome()); // Verifica o nome
        assertEquals("12345678900", resultado.getCpf()); // Verifica o CPF
        assertEquals("Rua A, 123", resultado.getEndereco()); // Verifica o endereço
        assertEquals("joao@gmail.com", resultado.getEmail()); // Verifica o email
        assertEquals("11999999999", resultado.getTelefone()); // Verifica o telefone

        
}
/* 
   @Test
    void deveLancarExcecao_QuandoBuscarPorIdInexistente() {

        Integer idInexistente = 999; // ID que não existe no banco de dados

        Mockito.when(clienteRepository.findById(idInexistente))
                .thenReturn(Optional.empty());// Simula que o cliente não foi salvo

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.buscarPorId(idInexistente);// Chama o service
        });

        assertEquals("Cliente com ID 999 não encontrado.", exception.getMessage());
        Mockito.verify(clienteRepository).findById(idInexistente);// Verifica se o repositorio foi chamado
    }

    @Test
    void deveDeletarClienteDoSistema() {

        Integer idExistente = 1; // ID que existe no banco de dados

        // cliente existente no "banco"
        Cliente clienteExistente = new Cliente();//
        clienteExistente.setIdCliente(idExistente);
        clienteExistente.setNome("Maria Souza");
        clienteExistente.setCpf("98765432100");
        clienteExistente.setEndereco("Rua B, 456");
        clienteExistente.setEmail("maria123@gmail.com");
        clienteExistente.setTelefone("11988888888");

        Mockito.when(clienteRepository.findById(idExistente))
                .thenReturn(Optional.of(clienteExistente));// Simula que o cliente foi encontrado

        // chamar o service
        clienteService.deletar(idExistente);

        // garantir que o repositório foi chamado corretamente
        Mockito.verify(clienteRepository, Mockito.times(1))
                .deleteById(idExistente);// Verifica se o deleteByid foi chmado corretamente
    }*/
}
