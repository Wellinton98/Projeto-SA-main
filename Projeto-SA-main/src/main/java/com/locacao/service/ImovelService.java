package com.locacao.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.locacao.model.Imovel;
import com.locacao.repository.ImovelRepository;

import jakarta.persistence.Column;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public List<ImovelResponseDTO> listarImovel() {

    List<ImovelResponseDTO> listarImovel = new ArrayList<>();

    for (Imovel imovel : imovelRepository.findAll()) {
        ImovelResponseDTO imovelConvertido = new ImovelResponseDTO (imovel);
        listarImovel.add(imovelConvertido);
    }

    return listarImovel;

}
    public List<Imovel> listarImoveisDisponiveis() {
        return (List<Imovel>)(Object) imovelRepository.findByDisponivelTrue();
    }

    public Imovel buscarPorId(Integer id) {
        return imovelRepository.findById(id).orElse(null);
    }
    private Imovel descricao(String descricao) {
        // TODO Auto-generated method stub
        return null;
    }

    public Imovel salvar(Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    public Imovel atualizar(Integer id, Imovel atualizado) {
        Imovel imovel = buscarPorId(id);
        if (imovel == null) return null;

    
        return imovelRepository.save(imovel);
    }

    public void deletar(Integer id) {
        imovelRepository.deleteById(id);
    }
    @Column(name = "foto")
    private String foto;

    public void excluir(Integer id) {
    imovelRepository.deleteById(id);

    }
}

