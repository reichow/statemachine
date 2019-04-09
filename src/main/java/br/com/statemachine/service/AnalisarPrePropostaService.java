package br.com.statemachine.service;

import br.com.statemachine.annotation.EventTemplate;
import br.com.statemachine.message.AnalisarPrePropostaMessage;
import br.com.statemachine.messaging.Messaging;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class AnalisarPrePropostaService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar() {

        AnalisarPrePropostaMessage proposta = AnalisarPrePropostaMessage.builder().cpf("cop").build();

        eventTemplate.convertAndSend(
                Messaging.ANALISAR_PROPOSTA.getExchange(),
                Messaging.ANALISAR_PROPOSTA.getRoutingKey(),
                proposta);
    }
}
