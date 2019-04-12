package br.com.camila.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.camila.statemachine.message.CriarPropostaMessage;
import br.com.camila.statemachine.response.ErrorResponse;
import br.com.camila.statemachine.response.PropostaDTO;
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
public class CriarPropostaEvent implements SucessoErroEvent<CriarPropostaMessage, PropostaDTO> {

    private static final long serialVersionUID = 170212259154071821L;

    private final CriarPropostaMessage requisicao;

    private final PropostaDTO resultado;

    private final ErrorResponse erro;

}
