package br.com.camila.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.camila.statemachine.domain.Estados;
import br.com.camila.statemachine.response.ErrorResponse;
import br.com.camila.statemachine.message.AnalisarPrePropostaMessage;
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
public class AnalisarPrePropostaEvent implements SucessoErroEvent<AnalisarPrePropostaMessage, Estados> {

    private static final long serialVersionUID = 1534990062660000468L;

    private final AnalisarPrePropostaMessage requisicao;

    private final Estados resultado;

    private final ErrorResponse erro;

}
