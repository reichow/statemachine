package br.com.statemachine.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.statemachine.message.CriarPropostaMessage;
import br.com.statemachine.response.ErrorResponse;
import br.com.statemachine.response.PropostaDTO;
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
