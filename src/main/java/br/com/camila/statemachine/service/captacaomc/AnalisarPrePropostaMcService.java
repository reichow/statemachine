package br.com.camila.statemachine.service.captacaomc;

import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.message.AnalisarPrePropostaMcMotorMessage;
import br.com.camila.statemachine.messaging.Messaging;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnalisarPrePropostaMcService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar(final Long numeroProposta, final String cpf) {

        AnalisarPrePropostaMcMotorMessage message = AnalisarPrePropostaMcMotorMessage.builder()
            .cpf(cpf)
            .numeroProposta(numeroProposta).build();

        log.info("Analisa pré proposta número {}, contratacao_mc.", numeroProposta);
        eventTemplate.convertAndSend(
            Messaging.ANALISAR_PRE_PROPOSTA_MC_MOTOR.getExchange(),
            Messaging.ANALISAR_PRE_PROPOSTA_MC_MOTOR.getRoutingKey(),
            message);
    }

    private String definirEstado() {
        Random random = new Random();
        Estados estado = Estados.values()[random.nextInt(Estados.values().length)];
        return estado.toString();
    }

    enum Estados {
        APROVADO_PRE,
        NEGADO_PRE,
        PENDENTE_PRE
    }
}
