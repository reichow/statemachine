package br.com.camila.statemachine.service.captacaomc;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camila.statemachine.annotation.EventTemplate;
import br.com.camila.statemachine.domain.TipoProposta;
import br.com.camila.statemachine.message.AnalisarPrePropostaMotorMessage;
import br.com.camila.statemachine.messaging.Messaging;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnalisarPrePropostaMcService {

    @Autowired
    @EventTemplate
    private RabbitTemplate eventTemplate;

    public void executar(final Long numeroProposta, final String cpf) {

        AnalisarPrePropostaMotorMessage message = AnalisarPrePropostaMotorMessage.builder()
            .cpf(cpf)
            .numeroProposta(numeroProposta)
            .proposta(TipoProposta.CONTRATACAO_MC).build();

        log.info("Analisa pré proposta número {}, contratacao_mc.", numeroProposta);
        eventTemplate.convertAndSend(
            Messaging.ANALISAR_PRE_PROPOSTA_MOTOR.getExchange(),
            Messaging.ANALISAR_PRE_PROPOSTA_MOTOR.getRoutingKey(),
            message);
    }
}
