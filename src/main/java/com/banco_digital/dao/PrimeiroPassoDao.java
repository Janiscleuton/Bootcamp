package com.banco_digital.dao;

import com.banco_digital.modelo.cliente.PrimeiroPasso;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrimeiroPassoDao extends CrudRepository<PrimeiroPasso, Long> {
    List<PrimeiroPasso> findByNomeIgnoreCaseContaining(String nome);
}
