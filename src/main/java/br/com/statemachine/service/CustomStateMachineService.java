package br.com.statemachine.service;

import static java.util.Objects.nonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

import br.com.statemachine.config.statemachine.TransitionConfig;
import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.repository.StateMachineRepository;
import br.com.statemachine.statemachine.CustomStateMachineInterceptor;
import br.com.statemachine.statemachine.CustomStateMachinePersist;

@Service
public class CustomStateMachineService {

    @Autowired
    private StateMachineFactory<Estados, Eventos> factory;

    @Autowired
    private StateMachineRepository repository;

    @Autowired
    private CustomStateMachinePersist persist;

    @Autowired
    private CustomStateMachineInterceptor interceptor;

    private Map<String, StateMachine<Estados, Eventos>> cache = new ConcurrentHashMap<>();

    public void start(final Long numeroProposta) {
        final StateMachine<Estados, Eventos> stateMachine = getStateMachine(numeroProposta.toString());
        stateMachine.getExtendedState().getVariables().put("numeroProposta", numeroProposta);
        stateMachine.start();
    }

    public String getState(final Long numeroProposta) {
        final StateMachine<Estados, Eventos> stateMachine = getStateMachine(numeroProposta.toString());
        return stateMachine.getState().getId().name();
    }

    public void setVariable(final Long numeroProposta, final String name, final Object value) {
        final StateMachine<Estados, Eventos> stateMachine = getStateMachine(numeroProposta.toString());
        stateMachine.getExtendedState().getVariables().put(name, value);
    }

    public void setVariables(final Long numeroProposta, final Map<String, Object> variables) {
        final StateMachine<Estados, Eventos> stateMachine = getStateMachine(numeroProposta.toString());
        stateMachine.getExtendedState().getVariables().putAll(variables);
    }

    public void sendEvent(final Long numeroProposta, final Eventos evento) {
        final String id = numeroProposta.toString();
        final StateMachine<Estados, Eventos> stateMachine = getStateMachine(id);
        stateMachine.sendEvent(evento);
    }

    public StateMachine getStateMachine(final String id) {
        if (cache.containsKey(id)) {
            return cache.get(id);
        }

        final StateMachine<Estados, Eventos> stateMachine = factory.getStateMachine();
        stateMachine.getStateMachineAccessor().withRegion().addStateMachineInterceptor(interceptor);
        stateMachine.addStateListener(new TransitionConfig());

        cache.put(id, stateMachine);

        if (nonNull(repository.findByIdMaquina(id))) {
            restoreStateMachine(stateMachine, id);
            stateMachine.start();
        }

        return stateMachine;
    }

    private void restoreStateMachine(StateMachine<Estados, Eventos> stateMachine, final String id) {
        try {
            final StateMachineContext<Estados, Eventos> context = persist.read(id);

            stateMachine.getStateMachineAccessor().doWithAllRegions(
                    function -> function.resetStateMachine(context));

        } catch (Exception ex) {
            throw new IllegalArgumentException("StateMachine ausente", ex);
        }
    }
}
