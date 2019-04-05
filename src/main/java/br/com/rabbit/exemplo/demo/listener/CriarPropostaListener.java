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
import br.com.rabbit.exemplo.demo.event.CriarPropostaEvent;
import br.com.rabbit.exemplo.demo.message.CriarPropostaMessage;
import br.com.rabbit.exemplo.demo.messaging.Messaging;
import br.com.rabbit.exemplo.demo.service.CriarPropostaService;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitEnabled
@RabbitListener(queues = Messaging.QUEUE_CRIAR_PROPOSTA)
@Slf4j
public class CriarPropostaListener {

    @Autowired
    private CriarPropostaService service;

    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    void receive(
        @Payload final CriarPropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        log.info("Mensagem: {}", message);

        final CriarPropostaEvent.CriarPropostaEventBuilder event = CriarPropostaEvent.builder()
            .requisicao(message);

        try {

            final String response = service.executar();

            event.resultado(response);

            log.info("Propagando evento: {}", event);
            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA.getRoutingKey(), event.build());

        } catch (final Exception e) {

            event.erro(ErrorResponse.build(e));

            log.error("Propagando evento de erro: " + event);
            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA_ERROR.getRoutingKey(), event.build());
            throw e;
        }
    }
}
