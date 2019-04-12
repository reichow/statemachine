package br.com.camila.statemachine.config;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.camila.statemachine.annotation.RabbitEnabled;


@Configuration
@RabbitEnabled
@Import(RabbitAutoConfiguration.class)
public class RabbitConfiguration {

}
