package br.com.statemachine.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.statemachine.entity.Auditoria;
import br.com.statemachine.entity.Proposta;

public interface AuditoriaRepository extends Repository<Auditoria, Long> {

    List<Proposta> findAll();

    Auditoria findById(Long id);

    void save(Auditoria auditoria);
}
