package br.com.camila.statemachine.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.message.AnalisarPosPropostaMotorMessage;
import br.com.camila.statemachine.messaging.Messaging;

@Service
@EnableRabbit
@Slf4j
public class AnalisarPosPropostaService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar() {

        AnalisarPosPropostaMotorMessage message = AnalisarPosPropostaMotorMessage.builder().build();

        eventTemplate.convertAndSend(
            Messaging.ANALISAR_POS_PROPOSTA_MOTOR.getRoutingKey(),
            Messaging.ANALISAR_POS_PROPOSTA_MOTOR.getExchange(),
            message);
    }
}
