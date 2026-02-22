package com.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locacao.model.Cliente;
import com.locacao.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    /*public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);*/


        public Cliente buscarPorId(Integer id) {
    return clienteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado."));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Integer id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);
        if (cliente == null) return null;

        
        return clienteRepository.save(cliente);
    }

    public void deletar(Integer id) {
    clienteRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente com ID " + id + " não encontrado."));

    clienteRepository.deleteById(id);

    }
}

