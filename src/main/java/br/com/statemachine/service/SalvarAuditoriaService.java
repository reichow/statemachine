package br.com.statemachine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.entity.Auditoria;
import br.com.statemachine.repository.AuditoriaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalvarAuditoriaService {

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

    @Autowired
    private AuditoriaRepository repository;

    public void executar() {

        Auditoria auditoria = Auditoria.builder()
            .cpf(stateMachine.getExtendedState().get("cpf", String.class))
            .data("data")
            .duracao("duracao")
            .numeroProposta(stateMachine.getExtendedState().get("numeroProposta", Long.class))
            .estado(stateMachine.getState().getId().name()).build();

        log.info("Inicia auditoria para: [{}]", stateMachine.getState().getId().name(), stateMachine.getExtendedState().get("numeroProposta", Long.class));
        repository.save(auditoria);
        log.info("Finaliza auditoria para: [{}]", stateMachine.getState().getId().name(), stateMachine.getExtendedState().get("numeroProposta", Long.class));


    }
}
