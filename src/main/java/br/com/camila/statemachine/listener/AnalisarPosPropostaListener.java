package br.com.camila.statemachine.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.message.AnalisarPosPropostaMessage;
import br.com.camila.statemachine.messaging.Messaging;
import br.com.camila.statemachine.service.CustomStateMachineService;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitListener(queues = Messaging.QUEUE_ANALISAR_POS_PROPOSTA)
@Slf4j
public class AnalisarPosPropostaListener {

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @RabbitHandler
    void receive(
        @Payload final AnalisarPosPropostaMessage message) {

        log.info("Mensagem: {}", message);

        customStateMachineService.sendEvent(message.getNumeroProposta(), Eventos.ANALISAR);
    }
}
