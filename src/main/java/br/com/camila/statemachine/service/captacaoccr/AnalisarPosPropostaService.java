package br.com.camila.statemachine.service.captacaoccr;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.message.AnalisarPosPropostaMotorMessage;
import br.com.camila.statemachine.messaging.Messaging;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableRabbit
@Slf4j
public class AnalisarPosPropostaService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar(Long numeroProposta, String cpf) {

        AnalisarPosPropostaMotorMessage message = AnalisarPosPropostaMotorMessage.builder()
            .cpf(cpf)
            .numeroProposta(numeroProposta).build();

        log.info("Envia análise da pós proposta de número {} para o motor.", numeroProposta);
        eventTemplate.convertAndSend(
            Messaging.ANALISAR_POS_PROPOSTA_MOTOR.getExchange(),
            Messaging.ANALISAR_POS_PROPOSTA_MOTOR.getRoutingKey(),
            message);
    }
}
