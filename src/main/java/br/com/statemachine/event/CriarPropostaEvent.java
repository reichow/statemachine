package br.com.statemachine.event;

import br.com.statemachine.message.CriarPropostaMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class CriarPropostaEvent implements SucessoErroEvent<CriarPropostaMessage, String> {

    private static final long serialVersionUID = 8170303797496422784L;

    private final CriarPropostaMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
