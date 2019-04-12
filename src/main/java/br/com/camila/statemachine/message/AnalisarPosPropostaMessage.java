package br.com.camila.statemachine.message;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalisarPosPropostaMessage implements Serializable {

    private static final long serialVersionUID = -4457019729364986621L;

    private final String cpf;

    private final Long numeroProposta;
}
