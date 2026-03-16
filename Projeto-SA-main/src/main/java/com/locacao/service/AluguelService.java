package com.locacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.locacao.dto.AluguelRequestDTO;
import com.locacao.dto.AluguelResponseDTO;
import com.locacao.model.Aluguel;
import com.locacao.repository.AluguelRepository;

@Service
public class AluguelService {

    private static final String CLIENTE_INDEFINIDO = "Sem cliente";
    private static final String IMOVEL_INDEFINIDO = "Sem imóvel";

    @Autowired
    private AluguelRepository aluguelRepository;

    // Salvar um novo aluguel
    public AluguelResponseDTO salvar(AluguelRequestDTO dto) {

        Aluguel aluguel = new Aluguel();
        aluguel.setDataInicio(dto.dataInicio());
        aluguel.setDataFim(dto.dataFim());
        aluguel.setValorMensal(dto.valorMensal());

        Aluguel salvo = aluguelRepository.save(aluguel);

        return converter(salvo);
    }

    // Listar todos os alugueis
    public List<AluguelResponseDTO> listarTodos() {
        return aluguelRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    // Buscar aluguel por ID
    public AluguelResponseDTO buscarPorId(Integer id) {

        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Aluguel com ID " + id + " não encontrado"));

        return converter(aluguel);
    }

    // Deletar aluguel por ID
    public void deletar(Integer id) {

        if (!aluguelRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Aluguel não encontrado");
        }

        aluguelRepository.deleteById(id);
    }

    // Conversão segura para DTO
    private AluguelResponseDTO converter(Aluguel aluguel) {
        String nomeCliente = aluguel.getCliente() != null ? aluguel.getCliente().getNome() : CLIENTE_INDEFINIDO;
        String enderecoImovel = aluguel.getImovel() != null ? aluguel.getImovel().getEndereco() : IMOVEL_INDEFINIDO;

        return new AluguelResponseDTO(
                aluguel.getIdAluguel(),
                nomeCliente,
                enderecoImovel,
                aluguel.getDataInicio(),
                aluguel.getDataFim(),
                aluguel.getValorMensal()
        );
    }
    public AluguelResponseDTO atualizar(Integer id, AluguelRequestDTO dto) {

        Aluguel aluguelExistente = aluguelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aluguel com ID " + id + " não encontrado"));

      
        Aluguel atualizado = aluguelRepository.save(aluguelExistente);
        return converter(atualizado);
    }
}