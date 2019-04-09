package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.AprovarPosPropostaMessage;
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
public class AprovarPosPropostaEvent implements SucessoErroEvent<AprovarPosPropostaMessage, String> {

    private static final long serialVersionUID = 8170303797496422784L;

    private final AprovarPosPropostaMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
