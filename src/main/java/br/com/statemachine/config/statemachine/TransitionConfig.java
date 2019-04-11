package br.com.statemachine.config.statemachine;

import static java.util.Objects.nonNull;

import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransitionConfig extends StateMachineListenerAdapter<Estados, Eventos> {

    @Override
    public void stateChanged(State<Estados, Eventos> from, State<Estados, Eventos> to) {
        log.info("Transição do estado: {}, {}", nonNull(from) ? from.getId().name() : "SEM_ESTADO", nonNull(to) ? to.getId().name() : "-");
    }
}
