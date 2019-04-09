package br.com.statemachine.config.statemachine;

import java.util.EnumSet;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<Estados, Eventos> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<Estados, Eventos> config) throws Exception {
        config.withConfiguration()
                .listener(new TransitionConfig())
                .autoStartup(true);
    }

    @Override
    public void configure(StateMachineStateConfigurer<Estados, Eventos> states) throws Exception {

        states
                .withStates()
                .initial(Estados.PROPOSTA_INEXISTENTE)
                .states(EnumSet.allOf(Estados.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Estados, Eventos> transitions) throws Exception {
        transitions
                .withExternal()
                .source(Estados.PROPOSTA_INEXISTENTE)
                .target(Estados.PROPOSTA_CRIADA)
                .event(Eventos.INICIAR)
                .action(
                        context -> {

                        }
                )
                .and()

                .withExternal()
                .source(Estados.PROPOSTA_CRIADA)
                .target(Estados.ANALISE_PRE)
                .event(Eventos.ANALISAR)
                .action(
                        context -> {
                            // chamar serviço de motor - fazer uma análise
                            // esse serviço irá retornar o novo evento;

                        }
                )
                .and()

                .withExternal()
                .source(Estados.ANALISE_PRE)
                .target(Estados.APROVADO_PRE)
                .event(Eventos.APROVAR)
                .action(
                        context -> {
                            // vai chamar evento de atualizar
                        }
                )
                .and()

                // evento negar é final
                .withExternal()
                .source(Estados.ANALISE_PRE)
                .target(Estados.NEGADO_PRE)
                .event(Eventos.NEGAR)
                .and()

                .withExternal()
                .source(Estados.APROVADO_PRE)
                .target(Estados.INFOS_PESSOAIS)
                .event(Eventos.ATUALIZAR)
                .action(
                        context -> {
                            // atualiza os dados - infos pessoais
                            // chama o evento de atualizar de novo, que agora vai para o email;
                        }
                )
                .and()

                .withExternal()
                .source(Estados.INFOS_PESSOAIS)
                .target(Estados.EMAIL_VALIDADO)
                .event(Eventos.ATUALIZAR)
                .action(
                        context -> {
                            // com os dados atualizados, vamos validar o email;
                            // após validar o email, chama evento de análise
                        }
                )
                .and()

                .withExternal()
                .source(Estados.EMAIL_VALIDADO)
                .target(Estados.ANALISE_POS)
                .event(Eventos.ANALISAR)
                .action(
                        context -> {
                            // chama 2 motor para próxima análise;
                            // essa análise retorna o próximo evento;
                            // context.getStateMachine().sendEvent();
                        }
                )
                .and()

                .withExternal()
                .source(Estados.ANALISE_POS)
                .target(Estados.NEGADO_POS)
                .event(Eventos.NEGAR)
                .and()

                .withExternal()
                .source(Estados.ANALISE_POS)
                .target(Estados.APROVADO_POS)
                .event(Eventos.APROVAR);
    }

}
