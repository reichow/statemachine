package br.com.statemachine.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.statemachine.entity.Proposta;

public interface PropostaRepository extends Repository<Proposta, Long> {

    List<Proposta> findAll();

    Optional<Proposta> findById(Long id);

    Proposta save(Proposta proposta);

//    Proposta findByProp1(String prop1);

    Optional<Proposta> findTopByOrderByNumeroDesc();

    Optional<Proposta> findByNumero(Long numero);
}
