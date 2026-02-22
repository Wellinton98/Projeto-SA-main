package com.locacao.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.locacao.model.Imovel;
import com.locacao.service.ImovelService;

@Controller
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping("/novo")
    public String novoImovel(Model model) {
        model.addAttribute("imovel", new Imovel());
        return "cadastro-imovel";
    }

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

        // Se quiser salvar contrato também
        if (!arquivoContrato.isEmpty()) {
            String nomeContrato = System.currentTimeMillis() + "_" + arquivoContrato.getOriginalFilename();
            Path caminhoContrato = Paths.get("src/main/resources/static/uploads/" + nomeContrato);
            Files.write(caminhoContrato, arquivoContrato.getBytes());
            // depois você pode criar campo contrato na entidade
        }

        imovelService.salvar(imovel);

    } catch (IOException e) {
        e.printStackTrace();
    }

    return "redirect:/imoveis/salvar-com-sucesso";
}

    @GetMapping("/salvar-com-sucesso")
    public String salvarComSucesso() {
        return "salvar-com-sucesso";
    }
}