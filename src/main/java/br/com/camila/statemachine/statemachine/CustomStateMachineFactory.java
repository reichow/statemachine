package br.com.camila.statemachine.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;

@Component
public class CustomStateMachineFactory {

    @Autowired
    @Qualifier("CONTRATACAO_CCR")
    public StateMachineFactory<Estados, Eventos> contratacaoCCR;

    @Autowired
    @Qualifier("CONTRATACAO_MC")
    public StateMachineFactory<Estados, Eventos> contratacaoMC;
}
