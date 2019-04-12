package br.com.camila.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.camila.statemachine.message.AtualizarEmailValidadoMessage;
import br.com.camila.statemachine.response.ErrorResponse;
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
public class AtualizarEmailValidadoEvent implements SucessoErroEvent<AtualizarEmailValidadoMessage, String> {

    private static final long serialVersionUID = 3762356017802315493L;

    private final AtualizarEmailValidadoMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
