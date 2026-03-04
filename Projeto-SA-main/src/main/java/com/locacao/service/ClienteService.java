package com.locacao.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.locacao.model.Cliente;
import com.locacao.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Cliente com ID " + id + " não encontrado."));
    }

    public Cliente salvar(ClienteRequestDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setEndereco(dto.endereco());
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Integer id, ClienteRequestDTO dto) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEmail(dto.email());
        cliente.setTelefone(dto.telefone());
        cliente.setEndereco(dto.endereco());
        return clienteRepository.save(cliente);
    }

    public void deletar(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }
}

