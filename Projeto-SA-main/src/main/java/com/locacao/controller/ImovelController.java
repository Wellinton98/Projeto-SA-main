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

import com.locacao.model.Imovel;
import com.locacao.repository.ImovelRepository;
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
   @GetMapping
public String listar(Model model) {
    List<Imovel> lista = imovelService.listarTodos();
    model.addAttribute("imoveis", lista);
    return "lista-imoveis";

    }
    @GetMapping("/excluir/{id}")
public String excluir(@PathVariable Integer id) {
    imovelService.excluir(id);
    return "redirect:/imoveis";
}
@GetMapping("/imoveis/novo")
public String novo(Imovel imovel) {
    return "imoveis/novo"; // mostra o formulário
}

@PostMapping("/imoveis")
public String salvar(@ModelAttribute Imovel imovel) {
    imovelRepository.save(imovel); // salva no banco
    return "redirect:/imoveis";
}
@GetMapping("/imoveis")
public String listarImoveis(Model model) {
    List<Imovel> todosImoveis = imovelRepository.findAll();

    List<Imovel> alugueis = todosImoveis.stream()
                                        .filter(i -> "Alugar".equalsIgnoreCase(i.getNegocio()))
                                        .toList();

    List<Imovel> vendas = todosImoveis.stream()
                                      .filter(i -> "Vender".equalsIgnoreCase(i.getNegocio()))
                                      .toList();

    model.addAttribute("alugueis", alugueis);
    model.addAttribute("vendas", vendas);

    return "imoveis/lista-imoveis"; // template correto
}
}