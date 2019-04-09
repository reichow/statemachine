package br.com.statemachine.config;

import br.com.statemachine.annotation.RabbitEnabled;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@RabbitEnabled
@Import(RabbitAutoConfiguration.class)
public class RabbitConfiguration {

}
