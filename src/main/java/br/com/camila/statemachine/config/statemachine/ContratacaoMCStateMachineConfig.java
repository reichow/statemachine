package br.com.camila.statemachine.config.statemachine;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.TipoProposta;
import br.com.camila.statemachine.service.captacaomc.AnalisarPrePropostaMcService;

@Configuration
@EnableStateMachineFactory(name = "CONTRATACAO_MC")
public class ContratacaoMCStateMachineConfig extends EnumStateMachineConfigurerAdapter<Estados, Eventos> {

    @Autowired
    private AnalisarPrePropostaMcService analisarPrePropostaMcService;

    @Override
    public void configure(StateMachineConfigurationConfigurer<Estados, Eventos> config) throws Exception {
        config.withConfiguration()
            .machineId(TipoProposta.CONTRATACAO_MC.name());
    }

    @Override
    public void configure(StateMachineStateConfigurer<Estados, Eventos> states) throws Exception {

        states
            .withStates()
            .initial(Estados.PROPOSTA_INEXISTENTE)
            .end(Estados.NEGADO_PRE)
            .states(EnumSet.allOf(Estados.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Estados, Eventos> transitions) throws Exception {
        transitions

            .withExternal()
            .source(Estados.PROPOSTA_INEXISTENTE)
            .target(Estados.PROPOSTA_CRIADA)
            .event(Eventos.INICIAR)

            .and()
            .withExternal()
            .source(Estados.PROPOSTA_CRIADA)
            .target(Estados.ANALISE_PRE)
            .event(Eventos.ANALISAR)
            .action(
                context -> {
                    analisarPrePropostaMcService.executar(
                        context.getExtendedState().get("numeroProposta", Long.class),
                        context.getExtendedState().get("cpf", String.class));
                }
            )

            .and()
            .withExternal()
            .source(Estados.ANALISE_PRE)
            .target(Estados.APROVADO_PRE)
            .event(Eventos.APROVAR)

            .and()
            .withExternal()
            .source(Estados.ANALISE_PRE)
            .target(Estados.NEGADO_PRE)
            .event(Eventos.NEGAR)

            .and()
            .withExternal()
            .source(Estados.ANALISE_PRE)
            .target(Estados.PENDENTE_PRE)
            .event(Eventos.AGUARDAR);
    }
}
