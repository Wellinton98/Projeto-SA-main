package com.locacao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "aluguel")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAluguel;

    @NotNull
    private LocalDate dataInicio;

    @NotNull
    private LocalDate dataFim;

    @DecimalMin("0.00")
    @Column(precision = 10, scale = 2)
    private BigDecimal valorMensal;

    private Boolean seguroIncendio;

    @Column(length = 500)
    private String contratoAluguel;

    @Column(length = 150)
    private String nomeFiador;

    @Column(length = 150)
    private String cpfFiador;

    @DecimalMin("0.00")
    @Column(precision = 10, scale = 2)
    private BigDecimal valorSeguroIncendio;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_imovel", nullable = false)
    private Imovel imovel;

    public Aluguel() {}

    // Getters e Setters
    // ...

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluguel)) return false;
        Aluguel aluguel = (Aluguel) o;
        return idAluguel != null && idAluguel.equals(aluguel.idAluguel);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "id=" + idAluguel +
                ", cliente=" + cliente +
                ", imovel=" + imovel +
                ", valorMensal=" + valorMensal +
                '}';
    }
}