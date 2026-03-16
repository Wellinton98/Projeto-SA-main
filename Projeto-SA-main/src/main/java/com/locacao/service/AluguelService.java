package com.locacao.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.locacao.dto.AluguelRequestDTO;
import com.locacao.dto.AluguelResponseDTO;
import com.locacao.model.Aluguel;
import com.locacao.model.Cliente;
import com.locacao.model.Imovel;
import com.locacao.repository.AluguelRepository;
import com.locacao.repository.ClienteRepository;
import com.locacao.repository.ImovelRepository;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final ClienteRepository clienteRepository;
    private final ImovelRepository imovelRepository;

    public AluguelService(AluguelRepository aluguelRepository,
                          ClienteRepository clienteRepository,
                          ImovelRepository imovelRepository) {
        this.aluguelRepository = aluguelRepository;
        this.clienteRepository = clienteRepository;
        this.imovelRepository = imovelRepository;
    }

    public AluguelResponseDTO salvar(AluguelRequestDTO dto) {
        validarDatas(dto);

        Cliente cliente = clienteRepository.findById(dto.idCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        Imovel imovel = imovelRepository.findById(dto.idImovel())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imóvel não encontrado"));

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setImovel(imovel);
        aluguel.setDataInicio(dto.dataInicio());
        aluguel.setDataFim(dto.dataFim());
        aluguel.setValorMensal(dto.valorMensal());
        aluguel.setSeguroIncendio(dto.seguroIncendio());
        aluguel.setContratoAluguel(dto.contratoAluguel());
        aluguel.setNomeFiador(dto.nomeFiador());
        aluguel.setCpfFiador(dto.cpfFiador());
        aluguel.setValorSeguroIncendio(dto.valorSeguroIncendio());

        return converter(aluguelRepository.save(aluguel));
    }

    public AluguelResponseDTO atualizar(Integer id, AluguelRequestDTO dto) {
        validarDatas(dto);

        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel não encontrado"));

        aluguel.setDataInicio(dto.dataInicio());
        aluguel.setDataFim(dto.dataFim());
        aluguel.setValorMensal(dto.valorMensal());
        aluguel.setSeguroIncendio(dto.seguroIncendio());
        aluguel.setContratoAluguel(dto.contratoAluguel());
        aluguel.setNomeFiador(dto.nomeFiador());
        aluguel.setCpfFiador(dto.cpfFiador());
        aluguel.setValorSeguroIncendio(dto.valorSeguroIncendio());

        return converter(aluguelRepository.save(aluguel));
    }

    public List<AluguelResponseDTO> listarTodos() {
        return aluguelRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    public AluguelResponseDTO buscarPorId(Integer id) {
        Aluguel aluguel = aluguelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel não encontrado"));
        return converter(aluguel);
    }

    public void deletar(Integer id) {
        if (!aluguelRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel não encontrado");
        }
        aluguelRepository.deleteById(id);
    }

    private AluguelResponseDTO converter(Aluguel aluguel) {
        return new AluguelResponseDTO(
                aluguel.getIdAluguel(),
                aluguel.getCliente().getNome(),
                aluguel.getImovel().getEndereco(),
                aluguel.getDataInicio(),
                aluguel.getDataFim(),
                aluguel.getValorMensal(),
                aluguel.getSeguroIncendio(),
                aluguel.getValorSeguroIncendio(),
                aluguel.getNomeFiador(),
                aluguel.getCpfFiador()
        );
    }

    private void validarDatas(AluguelRequestDTO dto) {
        if (dto.dataFim().isBefore(dto.dataInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data fim não pode ser anterior à data início");
        }
    }
}