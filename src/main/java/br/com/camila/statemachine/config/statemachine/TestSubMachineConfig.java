package br.com.camila.statemachine.config.statemachine;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.Tipo;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachineFactory(name = "TESTE")
public class TestSubMachineConfig extends RennerStateMachineAdapter {

    TestSubMachineConfig(){
        super(Tipo.TESTE);
    }

    @Override
    public void configure(StateMachineStateConfigurer<Estados, Eventos> states) throws Exception {
        states
                .withStates()
                .initial(Estados.P_CRIADA)
                .states(new HashSet<>(Arrays.asList(Estados.EM_ANALISE, Estados.P_CRIADA, Estados.APROVADO, Estados.NEGADO)))
                .end(Estados.APROVADO)
                .end(Estados.NEGADO)
                .and()
                .withStates()
                    .parent(Estados.EM_ANALISE)
                    .initial(Estados.SUB_STATE_1)
                    .states(new HashSet<>(Arrays.asList(Estados.SUB_STATE_1, Estados.SUB_STATE_2)));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Estados, Eventos> transitions) throws Exception {
        transitions.withExternal()
            .source(Estados.P_CRIADA).target(Estados.EM_ANALISE).event(Eventos.ANALISAR)
            .and()
            .withExternal()
            .source(Estados.SUB_STATE_1).target(Estados.SUB_STATE_2).event(Eventos.ANALISAR)
            .and()
            .withExternal()
            .source(Estados.SUB_STATE_2).target(Estados.APROVADO).event(Eventos.APROVAR)
            .and()
            .withExternal()
            .source(Estados.SUB_STATE_2).target(Estados.APROVADO).event(Eventos.NEGAR);
    }
}
