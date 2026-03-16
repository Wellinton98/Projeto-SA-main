package com.locacao.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.dto.ClienteRequestDTO;
import com.locacao.dto.ClienteResponseDTO;
import com.locacao.model.Cliente;
import com.locacao.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ClienteResponseDTO buscarPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com ID " + id + " não encontrado"));
        return converterParaDTO(cliente);
    }

    public ClienteResponseDTO salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(dto.endereco());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        Cliente clienteSalvo = clienteRepository.save(cliente);
        return converterParaDTO(clienteSalvo);
    }

    public ClienteResponseDTO atualizar(Integer id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente com ID " + id + " não encontrado"));

        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(dto.endereco());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());

        Cliente atualizado = clienteRepository.save(cliente);
        return converterParaDTO(atualizado);
    }

    public void deletar(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente com ID " + id + " não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteResponseDTO converterParaDTO(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getIdCliente(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }
}