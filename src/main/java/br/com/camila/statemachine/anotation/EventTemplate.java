package br.com.camila.statemachine.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Anotação para RabbitTemplate bean de eventos de conta totvs.
 */
@Documented
@Inherited
@Qualifier("proposta-events-rabbit-template")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface EventTemplate {

}

