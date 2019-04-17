package br.com.camila.statemachine.messaging;

/**
 * Interface para definição de nomes de exchanges, queues e routing keys.
 */
public interface Messaging {

    //msg recebidas da aplicação externa
    MessageInbox CRIAR_PROPOSTA = new MessageInbox("proposta.criar-proposta.message");
    MessageInbox ANALISAR_PRE_PROPOSTA = new MessageInbox("proposta.analisar-pre-proposta.message");
    MessageInbox ATUALIZAR_INFOS_PESSOAIS = new MessageInbox("proposta.atualizar-infos-pessoais.message");
    MessageInbox ATUALIZAR_EMAIL_VALIDADO = new MessageInbox("proposta.atualizar-email-validado.message");
    MessageInbox ANALISAR_POS_PROPOSTA = new MessageInbox("proposta.analisar-pos-proposta.message");

    //msg enviada para api de motor
    MessageOutbox ANALISAR_PRE_PROPOSTA_MOTOR = new MessageOutbox("motor.analisar-pre-proposta-motor.message");
    MessageOutbox ANALISAR_POS_PROPOSTA_MOTOR = new MessageOutbox("motor.analisar-pos-proposta-motor.message");

    //msg recebida da api de motor
    MessageInbox PRE_PROPOSTA_ANALISADA_MOTOR = new MessageInbox("proposta.pre-proposta-analisada.message");
    MessageInbox PRE_PROPOSTA_MC_ANALISADA_MOTOR = new MessageInbox("proposta.pre-proposta-analisada-mc.message");
    MessageInbox POS_PROPOSTA_ANALISADA_MOTOR = new MessageInbox("proposta.pos-proposta-analisada.message");

    //msg enviada para api processadora
    MessageOutbox ATUALIZAR_INFOS_PESSOAIS_PROCESSADORA = new MessageOutbox("processadora.atualizar-infos-pessoais-processadora.message");
    MessageOutbox ATUALIZAR_EMAIL_VALIDADO_PROCESSADORA = new MessageOutbox("processadora.atualizar-email-validado-processadora.message");

    //msg recebida da api processadora
    MessageInbox INFOS_PESSOAIS_ATUALIZADAS_PROCESSADORA = new MessageInbox("proposta.infos-pessoais-atualizadas.message");
    MessageInbox EMAIL_VALIDADO_ATUALIZADO_PROCESSADORA = new MessageInbox("proposta.email-validado-atualizado.message");

    //exchanges
    String EXCHANGE = "proposta.exchange";
    String EXCHANGE_EVENTS = "proposta.events.exchange";

    //filas
    String QUEUE_CRIAR_PROPOSTA = "proposta.criar-proposta.queue";
    String QUEUE_ANALISAR_PRE_PROPOSTA = "proposta.analisar-pre-proposta.queue";
    String QUEUE_PRE_PROPOSTA_ANALISADA = "proposta.analisar-pre-proposta.queue";
    String QUEUE_ATUALIZAR_INFOS_PESSOAIS = "proposta.atualizar-infos-pessoais.queue";
    String QUEUE_INFOS_PESSOAIS_ATUALIZADAS = "proposta.infos-pessoais-atualizadas.queue";
    String QUEUE_ATUALIZAR_EMAIL_VALIDADO = "proposta.atualizar-email-validado.queue";
    String QUEUE_EMAIL_VALIDADO_ATUALIZADO = "proposta.email-validado-atualizado.queue";
    String QUEUE_ANALISAR_POS_PROPOSTA = "proposta.analisar-pos-proposta.queue";
    String QUEUE_POS_PROPOSTA_ANALISADA = "proposta.pos-proposta-analisada.queue";
}
