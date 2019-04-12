package br.com.camila.statemachine.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Documented
@Inherited
@ConditionalOnProperty("spring.rabbitmq.enabled")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RabbitEnabled {

}

