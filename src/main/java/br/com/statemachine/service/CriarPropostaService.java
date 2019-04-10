package br.com.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.entity.Proposta;
import br.com.statemachine.repository.PropostaRepository;

@Service
public class CriarPropostaService {

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

    @Autowired
    private PropostaRepository repository;

    public Proposta executar(String cpf) {

        Proposta ultimaProposta = repository.findTopByOrderByNumeroDesc().orElse(Proposta.builder().numero(0L).build());

        Proposta proposta = Proposta.builder()
            .estado(stateMachine.getState().getId().name())
            .numero(ultimaProposta.getNumero() + 1)
            .cpf(cpf).build();

        Proposta propostaSalva = repository.save(proposta);

        Long numeroProposta = propostaSalva.getNumero();

        stateMachine.getExtendedState().getVariables().put("cpf", cpf);
        stateMachine.getExtendedState().getVariables().put("numeroProposta", numeroProposta);
        stateMachine.getExtendedState().getVariables().put("proposta", propostaSalva);

        return propostaSalva;
    }
}
