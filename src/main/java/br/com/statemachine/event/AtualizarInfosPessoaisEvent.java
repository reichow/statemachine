package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.AtualizarInfosPessoaisMessage;
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
public class AtualizarInfosPessoaisEvent implements SucessoErroEvent<AtualizarInfosPessoaisMessage, String> {

    private static final long serialVersionUID = -4343241043920472109L;

    private final AtualizarInfosPessoaisMessage requisicao;

    private final String resultado;

    private final ErrorResponse erro;

}
