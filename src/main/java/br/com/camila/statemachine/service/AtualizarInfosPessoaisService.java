package br.com.camila.statemachine.service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;

@Service
@EnableRabbit
public class AtualizarInfosPessoaisService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar() {

        // vai na processadora e atualiza os dados;
    }
}
