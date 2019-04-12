package br.com.camila.statemachine.config.statemachine;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.service.AnalisarPosPropostaService;
import br.com.camila.statemachine.service.AnalisarPrePropostaService;
import br.com.camila.statemachine.service.AtualizarEmailValidadoService;
import br.com.camila.statemachine.service.AtualizarInfosPessoaisService;
import br.com.camila.statemachine.service.AtualizarPropostaService;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<Estados, Eventos> {

    @Autowired
    private AnalisarPrePropostaService analisarPrePropostaService;

    @Autowired
    private AtualizarPropostaService atualizarPropostaService;

    @Autowired
    private AtualizarInfosPessoaisService atualizarInfosPessoaisService;

    @Autowired
    private AtualizarEmailValidadoService atualizarEmailValidadoService;

    @Autowired
    private AnalisarPosPropostaService analisarPosPropostaService;

//    @Override
//    public void configure(StateMachineConfigurationConfigurer<Estados, Eventos> config) throws Exception {
//        config.withConfiguration();
//    }

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

            .and()
            .withExternal()
            .source(Estados.PROPOSTA_CRIADA)
            .target(Estados.ANALISE_PRE)
            .event(Eventos.ANALISAR)
            .action(
                context -> {
                    analisarPrePropostaService.executar();
                }
            )

            .and()
            .withExternal()
            .source(Estados.ANALISE_PRE)
            .target(Estados.APROVADO_PRE)
            .event(Eventos.APROVAR)
            .action(
                context -> atualizarPropostaService.executar(context.getExtendedState().get("numeroProposta", Long.class), context.getTarget().getId())
            )

            .and()
            .withExternal()
            .source(Estados.ANALISE_PRE)
            .target(Estados.NEGADO_PRE)
            .event(Eventos.NEGAR)
            .action(
                context -> atualizarPropostaService.executar(context.getExtendedState().get("numeroProposta", Long.class), context.getTarget().getId())
            )

            .and()
            .withExternal()
            .source(Estados.APROVADO_PRE)
            .target(Estados.INFOS_PESSOAIS)
            .event(Eventos.ATUALIZAR)
            .action(
                context -> atualizarInfosPessoaisService.executar()
            )

            .and()
            .withExternal()
            .source(Estados.INFOS_PESSOAIS)
            .target(Estados.EMAIL_VALIDADO)
            .event(Eventos.ATUALIZAR)
            .action(
                context -> {
                    atualizarEmailValidadoService.executar();
                }
            )

            .and()
            .withExternal()
            .source(Estados.EMAIL_VALIDADO)
            .target(Estados.ANALISE_POS)
            .event(Eventos.ANALISAR)
            .action(
                context -> {
                    analisarPosPropostaService.executar();
                }
            )

            .and()
            .withExternal()
            .source(Estados.ANALISE_POS)
            .target(Estados.NEGADO_POS)
            .event(Eventos.NEGAR)
            .action(
                context -> atualizarPropostaService.executar(context.getExtendedState().get("numeroProposta", Long.class), context.getTarget().getId())
            )

            .and()
            .withExternal()
            .source(Estados.ANALISE_POS)
            .target(Estados.APROVADO_POS)
            .action(
                context -> atualizarPropostaService.executar(context.getExtendedState().get("numeroProposta", Long.class), context.getTarget().getId())
            )
            .event(Eventos.APROVAR);
    }
}
