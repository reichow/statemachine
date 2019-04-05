package br.com.rabbit.exemplo.demo.config;

import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.rabbit.exemplo.demo.annotations.RabbitEnabled;

@Configuration
@RabbitEnabled
@Import(RabbitAutoConfiguration.class)
public class RabbitConfiguration {

}
