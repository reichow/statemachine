package br.com.camila.statemachine.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.entity.Proposta;
import br.com.camila.statemachine.message.CriarPropostaMessage;
import br.com.camila.statemachine.messaging.Messaging;
import br.com.camila.statemachine.response.PropostaDTO;
import br.com.camila.statemachine.service.CriarPropostaService;
import br.com.camila.statemachine.statemachine.AbstractStateMachineContextBuilder;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitListener(queues = Messaging.QUEUE_CRIAR_PROPOSTA)
@Slf4j
public class CriarPropostaListener extends AbstractStateMachineContextBuilder<Estados, Eventos> {

    @Autowired
    @EventTemplate
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CriarPropostaService criarPropostaService;

    @RabbitHandler
    void receive(@Payload final CriarPropostaMessage message, @Headers final MessageHeaders headers) {

        log.info("Mensagem: {}", message);

//        final CriarPropostaEvent.CriarPropostaEventBuilder event = CriarPropostaEvent.builder().requisicao(message);

        try {

            log.info("Inicia persistência da proposta para o cpf: {}", message.getCpf());
            Proposta proposta = criarPropostaService.executar(message.getCpf(), message.getProposta());
            log.info("Finaliza persistência da proposta para o cpf: {}", message.getCpf());

            PropostaDTO propostaDTO = PropostaDTO.builder()
                .estado(proposta.getEstado())
                .numero(proposta.getNumero())
                .cpf(proposta.getCpf()).build();

//            event.resultado(propostaDTO);

//            log.info("Propagando evento: {}", event);
//            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA.getRoutingKey(), event.build());

        } catch (final Exception e) {

//            event.erro(ErrorResponse.build(e));

//            log.error("Propagando evento de erro: " + event);
//            rabbitTemplate.convertAndSend(Messaging.PROPOSTA_CRIADA_ERROR.getRoutingKey(), event.build());
            throw e;
        }
    }
}
