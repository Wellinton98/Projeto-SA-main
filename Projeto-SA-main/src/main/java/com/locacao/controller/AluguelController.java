package com.locacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.dto.AluguelResponseDTO;
import com.locacao.model.Aluguel;
import com.locacao.service.AluguelService;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping
    public Aluguel salvar(@RequestBody AluguelRequestDTO dto) {
        return aluguelService.salvar(dto);
    }

    @GetMapping
    public List<AluguelResponseDTO> listarTodos() {
        return aluguelService.listarTodos();
    }

    @GetMapping("/{id}")
    public AluguelResponseDTO buscarPorId(@PathVariable Integer id) {
        return aluguelService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        aluguelService.deletar(id);
    }
}


