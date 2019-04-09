package br.com.statemachine.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.statemachine.annotation.EventTemplate;
import br.com.statemachine.annotation.RabbitEnabled;
import br.com.statemachine.event.CriarPropostaEvent;
import br.com.statemachine.message.CriarPropostaMessage;
import br.com.statemachine.messaging.Messaging;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitEnabled
@RabbitListener(queues = Messaging.QUEUE_CRIAR_PROPOSTA)
@Slf4j
public class CriarPropostaListener {

//    @Autowired
//    private StateMachine<Estados, Eventos> stateMachine;
//
    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;
//
//    @Autowired
//    private CriarPropostaService criarPropostaService;
//
//    @Autowired
//    private BuscarPropostaService buscarPropostaService;

    @RabbitHandler
    void receive(
        @Payload final CriarPropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        log.info("Mensagem: {}", message);

        final CriarPropostaEvent.CriarPropostaEventBuilder event = CriarPropostaEvent.builder().requisicao(message);

        try {

//            criarPropostaService.executar(message.getCpf());

//            Proposta proposta = buscarPropostaService.buscarPorNumeroProposta("numeroProposta");
//            PropostaDTO propostaDTO = PropostaDTO.builder().build();
//            event.resultado(propostaDTO);
//
//            log.info("Inicia envio do evento INICIAR. Estado: {}", stateMachine.getState().getId());
//            stateMachine.sendEvent(Eventos.INICIAR);
//            log.info("Finaliza envio do evento INICIAR. Estado: {}", stateMachine.getState().getId());
//
//            log.info("Propagando evento: {}", event);
//            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA.getRoutingKey(), event.build());

        } catch (final Exception e) {

//            event.erro(ErrorResponse.build(e));
//
//            log.error("Propagando evento de erro: " + event);
//            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA_ERROR.getRoutingKey(), event.build());
            throw e;
        }
    }
}
