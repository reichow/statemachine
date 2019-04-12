package br.com.camila.statemachine.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import br.com.camila.statemachine.entity.Proposta;
import br.com.camila.statemachine.entity.Auditoria;

public interface AuditoriaRepository extends Repository<Auditoria, Long> {

    List<Proposta> findAll();

    Auditoria findById(Long id);

    void save(Auditoria auditoria);
}
