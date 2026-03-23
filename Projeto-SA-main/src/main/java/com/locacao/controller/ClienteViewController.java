package com.locacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locacao.dto.ClienteRequestDTO;
import com.locacao.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/novo")
    public String novoCliente() {
        System.out.println("CHEGOU NO CONTROLLER");
        return "cliente-form";
    }

    @PostMapping("/salvar")
    public String salvar(ClienteRequestDTO dto) {
        try {
            clienteService.salvar(dto);
            return "redirect:/cliente/novo?sucesso";
        } catch (Exception e) {
            return "redirect:/cliente/novo?erro";
        }
    }
}