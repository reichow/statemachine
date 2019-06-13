package br.com.camila.statemachine.config.statemachine;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableStateMachineFactory(name = "TESTE")
//uma politica state machine
public class TestSubMachineConfig extends RennerStateMachineAdapter {

    TestSubMachineConfig(){
        super(TipoProposta.TESTE);
    }

    @Override
    Set<Estados> getSubMachineStates() {
        return new HashSet<>(Arrays.asList(Estados.ANALISE_PRE, Estados.ANALISE_POS, Estados.APROVADO_PRE, Estados.APROVADO_POS));
    }

    @Override
    Estados getInitialSubmachineState() {
        return Estados.ANALISE_PRE;
    }

    @Override
    Set<Estados> getTransitionAprovado() {
        return new HashSet<>(Arrays.asList(Estados.ANALISE_POS));
    }

    @Override
    Set<Estados> getTransitionNegado() {
        return new HashSet<>(Arrays.asList(Estados.ANALISE_POS,Estados.ANALISE_PRE));
    }

    @Override
    public Set<RennerTransition<Estados, Eventos>> getTransitions(){
        Set<RennerTransition<Estados, Eventos>> transitions = new HashSet<>();

        transitions.add(new RennerTransition<>(Estados.ANALISE_PRE, Estados.APROVADO_PRE, Eventos.APROVAR));
        transitions.add(new RennerTransition<>(Estados.APROVADO_PRE, Estados.ANALISE_POS, Eventos.ANALISAR));

        return transitions;
    }


    //metodos para retornar guards
}
