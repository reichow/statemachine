package br.com.rabbit.exemplo.demo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.rabbit.exemplo.demo.ErrorResponse;
import br.com.rabbit.exemplo.demo.annotations.EventTemplate;
import br.com.rabbit.exemplo.demo.annotations.RabbitEnabled;
import br.com.rabbit.exemplo.demo.event.AnalisarPrePropostaEvent;
import br.com.rabbit.exemplo.demo.message.AnalisarPrePropostaMessage;
import br.com.rabbit.exemplo.demo.messaging.Messaging;
import br.com.rabbit.exemplo.demo.service.AnalisarPrePropostaService;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitEnabled
@RabbitListener(queues = Messaging.QUEUE_ANALISAR_PRE_PROPOSTA)
@Slf4j
public class PreAnaliseListener {

    @Autowired
    private AnalisarPrePropostaService service;

    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    void receive(
        @Payload final AnalisarPrePropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        //stateMachine.sendEvent(ANALISAR);

        log.info("Mensagem: {}", message);

        final AnalisarPrePropostaEvent.AnalisarPrePropostaEventBuilder event = AnalisarPrePropostaEvent.builder()
            .requisicao(message);

        try {

            final Boolean response = service.executar();

            event.resultado(response);

            log.info("Propagando evento: {}", event);

            if (response) {
                rabbitTemplate.convertAndSend(Messaging.ANALISE_APROVADA.getRoutingKey(), event.build());
            } else {
                rabbitTemplate.convertAndSend(Messaging.ANALISE_NEGADA.getRoutingKey(), event.build());
            }


        } catch (final Exception e) {

            event.erro(ErrorResponse.build(e));

            log.error("Propagando evento de erro: " + event);
            rabbitTemplate.convertAndSend(Messaging.ANALISE_PRE_PROPOSTA_ERROR.getRoutingKey(), event.build());
            throw e;
        }
    }

}
