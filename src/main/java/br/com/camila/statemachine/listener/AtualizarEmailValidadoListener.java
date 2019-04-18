package br.com.camila.statemachine.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.domain.Eventos;
import br.com.camila.statemachine.message.AtualizarEmailValidadoMessage;
import br.com.camila.statemachine.messaging.Messaging;
import br.com.camila.statemachine.statemachine.CustomStateMachineService;
import br.com.camila.statemachine.statemachine.AbstractStateMachineContextBuilder;
import lombok.extern.slf4j.Slf4j;

@Component
@RabbitListener(queues = Messaging.QUEUE_ATUALIZAR_EMAIL_VALIDADO)
@Slf4j
public class AtualizarEmailValidadoListener extends AbstractStateMachineContextBuilder<Estados, Eventos> {

    @Autowired
    private CustomStateMachineService customStateMachineService;

    @RabbitHandler
    void receive(@Payload final AtualizarEmailValidadoMessage message) {
        log.info("Mensagem: {}", message);
        log.info("Enviando evento {} para StateMachine.", Eventos.ATUALIZAR);

        customStateMachineService.sendEvent(message.getNumeroProposta(), Eventos.ATUALIZAR, message.getProposta());
    }
}
