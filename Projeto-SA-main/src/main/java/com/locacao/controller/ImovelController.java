package com.locacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.locacao.dto.ImovelRequestDTO;
import com.locacao.dto.ImovelResponseDTO;
import com.locacao.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping("/novo")
    public String novoImovel(Model model) {
        model.addAttribute("imovel", new ImovelRequestDTO("endereco","tipo","quartos","banheiros","vagas"));
        return "cadastro-imovel";
    }

    
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute ImovelRequestDTO dto,
                         @RequestParam("arquivoFoto") MultipartFile arquivoFoto,
                         @RequestParam("arquivoContrato") MultipartFile arquivoContrato) {

        return "redirect:/imoveis/salvar-com-sucesso";
    }

    
    @GetMapping
    public String listar(Model model) {
        List<ImovelResponseDTO> lista = imovelService.listarTodos();
        model.addAttribute("imoveis", lista);
        return "lista-imoveis";
    }

    // Excluir imóvel
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        imovelService.deletar(id);
        return "redirect:/imoveis";
    }

    // Página de sucesso
    @GetMapping("/salvar-com-sucesso")
    public String salvarComSucesso() {
        return "salvar-com-sucesso";
    }
}