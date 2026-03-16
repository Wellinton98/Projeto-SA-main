package com.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.dto.AluguelResponseDTO;
import com.locacao.model.Aluguel;
import com.locacao.repository.AluguelRepository;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public Aluguel salvar(AluguelRequestDTO dto) {

        Aluguel aluguel = new Aluguel();

        aluguel.setDataInicio(dto.dataInicio());
        aluguel.setDataFim(dto.dataFim());
        aluguel.setValorMensal(dto.valorMensal());

        return aluguelRepository.save(aluguel);
    }

    public List<AluguelResponseDTO> listarTodos() {
        return aluguelRepository.findAll()
                .stream()
                .map(aluguel -> new AluguelResponseDTO(
                        aluguel.getIdAluguel(),
                        aluguel.getCliente().getNome(),
                        aluguel.getImovel().getEndereco(),
                        aluguel.getDataInicio(),
                        aluguel.getDataFim(),
                        aluguel.getValorMensal()))
                .toList();
    }

    public AluguelResponseDTO buscarPorId(Integer id) {

        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow();

        return new AluguelResponseDTO(
                aluguel.getIdAluguel(),
                aluguel.getCliente().getNome(),
                aluguel.getImovel().getEndereco(),
                aluguel.getDataInicio(),
                aluguel.getDataFim(),
                aluguel.getValorMensal());
    }

    public void deletar(Integer id) {
        aluguelRepository.deleteById(id);
    }
}