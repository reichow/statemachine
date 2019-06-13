package br.com.camila.statemachine.statemachine.teste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;

@Service
public class StateMachinePolitica1 implements ContratoStateMachine {

    @Autowired
    @CustomStateMachine(Politica.POLITICA_1)
    private StateMachineFactory<Estados, Eventos> stateMachineFactory;

    @Override
    public StateMachine<Estados, Eventos> buscar() {

        return stateMachineFactory.getStateMachine(TipoProposta.CONTRATACAO_CCR.name());
    }
}
