package br.com.camila.statemachine.statemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineException;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptor;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.service.SalvarAuditoriaService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomStateMachineInterceptor extends AbstractStateMachineContextBuilder<Estados, Eventos> implements StateMachineInterceptor<Estados, Eventos> {

    @Autowired
    private CustomStateMachinePersist persist;

    @Autowired
    private SalvarAuditoriaService salvarAuditoriaService;


    @Override
    public Message<Eventos> preEvent(final Message<Eventos> message, final StateMachine<Estados, Eventos> stateMachine) {
        return message;
    }

    @Override
    public void preStateChange(final State<Estados, Eventos> state, final Message<Eventos> message,
        final Transition<Estados, Eventos> transition, final StateMachine<Estados, Eventos> stateMachine) {
    }

    @Override
    public void postStateChange(final State<Estados, Eventos> state, final Message<Eventos> message,
        final Transition<Estados, Eventos> transition, final StateMachine<Estados, Eventos> stateMachine) {

        if (state != null && transition != null) {
            try {

                //TODO verificar se existe a proposta salva antes;

                log.info("Iniciada persistência da SM.");
                final Long id = stateMachine.getExtendedState().get("numeroProposta", Long.class);
                persist.write(buildStateMachineContext(stateMachine), id.toString());
                log.info("Finalizada persistência da SM.");

                salvarAuditoriaService.executar(id);

            } catch (Exception e) {
                throw new StateMachineException("Não foi possível persistir o contexto da SM.", e);
            }
        }
    }

    @Override
    public StateContext<Estados, Eventos> preTransition(StateContext<Estados, Eventos> stateContext) {
        return stateContext;
    }

    @Override
    public StateContext<Estados, Eventos> postTransition(StateContext<Estados, Eventos> stateContext) {
        return stateContext;
    }

    @Override
    public Exception stateMachineError(StateMachine<Estados, Eventos> stateMachine, Exception exception) {
        return exception;
    }

}
