package com.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.dto.ImovelRequestDTO;
import com.locacao.dto.ImovelResponseDTO;
import com.locacao.model.Imovel;
import com.locacao.repository.ImovelRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<ImovelResponseDTO> listarTodos() {

        return imovelRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public ImovelResponseDTO buscarPorId(Integer id) {

        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Imóvel com ID " + id + " não encontrado"));

        return converter(imovel);
    }

    public ImovelResponseDTO salvar(ImovelRequestDTO dto) {

        Imovel imovel = new Imovel();

        imovel.setEndereco(dto.endereco());
        imovel.setTipo(dto.tipo());
        imovel.setQuartos(dto.quartos());
        imovel.setBanheiros(dto.banheiros());
        imovel.setVagas(dto.vagas());
        imovel.setMobilia(dto.mobilia());
        imovel.setDisponivel(dto.disponivel());
        imovel.setDescricao(dto.descricao());
        imovel.setValorAluguel(dto.valorAluguel());
        imovel.setFoto(dto.foto());
        imovel.setNegocio(dto.negocio());

        Imovel salvo = imovelRepository.save(imovel);

        return converter(salvo);
    }

    public void deletar(Integer id) {

        if (!imovelRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Imóvel não encontrado");
        }

        imovelRepository.deleteById(id);
    }

    private ImovelResponseDTO converter(Imovel imovel) {

        return new ImovelResponseDTO(
                imovel.getIdImovel(),
                imovel.getEndereco(),
                imovel.getTipo(),
                imovel.getQuartos(),
                imovel.getBanheiros(),
                imovel.getVagas(),
                imovel.getMobilia(),
                imovel.getDisponivel(),
                imovel.getDescricao(),
                imovel.getValorAluguel(),
                imovel.getFoto(),
                imovel.getNegocio()
        );
    }
}