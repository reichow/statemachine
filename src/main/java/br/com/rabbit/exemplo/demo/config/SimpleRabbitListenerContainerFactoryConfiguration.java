package br.com.rabbit.exemplo.demo.config;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.aopalliance.aop.Advice;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.rabbit.exemplo.demo.annotations.RabbitEnabled;
import br.com.rabbit.exemplo.demo.interceptor.HeaderMessageInterceptor;
import br.com.rabbit.exemplo.demo.interceptor.ListenerExceptionInterceptor;
import br.com.rabbit.exemplo.demo.interceptor.TraceMessageInterceptor;

@Configuration
@RabbitEnabled
public class SimpleRabbitListenerContainerFactoryConfiguration {

    @Autowired
    private SimpleRabbitListenerContainerFactory listenerFactory;

    @Autowired
    private HeaderMessageInterceptor headerMessageInterceptor;

    @Autowired
    private TraceMessageInterceptor traceMessageInterceptor;

    @Autowired
    private ListenerExceptionInterceptor listenerExceptionInterceptor;

    @PostConstruct
    void simpleRabbitListenerContainerFactorySetup() {

        final Advice[] advices = ofNullable(listenerFactory.getAdviceChain()).orElse(new Advice[0]);

        final List<Advice> chain = stream(advices).collect(toList());
        chain.add(headerMessageInterceptor);
        chain.add(traceMessageInterceptor);
        chain.add(listenerExceptionInterceptor);

        listenerFactory.setAdviceChain(chain.toArray(new Advice[0]));
    }
}
