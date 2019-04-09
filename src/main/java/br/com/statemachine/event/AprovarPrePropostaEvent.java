package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.AprovarPrePropostaMessage;
import br.com.statemachine.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(of = "requisicao")
public class AprovarPrePropostaEvent implements SucessoErroEvent<AprovarPrePropostaMessage, String> {

    private static final long serialVersionUID = 44356421536515435L;

    private final AprovarPrePropostaMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
