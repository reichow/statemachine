package br.com.statemachine.messaging;

/**
 * Interface para definição de nomes de exchanges, queues e routing keys.
 */
public interface Messaging {

    EventOutbox PROPOSTA_CRIADA = new EventOutbox("proposta.criar-proposta.success.event");
    EventOutbox PRE_PROPOSTA_ANALISADA = new EventOutbox("proposta.analisar-pre-proposta.success.event");
    EventOutbox INFOS_PESSOAIS_ATUALIZADAS = new EventOutbox("proposta.infos-pessoais-atualizadas.success.event");
    EventOutbox EMAIL_VALIDADO_ATUALIZADO = new EventOutbox("proposta.email-validado-atualizado.success.event");
    EventOutbox POS_PROPOSTA_ANALISADA = new EventOutbox("proposta.analisar-pos-proposta.success.event");

    EventOutbox PROPOSTA_CRIADA_ERROR = new EventOutbox("proposta.criar-proposta.error.event");
    EventOutbox ANALISE_PRE_PROPOSTA_ERROR = new EventOutbox("proposta.analise-pre-proposta.error.event");
    EventOutbox INFOS_PESSOAIS_ATUALIZADAS_ERROR = new EventOutbox("proposta.infos-pessoais-atualizadas.error.event");
    EventOutbox EMAIL_VALIDADO_ATUALIZADO_ERROR = new EventOutbox("proposta.email-validado-atualizado.error.event");
    EventOutbox ANALISE_POS_PROPOSTA_ERROR = new EventOutbox("proposta.analise-pos-proposta.error.event");

    MessageInbox CRIAR_PROPOSTA = new MessageInbox("proposta.criar-proposta.message");
    MessageInbox ANALISAR_PRE_PROPOSTA = new MessageInbox("proposta.analisar-pre-proposta.message");
    MessageInbox ATUALIZAR_INFOS_PESSOAIS = new MessageInbox("proposta.atualizar-infos-pessoais.message");
    MessageInbox ATUALIZAR_EMAIL_VALIDADO = new MessageInbox("proposta.atualizar-email-validado.message");
    MessageInbox ANALISAR_POS_PROPOSTA = new MessageInbox("proposta.analisar-pos-proposta.message");

    MessageOutbox GFE = new MessageOutbox("gfe.armazenar-mensagem-gfe.message");

    String EXCHANGE = "proposta.exchange";
    String EXCHANGE_EVENTS = "proposta.events.exchange";

    String QUEUE_GFE = "gfe.armazenar-mensagem-gfe.queue";
    String QUEUE_CRIAR_PROPOSTA = "proposta.criar-proposta.queue";
    String QUEUE_ANALISAR_PRE_PROPOSTA = "proposta.analisar-pre-proposta.queue";
    String QUEUE_ATUALIZAR_INFOS_PESSOAIS = "proposta.atualizar-infos-pessoais.queue";
    String QUEUE_ATUALIZAR_EMAIL_VALIDADO = "proposta.atualizar-email-validado.queue";
    String QUEUE_ANALISAR_POS_PROPOSTA = "proposta.analisar-pos-proposta.queue";
}
