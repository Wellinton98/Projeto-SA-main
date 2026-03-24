package com.locacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.dto.AluguelResponseDTO;
import com.locacao.service.AluguelService;

@Controller
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;
    @Autowired
    AluguelService clienteService;
    @Autowired
    AluguelService imovelService;

    // Criar novo aluguel
    @PostMapping
    public AluguelResponseDTO salvar(@Valid @RequestBody AluguelRequestDTO dto) {
        return aluguelService.salvar(dto);
    }
   

    // Listar todos os alugueis
    @GetMapping
    public List<AluguelResponseDTO> listarTodos() {
        return aluguelService.listarTodos();
    }

    // Buscar aluguel por ID
    @GetMapping("/{id}")
    public AluguelResponseDTO buscarPorId(@Valid @PathVariable Integer id) {
        return aluguelService.buscarPorId(id);
    }

    // Atualizar aluguel existente
    @PutMapping("/{id}")
    public AluguelResponseDTO atualizar(@Valid @PathVariable Integer id, @RequestBody AluguelRequestDTO dto) {
        return aluguelService.atualizar(id, dto);
    }

    // Deletar aluguel por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        aluguelService.deletar(id);
        return ResponseEntity.noContent().build();
    
    }

    @PostMapping("/salvar")
    public String salvarAluguel(AluguelRequestDTO dto, RedirectAttributes redirectAttributes) {

    try {
        aluguelService.salvar(dto);

        redirectAttributes.addFlashAttribute("sucesso", "Aluguel salvo com sucesso!");

    } catch (Exception e) {

        redirectAttributes.addFlashAttribute("erro", "Erro ao salvar aluguel: " + e.getMessage());
    }

    return "redirect:/alugueis/novo";
}
}