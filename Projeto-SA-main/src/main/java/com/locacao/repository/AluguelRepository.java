package com.locacao.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locacao.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

    Optional<Aluguel> findById(Integer id_aluguel);

    boolean existsById(Integer id_aluguel);

    List<Aluguel> findByImovelIdImovel(Integer idImovel);
    
    

    @Query("""
        SELECT a FROM Aluguel a
        WHERE a.imovel.idImovel = :imovelId
        AND (
              a.dataInicio <= :dataFim
              AND a.dataFim >= :dataInicio
        )
    """)
    List<Aluguel> verificarDisponibilidade(
        @Param("imovelId") Integer imovelId,
        @Param("dataInicio") LocalDate dataInicio,
        @Param("dataFim") LocalDate dataFim
    );
}

