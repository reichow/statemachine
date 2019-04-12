package br.com.camila.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.camila.statemachine.response.ErrorResponse;
import br.com.camila.statemachine.message.AtualizarInfosPessoaisMessage;
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
public class AtualizarInfosPessoaisEvent implements SucessoErroEvent<AtualizarInfosPessoaisMessage, String> {

    private static final long serialVersionUID = -4343241043920472109L;

    private final AtualizarInfosPessoaisMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
