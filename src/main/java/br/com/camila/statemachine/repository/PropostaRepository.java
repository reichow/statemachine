package br.com.camila.statemachine.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.camila.statemachine.entity.Proposta;

public interface PropostaRepository extends Repository<Proposta, Long> {

    Proposta save(Proposta proposta);

    Optional<Proposta> findTopByOrderByNumeroDesc();

    Optional<Proposta> findByNumero(Long numero);
}
