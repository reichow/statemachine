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
public class AnalisarPrePropostaMessage implements Serializable {

    private static final long serialVersionUID = -7703379227520366635L;

    private String cpf;

    private Long numeroProposta;

}
