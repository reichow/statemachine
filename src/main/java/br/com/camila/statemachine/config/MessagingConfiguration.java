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
    Queue analisarPropostaQueue() {
        return new Queue(Messaging.QUEUE_ANALISAR_PRE_PROPOSTA);
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
    Binding analisarPropostaQueueToPropostaExchangeBinder() {
        return BindingBuilder.bind(analisarPropostaQueue())
            .to(propostaExchange())
            .with(Messaging.ANALISAR_PRE_PROPOSTA.getRoutingKey());
    }
}
