package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.NegarPrePropostaMessage;
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
public class NegarPrePropostaEvent implements SucessoErroEvent<NegarPrePropostaMessage, String> {

    private static final long serialVersionUID = 514426421969709702L;

    private final NegarPrePropostaMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
