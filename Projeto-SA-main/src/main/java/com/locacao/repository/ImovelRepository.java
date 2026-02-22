package com.locacao.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locacao.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Integer> {
    Optional<Imovel> findById(Integer id_imovel);

    boolean existsById(Integer id_imovel);
    List<Object> findByDisponivelTrue();
    
 
}
