package br.com.statemachine.listener;

import br.com.statemachine.annotation.EventTemplate;
import br.com.statemachine.annotation.RabbitEnabled;
import br.com.statemachine.message.CriarPropostaMessage;
import br.com.statemachine.messaging.Messaging;
import br.com.statemachine.service.AnalisarPrePropostaService;
import br.com.statemachine.service.CriarPropostaService;
import br.com.statemachine.domain.Estados;
import br.com.statemachine.domain.Eventos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

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

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

    @Autowired
    private AnalisarPrePropostaService analisarPrePropostaService;

    @RabbitHandler
    void receive(
        @Payload final CriarPropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        Estados estado = stateMachine.getState().getId();

        stateMachine.sendEvent(Eventos.INICIAR);

        Estados novoEstado = stateMachine.getState().getId();

        log.info("Mensagem: {}", message);

        analisarPrePropostaService.executar();

//        final CriarPropostaEvent.CriarPropostaEventBuilder event = CriarPropostaEvent.builder()
//            .requisicao(message);
//
//        try {
//
////            final String response = service.executar();
//
////            event.resultado(response);
//
//            log.info("Propagando evento: {}", event);
//            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA.getRoutingKey(), event.build());
//
//        } catch (final Exception e) {
//
//            event.erro(ErrorResponse.build(e));
//
//            log.error("Propagando evento de erro: " + event);
//            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA_ERROR.getRoutingKey(), event.build());
//            throw e;
//        }
//    }
    }
}
