package br.com.statemachine.messaging;

/**
 * Interface para definição de nomes de exchanges, queues e routing keys.
 */
public interface Messaging {

    EventOutbox PROPOSTA_CRIADA = new EventOutbox("proposta.criar.success.event");
    EventOutbox ANALISE_NEGADA = new EventOutbox("proposta.analise-negada.success.event");
    EventOutbox ANALISE_APROVADA = new EventOutbox("proposta.analise-aprovada.success.event");

    EventOutbox PROPOSTA_CRIADA_ERROR = new EventOutbox("proposta.criar.error.event");
    EventOutbox ANALISE_PRE_PROPOSTA_ERROR = new EventOutbox("proposta.analise-pre.error.event");

    MessageInbox CRIAR_PROPOSTA = new MessageInbox("proposta.criar.message");
    MessageInbox ANALISAR_PROPOSTA = new MessageInbox("proposta.analisar.message");

    MessageOutbox GFE = new MessageOutbox("gfe.armazenar-mensagem-gfe.message");

    String EXCHANGE = "proposta.exchange";
    String EXCHANGE_EVENTS = "proposta.events.exchange";

    String QUEUE_GFE = "gfe.armazenar-mensagem-gfe.queue";
    String QUEUE_CRIAR_PROPOSTA = "proposta.criar.queue";
    String QUEUE_ANALISAR_PRE_PROPOSTA = "proposta.analisar-proposta.queue";
}
