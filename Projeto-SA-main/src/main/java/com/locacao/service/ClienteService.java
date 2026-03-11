package com.locacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.dto.ClienteRequestDTO;
import com.locacao.dto.ClienteResponseDTO;
import com.locacao.model.Cliente;
import com.locacao.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteResponseDTO> listarTodos() {

        List<ClienteResponseDTO> listaCliente = new ArrayList<>();

        for (Cliente cliente : clienteRepository.findAll()) {
            ClienteResponseDTO clienteConvertido = conversorClienteParaDto(cliente);
            listaCliente.add(clienteConvertido);
        }

        return listaCliente;
    }

    private ClienteResponseDTO conversorClienteParaDto(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getIdCliente(), cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getTelefone(), cliente.getEndereco());
    }

    private Cliente conversorDtoParaCliente(ClienteResponseDTO clienteDto) {
        return new Cliente(clienteDto.cpf(), clienteDto.email(), clienteDto.endereco(), clienteDto.id(), clienteDto.nome(), clienteDto.telefone());
    }

    public ClienteResponseDTO buscarPorId(Integer id) {

        Cliente clienteBuscado = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente com ID " + id + " não encontrado."));

        return conversorClienteParaDto(clienteBuscado);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setEndereco(dto.endereco());

        Cliente clienteSalvado = clienteRepository.save(cliente);

        return conversorClienteParaDto(clienteSalvado);
    }

    public ClienteResponseDTO atualizar(Integer id, ClienteRequestDTO dto) {
        
        ClienteResponseDTO clienteBuscado = buscarPorId(id);

        Cliente clienteAtualizado = clienteRepository.save(conversorDtoParaCliente(clienteBuscado));

        return conversorClienteParaDto(clienteAtualizado);
    }

    public void deletar(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }
}

