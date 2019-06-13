package br.com.camila.statemachine.statemachine.teste;

import org.springframework.statemachine.StateMachine;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;

public interface ContratoStateMachine {

    StateMachine<Estados, Eventos> buscar();

}
