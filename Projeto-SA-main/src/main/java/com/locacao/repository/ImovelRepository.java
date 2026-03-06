package com.locacao.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.locacao.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
    Optional<Imovel> findById(Integer id_imovel);

    boolean existsById(Integer id_imovel);
    List<Object> findByDisponivelTrue();

    @Query("SELECT i FROM Imovel i WHERE "
     + "(:negocio IS NULL OR i.negocio = :negocio)")
List<Imovel> findByNegocio(@Param("negocio") String negocio);
    
 
}
