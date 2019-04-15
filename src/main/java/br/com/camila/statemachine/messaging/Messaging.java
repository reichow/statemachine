package br.com.camila.statemachine.messaging;

/**
 * Interface para definição de nomes de exchanges, queues e routing keys.
 */
public interface Messaging {

    EventOutbox PROPOSTA_CRIADA = new EventOutbox("proposta.criar-proposta.success.event");
    EventInbox PRE_PROPOSTA_ANALISADA_MOTOR = new EventInbox("motor.analisar-pre-proposta.success.event");
    EventInbox INFOS_PESSOAIS_ATUALIZADAS_PROCESSADORA = new EventInbox("processadora.infos-pessoais-atualizadas-processadora.success.event");
    EventInbox EMAIL_VALIDADO_ATUALIZADO_PROCESSADORA = new EventInbox("processadora.email-validado-atualizado-processadora.success.event");
    EventInbox POS_PROPOSTA_ANALISADA_MOTOR = new EventInbox("motor.analisar-pos-proposta-motor.success.event");

    EventOutbox PROPOSTA_CRIADA_ERROR = new EventOutbox("proposta.criar-proposta.error.event");
    EventInbox ANALISE_PRE_PROPOSTA_MOTOR_ERROR = new EventInbox("motor.analise-pre-proposta.error.event");
    EventInbox INFOS_PESSOAIS_ATUALIZADAS_PROCESSADORA_EROOR = new EventInbox("processadora.infos-pessoais-atualizadas-processadora.error.event");
    EventInbox EMAIL_VALIDADO_ATUALIZADO_PROCESSADORA_ERROR = new EventInbox("processadora.email-validado-atualizado-processadora.error.event");
    EventInbox ANALISE_POS_PROPOSTA_MOTOR_ERROR = new EventInbox("motor.analise-pos-proposta.error.event");

    // mensagens que são recebidas para essa aplicação
    MessageInbox CRIAR_PROPOSTA = new MessageInbox("proposta.criar-proposta.message");
    MessageInbox ANALISAR_PRE_PROPOSTA = new MessageInbox("proposta.analisar-pre-proposta.message");
    MessageInbox ATUALIZAR_INFOS_PESSOAIS = new MessageInbox("proposta.atualizar-infos-pessoais.message");
    MessageInbox ATUALIZAR_EMAIL_VALIDADO = new MessageInbox("proposta.atualizar-email-validado.message");
    MessageInbox ANALISAR_POS_PROPOSTA = new MessageInbox("proposta.analisar-pos-proposta.message");

    MessageInbox PRE_PROPOSTA_ANALISADA_MOTOR2 = new MessageInbox("proposta.pre-proposta-analisada.message");

    // mensagens que são enviadas para outras aplicações
    MessageOutbox GFE = new MessageOutbox("gfe.armazenar-mensagem-gfe.message");
    MessageOutbox ANALISAR_PRE_PROPOSTA_MOTOR = new MessageOutbox("motor.analisar-pre-proposta-motor.message");
    MessageOutbox ATUALIZAR_INFOS_PESSOAIS_PROCESSADORA = new MessageOutbox("processadora.atualizar-infos-pessoais-processadora.message");
    MessageOutbox ATUALIZAR_EMAIL_VALIDADO_PROCESSADORA = new MessageOutbox("processadora.atualizar-email-validado-processadora.message");
    MessageOutbox ANALISAR_POS_PROPOSTA_MOTOR = new MessageOutbox("motor.analisar-pos-proposta-motor.message");

    String EXCHANGE = "proposta.exchange";
    String EXCHANGE_MOTOR = "motor.exchange";
    String EXCHANGE_PROCESSADORA = "processadora.exchange";
    String EXCHANGE_EVENTS = "proposta.events.exchange";
    String EXCHANGE_EVENTS_MOTOR = "motor.events.exchange";
    String EXCHANGE_EVENTS_PROCESSADORA = "processadora.events.exchange";

    String QUEUE_GFE = "gfe.armazenar-mensagem-gfe.queue";
    String QUEUE_CRIAR_PROPOSTA = "proposta.criar-proposta.queue";
    String QUEUE_ANALISAR_PRE_PROPOSTA = "proposta.analisar-pre-proposta.queue";
    String QUEUE_PRE_PROPOSTA_ANALISADA = "proposta.analisar-pre-proposta.queue";
    String QUEUE_ATUALIZAR_INFOS_PESSOAIS = "proposta.atualizar-infos-pessoais.queue";
    String QUEUE_INFOS_PESSOAIS_ATUALIZADAS = "proposta.atualizar-infos-pessoais.queue";
    String QUEUE_ATUALIZAR_EMAIL_VALIDADO = "proposta.atualizar-email-validado.queue";
    String QUEUE_EMAIL_VALIDADO_ATUALIZADO = "proposta.atualizar-email-validado.queue";
    String QUEUE_ANALISAR_POS_PROPOSTA = "proposta.analisar-pos-proposta.queue";
    String QUEUE_POS_PROPOSTA_ANALISADA = "proposta.analisar-pos-proposta.queue";
}
