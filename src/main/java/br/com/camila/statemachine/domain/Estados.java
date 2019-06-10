package br.com.camila.statemachine.domain;

public enum Estados {

    PROPOSTA_INEXISTENTE,
    PROPOSTA_CRIADA,
    ANALISE_PRE,
    NEGADO_PRE,
    APROVADO_PRE,
    INFOS_PESSOAIS,
    EMAIL_VALIDADO,
    ANALISE_POS,
    NEGADO_POS,
    APROVADO_POS,
    PENDENTE_PRE,
    APROVADO,
    NEGADO,
    SUB_STATE_1,
    SUB_STATE_2,
    EM_ANALISE
}
