package com.locacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.service.AluguelService;



@Controller
@RequestMapping("/alugueis")
public class AluguelViewController {

    @Autowired
private AluguelService aluguelService;

     @GetMapping("/novo")
public String novoAluguel(Model model) {
    model.addAttribute("aluguel", new AluguelRequestDTO(
        null, null, null, null,
        null, null, null, null, null, null
    ));
    return "cadastro-aluguel";
    
}
}