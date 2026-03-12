package com.locacao.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import com.locacao.model.Imovel;
import com.locacao.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    // formulário
    @GetMapping("/novo")
    public String novoImovel(Model model) {
        model.addAttribute("imovel", new Imovel());
        return "cadastro-imovel";
    }

    // salvar
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Imovel imovel,
                         @RequestParam("arquivoFoto") MultipartFile arquivo,
                         @RequestParam("arquivoContrato") MultipartFile arquivoContrato) {

        try {

            if (!arquivo.isEmpty()) {
                String nomeArquivo = System.currentTimeMillis() + "_" + arquivo.getOriginalFilename();
                Path caminho = Paths.get("src/main/resources/static/uploads/" + nomeArquivo);
                Files.write(caminho, arquivo.getBytes());
                imovel.setFoto(nomeArquivo);
            }

            if (!arquivoContrato.isEmpty()) {
                String nomeContrato = System.currentTimeMillis() + "_" + arquivoContrato.getOriginalFilename();
                Path caminhoContrato = Paths.get("src/main/resources/static/uploads/" + nomeContrato);
                Files.write(caminhoContrato, arquivoContrato.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/imoveis/salvar-com-sucesso";
    }

    // salva com sucesso
    @GetMapping("/salvar-com-sucesso")
    public String salvarComSucesso() {
        return "salvar-com-sucesso";
    }

    // listar imoveis
    @GetMapping
    public String listar(Model model) {
        List<ImovelRequestDTO> lista = imovelService.listarTodos();
        model.addAttribute("imoveis", lista);
        return "lista-imoveis";
    }

    // excluir
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        imovelService.excluir(id);
        return "redirect:/imoveis";
    }
}