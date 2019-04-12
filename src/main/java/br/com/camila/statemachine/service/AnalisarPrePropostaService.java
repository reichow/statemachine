package br.com.camila.statemachine.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.message.AnalisarPrePropostaMotorMessage;
import br.com.camila.statemachine.messaging.Messaging;

@Service
@EnableRabbit
public class AnalisarPrePropostaService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar() {

        //esse método vai enviar os dados de um cliente para ser analisado pelo motor;

        AnalisarPrePropostaMotorMessage message = AnalisarPrePropostaMotorMessage.builder().build();

        eventTemplate.convertAndSend(Messaging.ANALISAR_PRE_PROPOSTA_MOTOR.getRoutingKey(), Messaging.ANALISAR_PRE_PROPOSTA_MOTOR.getExchange(), message);
    }
}
