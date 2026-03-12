package com.locacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.model.Imovel;
import com.locacao.repository.ImovelRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<ImovelResponseDTO> listarTodos() {

        List<ImovelResponseDTO> listaImoveis = new ArrayList<>();

        for (Imovel imovel : imovelRepository.findAll()) {
            ImovelResponseDTO imovelConvertido = conversorImovelParaDto(imovel);
            listaImoveis.add(imovelConvertido);
        }

        return listaImoveis;
    }

    private ImovelResponseDTO conversorImovelParaDto(Imovel imovel) {
        return new ImovelResponseDTO(
                imovel.getId(),
                imovel.getEndereco(),
                imovel.getTipo(),
                imovel.getValor(),
                imovel.getFoto(),
                imovel.getDisponivel()
        );
    }

    private Imovel conversorDtoParaImovel(ImovelResponseDTO dto) {
        return new Imovel(
                dto.id(),
                dto.endereco(),
                dto.tipo(),
                dto.valor(),
                dto.foto(),
                dto.disponivel()
        );
    }

    public ImovelResponseDTO buscarPorId(Integer id) {

        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Imóvel com ID " + id + " não encontrado."));

        return conversorImovelParaDto(imovel);
    }

    public ImovelResponseDTO salvar(ImovelRequestDTO dto) {

        Imovel imovel = new Imovel();

        imovel.setEndereco(dto.endereco());
        imovel.setTipo(dto.tipo());
        imovel.setValor(dto.valor());
        imovel.setFoto(dto.foto());
        imovel.setDisponivel(dto.disponivel());

        Imovel imovelSalvo = imovelRepository.save(imovel);

        return conversorImovelParaDto(imovelSalvo);
    }

    public ImovelResponseDTO atualizar(Integer id, ImovelRequestDTO dto) {

        Imovel imovel = imovelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Imóvel com ID " + id + " não encontrado."));

        imovel.setEndereco(dto.endereco());
        imovel.setTipo(dto.tipo());
        imovel.setValor(dto.valor());
        imovel.setFoto(dto.foto());
        imovel.setDisponivel(dto.disponivel());

        Imovel imovelAtualizado = imovelRepository.save(imovel);

        return conversorImovelParaDto(imovelAtualizado);
    }

    public void deletar(Integer id) {

        if (!imovelRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Imóvel com ID " + id + " não encontrado."
            );
        }

        imovelRepository.deleteById(id);
    }

    public List<ImovelResponseDTO> listarDisponiveis() {

        List<ImovelResponseDTO> lista = new ArrayList<>();

        for (Imovel imovel : imovelRepository.findByDisponivelTrue()) {
            lista.add(conversorImovelParaDto(imovel));
        }

        return lista;
    }

    public void excluir(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}