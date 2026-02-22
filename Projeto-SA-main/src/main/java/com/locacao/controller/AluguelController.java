package com.locacao.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locacao.model.Aluguel;
import com.locacao.service.AluguelService;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @PostMapping
    public Aluguel salvar(@RequestBody Aluguel aluguel) {
        return aluguelService.salvar(aluguel);
    }

    @GetMapping
    public List<Aluguel> listarTodos() {
        return aluguelService.listarTodos();
    }

    @GetMapping("/imovel/{idImovel}")
    public List<Aluguel> listarPorImovel(@PathVariable Integer idImovel) {
        return aluguelService.listarAlugueisPorImovel(idImovel);
    }
    public Aluguel imovelNaoexisti(Integer id) {
        return aluguelService.imovelNaoEncontrado(id);
    }

    @GetMapping("/disponivel/{idImovel}")
    public boolean verificarDisponibilidade(
            @PathVariable Integer idImovel,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim) {

        return aluguelService.imovelDisponivel(idImovel, dataInicio, dataFim);
    }

    @GetMapping("/{id}")
    public Aluguel buscarPorId(@PathVariable Integer id) {
        return aluguelService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        aluguelService.deletar(id);
    }
}




