package br.com.camila.statemachine.repository;

import org.springframework.data.repository.Repository;

import br.com.camila.statemachine.entity.Auditoria;

public interface AuditoriaRepository extends Repository<Auditoria, Long> {

    void save(Auditoria auditoria);
}
