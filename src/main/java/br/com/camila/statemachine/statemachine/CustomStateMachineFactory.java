package br.com.camila.statemachine.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import br.com.camila.statemachine.annotation.StateMachineFactoryQualifier;
import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;

@Component
public abstract class CustomStateMachineFactory {

    @Autowired
    @StateMachineFactoryQualifier(TipoProposta.CONTRATACAO_CCR)
    protected StateMachineFactory<Estados, Eventos> contratacaoCCR;

    @Autowired
    @StateMachineFactoryQualifier(TipoProposta.CONTRATACAO_MC)
    protected StateMachineFactory<Estados, Eventos> contratacaoMC;
}
