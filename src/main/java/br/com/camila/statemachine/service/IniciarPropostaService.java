package br.com.camila.statemachine.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.message.CriarPropostaMessage;
import br.com.camila.statemachine.messaging.Messaging;

@Service
@EnableRabbit
public class IniciarPropostaService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar(String cpf) {

        CriarPropostaMessage novaProposta = CriarPropostaMessage.builder().cpf(cpf).build();

        eventTemplate.convertAndSend(
            Messaging.CRIAR_PROPOSTA.getExchange(),
            Messaging.CRIAR_PROPOSTA.getRoutingKey(),
            novaProposta);
    }
}
