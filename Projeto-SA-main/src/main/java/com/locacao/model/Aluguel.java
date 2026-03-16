package com.locacao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.locacao.dto.AluguelResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluguel;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorMensal;

    private Boolean seguroIncendio;

    @Column(length = 500)
    private String contratoAluguel;

    @Column(length = 150)
    private String nomeFiador;

    @Column(length = 150)
    private String cpfFiador;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorSeguroIncendio;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imovel imovel;

    public Aluguel() {}

    // GETTERS
    public Integer getIdAluguel() { return idAluguel; }
    public LocalDate getDataInicio() { return dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public BigDecimal getValorMensal() { return valorMensal; }
    public Boolean getSeguroIncendio() { return seguroIncendio; }
    public String getContratoAluguel() { return contratoAluguel; }
    public String getNomeFiador() { return nomeFiador; }
    public String getCpfFiador() { return cpfFiador; }
    public BigDecimal getValorSeguroIncendio() { return valorSeguroIncendio; }
    public Cliente getCliente() { return cliente; }
    public Imovel getImovel() { return imovel; }

    // SETTERS
    public void setIdAluguel(Integer idAluguel) { this.idAluguel = idAluguel; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public void setValorMensal(BigDecimal valorMensal) { this.valorMensal = valorMensal; }
    public void setSeguroIncendio(Boolean seguroIncendio) { this.seguroIncendio = seguroIncendio; }
    public void setContratoAluguel(String contratoAluguel) { this.contratoAluguel = contratoAluguel; }
    public void setNomeFiador(String nomeFiador) { this.nomeFiador = nomeFiador; }
    public void setCpfFiador(String cpfFiador) { this.cpfFiador = cpfFiador; }
    public void setValorSeguroIncendio(BigDecimal valorSeguroIncendio) { this.valorSeguroIncendio = valorSeguroIncendio; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setImovel(Imovel imovel) { this.imovel = imovel; }

    // MÉTODO AUXILIAR PARA DTO
    public AluguelResponseDTO toResponseDTO() {
        String nomeCliente = cliente != null ? cliente.getNome() : "Sem cliente";
        String enderecoImovel = imovel != null ? imovel.getEndereco() : "Sem imóvel";

        return new AluguelResponseDTO(
            idAluguel,
            nomeCliente,
            enderecoImovel,
            dataInicio,
            dataFim,
            valorMensal
        );
    }
}