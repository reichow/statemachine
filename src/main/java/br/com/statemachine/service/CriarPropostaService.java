package br.com.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.entity.Proposta;

@Service
public class CriarPropostaService {

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

//    @Autowired
//    private PropostaRepository repository;

    public void executar(String cpf) {

        String numeroProposta = "numeroProposta";

        Proposta proposta = new Proposta();
//            Proposta.builder().estado(stateMachine.getState().getId().name()).prop(numeroProposta).prop2(numeroProposta).build();

//        repository.save(proposta);

        stateMachine.getExtendedState().getVariables().put("numeroProposta", String.class);
    }
}
