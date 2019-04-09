package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.AnalisarPosPropostaMessage;
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
public class AnalisarPosPropostaEvent implements SucessoErroEvent<AnalisarPosPropostaMessage, String> {

    private static final long serialVersionUID = 6164640200642713262L;

    private final AnalisarPosPropostaMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
