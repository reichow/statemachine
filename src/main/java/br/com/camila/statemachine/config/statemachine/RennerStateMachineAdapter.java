package br.com.camila.statemachine.config.statemachine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;
import lombok.SneakyThrows;

public abstract class RennerStateMachineAdapter extends StateMachineConfigurerAdapter<Estados, Eventos> {

    private final TipoProposta tipo;

    RennerStateMachineAdapter(TipoProposta tipo){
        this.tipo = tipo;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<Estados, Eventos> config) throws Exception {
        config.withConfiguration()
                .machineId(tipo.name());
    }

    @Override
    public void configure(StateMachineStateConfigurer<Estados, Eventos> states) throws Exception {
        states
            .withStates()
            .initial(Estados.PROPOSTA_CRIADA)
            .states(new HashSet<>(Arrays.asList(Estados.EM_ANALISE, Estados.PROPOSTA_CRIADA, Estados.APROVADO, Estados.NEGADO)))
            .end(Estados.APROVADO)
            .end(Estados.NEGADO)
            .and()
            .withStates()
            .parent(Estados.EM_ANALISE)
            .initial(getInitialSubmachineState())
            .states(getSubMachineStates());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Estados, Eventos> transitions) throws Exception {
        transitions.withExternal()
            .source(Estados.PROPOSTA_CRIADA).target(Estados.EM_ANALISE).event(Eventos.ANALISAR);

        getTransitions().stream().forEach((t)->buildTransition(t, transitions));


        getTransitionAprovado().stream()
            .map((t)->new RennerTransition<>(t, Estados.APROVADO, Eventos.APROVAR))
            .forEach((rt)->buildTransition(rt,transitions));

        getTransitionNegado().stream()
            .map((t)->new RennerTransition<>(t, Estados.NEGADO, Eventos.NEGAR))
            .forEach((rt)->buildTransition(rt,transitions));
    }

    @SneakyThrows
    private void buildTransition(final RennerTransition<Estados, Eventos> transition, StateMachineTransitionConfigurer<Estados, Eventos> transitions){
        transitions.withExternal().event(transition.getEvent())
            .source(transition.getSource())
            .target(transition.getTarget());
    }

    abstract Set<RennerTransition<Estados, Eventos>> getTransitions();

    abstract Set<Estados> getSubMachineStates();
    abstract Estados getInitialSubmachineState();

    abstract Set<Estados> getTransitionAprovado();
    abstract Set<Estados> getTransitionNegado();

}
