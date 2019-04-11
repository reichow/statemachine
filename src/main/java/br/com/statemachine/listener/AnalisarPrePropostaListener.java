package br.com.statemachine.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Component;

import br.com.statemachine.annotation.EventTemplate;
import br.com.statemachine.annotation.RabbitEnabled;
import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.event.AnalisarPrePropostaEvent;
import br.com.statemachine.message.AnalisarPrePropostaMessage;
import br.com.statemachine.messaging.Messaging;
import br.com.statemachine.response.ErrorResponse;
import br.com.statemachine.service.CustomStateMachineService;
import br.com.statemachine.service.proposta.AnalisarPrePropostaService;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitEnabled
@RabbitListener(queues = Messaging.QUEUE_ANALISAR_PRE_PROPOSTA)
@Slf4j
public class AnalisarPrePropostaListener {

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @Autowired
    private AnalisarPrePropostaService service;

    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private StateMachineFactory<Estados, Eventos> factory;

    @RabbitHandler
    void receive(
        @Payload final AnalisarPrePropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        log.info("Mensagem: {}", message);

        customStateMachineService.sendEvent(message.getNumeroProposta(), Eventos.ANALISAR);

        final AnalisarPrePropostaEvent.AnalisarPrePropostaEventBuilder event = AnalisarPrePropostaEvent.builder()
            .requisicao(message);

        try {

            StateMachine<Estados, Eventos> stateMachine = customStateMachineService.getStateMachine(message.getNumeroProposta().toString());

            event.resultado(stateMachine.getExtendedState().get("response", Estados.class));

            log.info("Propagando evento: {}", event);

            rabbitTemplate.convertAndSend(Messaging.PRE_PROPOSTA_ANALISADA.getRoutingKey(), event.build());

            if (stateMachine.getExtendedState().get("response", Estados.class).equals(Estados.NEGADO_PRE)) {
                log.info("");
                customStateMachineService.sendEvent(stateMachine.getExtendedState().get("numeroProposta", Long.class), Eventos.NEGAR);
                log.info("");
            }

            if (stateMachine.getExtendedState().get("response", Estados.class).equals(Estados.APROVADO_PRE)) {
                log.info("");
                customStateMachineService.sendEvent(stateMachine.getExtendedState().get("numeroProposta", Long.class), Eventos.APROVAR);
                log.info("");
            }

        } catch (final Exception e) {

            event.erro(ErrorResponse.build(e));

            log.error("Propagando evento de erro: " + event);
            rabbitTemplate.convertAndSend(Messaging.ANALISE_PRE_PROPOSTA_ERROR.getRoutingKey(), event.build());
            throw e;
        }
    }

}
