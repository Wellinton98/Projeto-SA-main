package com.locacao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.dto.AluguelResponseDTO;
import com.locacao.service.AluguelService;

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> salvar(@RequestBody AluguelRequestDTO dto) {
        AluguelResponseDTO response = aluguelService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AluguelResponseDTO> atualizar(@PathVariable Integer id,
                                                        @RequestBody AluguelRequestDTO dto) {
        AluguelResponseDTO response = aluguelService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AluguelResponseDTO>> listarTodos() {
        return ResponseEntity.ok(aluguelService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AluguelResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(aluguelService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        aluguelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}