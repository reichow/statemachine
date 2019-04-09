package br.com.statemachine.listener;

import static br.com.statemachine.domain.Eventos.ANALISAR;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

import br.com.statemachine.annotation.EventTemplate;
import br.com.statemachine.annotation.RabbitEnabled;
import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import br.com.statemachine.event.AnalisarPrePropostaEvent;
import br.com.statemachine.message.AnalisarPrePropostaMessage;
import br.com.statemachine.messaging.Messaging;
import br.com.statemachine.response.ErrorResponse;
import br.com.statemachine.service.AnalisarPrePropostaService;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitEnabled
@RabbitListener(queues = Messaging.QUEUE_ANALISAR_PRE_PROPOSTA)
@Slf4j
public class AnalisarPrePropostaListener {

    @Autowired
    private AnalisarPrePropostaService service;

    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

    @RabbitHandler
    void receive(
        @Payload final AnalisarPrePropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        Estados estado = stateMachine.getState().getId();

        stateMachine.sendEvent(ANALISAR);

        estado = stateMachine.getState().getId();
        log.info("Mensagem: {}", message);

        final AnalisarPrePropostaEvent.AnalisarPrePropostaEventBuilder event = AnalisarPrePropostaEvent.builder()
            .requisicao(message);

        try {

//            final Boolean response = service.executar();

//            event.resultado(response);

            log.info("Propagando evento: {}", event);

//            if (response) {
//                rabbitTemplate.convertAndSend(Messaging.ANALISE_APROVADA.getRoutingKey(), event.build());
//            } else {
//                rabbitTemplate.convertAndSend(Messaging.ANALISE_NEGADA.getRoutingKey(), event.build());
//            }


        } catch (final Exception e) {

            event.erro(ErrorResponse.build(e));

            log.error("Propagando evento de erro: " + event);
            rabbitTemplate.convertAndSend(Messaging.ANALISE_PRE_PROPOSTA_ERROR.getRoutingKey(), event.build());
            throw e;
        }
    }

}
