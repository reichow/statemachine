package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.NegarPosPropostaMessage;
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
public class NegarPosPropostaEvent implements SucessoErroEvent<NegarPosPropostaMessage, String> {

    private static final long serialVersionUID = 6033886961967763772L;

    private final NegarPosPropostaMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
