package br.com.statemachine.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.statemachine.entity.StateMachineEntity;

public interface StateMachineRepository extends Repository<StateMachineEntity, Long> {

    Optional<StateMachineEntity> findByIdMaquina(String idMaquina);

    StateMachineEntity save(StateMachineEntity entity);

}
