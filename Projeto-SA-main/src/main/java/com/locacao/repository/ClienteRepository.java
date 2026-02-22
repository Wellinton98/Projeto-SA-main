package com.locacao.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locacao.model.Cliente;

    public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
        Optional<Cliente> findById(Integer id_cliente);

        boolean existsById(Integer id_cliente);
    
}
