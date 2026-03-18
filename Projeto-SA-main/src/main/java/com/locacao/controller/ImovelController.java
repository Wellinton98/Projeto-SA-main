package com.locacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    model.addAttribute("imovel", new ImovelRequestDTO(
        "", "", (short)0, (short)0, (short)0,
        false, true, "", null, "", ""
    ));
    
    // Mude de "form-imovel" para "cadastro-imovel"
    return "cadastro-imovel"; 
}


    // MANTENHA APENAS ESTE MÉTODO POST
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute ImovelRequestDTO dto, // Removido @Valid se não houver BindingResult
            @RequestParam("arquivoFoto") MultipartFile foto,
            @RequestParam("arquivoContrato") MultipartFile contrato) {

        // 1. Aqui você deve implementar a lógica para salvar os arquivos no disco
        // 2. Depois, setar o nome/caminho dos arquivos no seu DTO ou Entity
        
        imovelService.salvar(dto);
        return "redirect:/imoveis/salvar-com-sucesso";
    }
  
    @GetMapping
    public String listar(Model model) {
        List<ImovelResponseDTO> lista = imovelService.listarTodos();
        model.addAttribute("imoveis", lista);
        return "lista-imoveis";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        imovelService.deletar(id);
        return "redirect:/imoveis";
    }

    @GetMapping("/salvar-com-sucesso")
    public String salvarComSucesso() {
        return "salvar-com-sucesso";
    }
}
