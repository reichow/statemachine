package br.com.statemachine.listener;

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
import br.com.statemachine.entity.Proposta;
import br.com.statemachine.event.CriarPropostaEvent;
import br.com.statemachine.message.CriarPropostaMessage;
import br.com.statemachine.messaging.Messaging;
import br.com.statemachine.persistency.AbstractStateMachineContextBuilder;
import br.com.statemachine.response.ErrorResponse;
import br.com.statemachine.response.PropostaDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitEnabled
@RabbitListener(queues = Messaging.QUEUE_CRIAR_PROPOSTA)
@Slf4j
public class CriarPropostaListener extends AbstractStateMachineContextBuilder<Estados, Eventos> {

    @Autowired
    private StateMachine<Estados, Eventos> stateMachine;

    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    void receive(
        @Payload final CriarPropostaMessage message,
        @Headers final MessageHeaders headers) throws Exception {

        log.info("Mensagem: {}", message);

        final CriarPropostaEvent.CriarPropostaEventBuilder event = CriarPropostaEvent.builder().requisicao(message);

        try {
            log.info("Inicia envio do evento INICIAR. Estado: {}", stateMachine.getState().getId());
            stateMachine.sendEvent(Eventos.INICIAR);
            log.info("Finaliza envio do evento INICIAR. Estado: {}", stateMachine.getState().getId());

//            Proposta propostaSalva = criarPropostaService.executar(message.getCpf());

            Proposta propostaSalva = stateMachine.getExtendedState().get("proposta", Proposta.class);

            PropostaDTO propostaDTO = PropostaDTO.builder()
                .estado(propostaSalva.getEstado())
                .numero(propostaSalva.getNumero())
                .cpf(propostaSalva.getCpf()).build();

            event.resultado(propostaDTO);

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
