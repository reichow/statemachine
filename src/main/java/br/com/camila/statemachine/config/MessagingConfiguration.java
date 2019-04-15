package br.com.camila.statemachine.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.camila.statemachine.annotation.RabbitEnabled;
import br.com.camila.statemachine.messaging.Messaging;

@Configuration
@RabbitEnabled
public class MessagingConfiguration {

    /**
     * Exchanges
     **/

    @Bean
    TopicExchange propostaExchange() {
        return new TopicExchange(Messaging.EXCHANGE);
    }

    @Bean
    TopicExchange eventsExchange() {
        return new TopicExchange(Messaging.EXCHANGE_EVENTS);
    }

    @Bean
    TopicExchange eventsExchangeMotor() {
        return new TopicExchange(Messaging.EXCHANGE_EVENTS_MOTOR);
    }

    @Bean
    TopicExchange eventsExchangeProcessadora() {
        return new TopicExchange(Messaging.EXCHANGE_PROCESSADORA);
    }

    /**
     * Queues
     **/

    @Bean
    Queue gfeQueue() {
        return new Queue(Messaging.QUEUE_GFE);
    }

    @Bean
    Queue criarPropostaQueue() {
        return new Queue(Messaging.QUEUE_CRIAR_PROPOSTA);
    }

    @Bean
    Queue analisarPrePropostaQueue() {
        return new Queue(Messaging.QUEUE_ANALISAR_PRE_PROPOSTA);
    }

    @Bean
    Queue atualizarInfosPessoaisQueue() {
        return new Queue(Messaging.QUEUE_ATUALIZAR_INFOS_PESSOAIS);
    }

    @Bean
    Queue atualizarEmailValidadoQueue() {
        return new Queue(Messaging.QUEUE_ATUALIZAR_EMAIL_VALIDADO);
    }

    @Bean
    Queue analisarPosPropostaQueue() {
        return new Queue(Messaging.QUEUE_ANALISAR_POS_PROPOSTA);
    }

    @Bean
    Queue prePropostaAnalisadaQueue() {
        return new Queue(Messaging.QUEUE_PRE_PROPOSTA_ANALISADA);
    }

    @Bean
    Queue infosPessoaisAtualizadasQueue() {
        return new Queue(Messaging.QUEUE_INFOS_PESSOAIS_ATUALIZADAS);
    }

    @Bean
    Queue emailValidadoAtualizadoQueue() {
        return new Queue(Messaging.QUEUE_EMAIL_VALIDADO_ATUALIZADO);
    }

    @Bean
    Queue porPropostaAnalisadaQueue() {
        return new Queue(Messaging.QUEUE_POS_PROPOSTA_ANALISADA);
    }

    /**
     * Bindings
     **/

    @Bean
    Binding gfeQueueToEventsExchangeBinder() {
        return BindingBuilder.bind(gfeQueue())
            .to(eventsExchange())
            .with(Messaging.GFE.getRoutingKey());
    }

    @Bean
    Binding criarPropostsQueueToPropostaExchangeBinder() {
        return BindingBuilder.bind(criarPropostaQueue())
            .to(propostaExchange())
            .with(Messaging.CRIAR_PROPOSTA.getRoutingKey());
    }

    @Bean
    Binding analisarPrePropostaQueueToPropostaExchangeBinder() {
        return BindingBuilder.bind(analisarPrePropostaQueue())
            .to(propostaExchange())
            .with(Messaging.ANALISAR_PRE_PROPOSTA.getRoutingKey());
    }

    @Bean
    Binding prePropostaAnalisadaQueueToPropostaExchangeBinder() {
        return BindingBuilder.bind(prePropostaAnalisadaQueue())
            .to(propostaExchange())
            .with(Messaging.PRE_PROPOSTA_ANALISADA_MOTOR2.getRoutingKey());
    }
}
